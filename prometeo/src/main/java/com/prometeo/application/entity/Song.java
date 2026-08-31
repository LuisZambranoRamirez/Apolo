package com.prometeo.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "song")
public class Song {

    @EmbeddedId
    private SongId id;

    @Column(name = "song_popularity")
    private Double songPopularity;

    private Double danceability;

    private Double energy;

    @Column(name = "key")
    private Double key;

    private Double loudness;

    private Double mode;

    private Double speechiness;

    private Double acousticness;

    private Double instrumentalness;

    private Double liveness;

    private Double valence;

    private Double tempo;

    @Column(name = "duration_ms")
    private Double durationMs;

    @ManyToMany
    @JoinTable(
            name = "song_genre_map",
            joinColumns = {
                    @JoinColumn(
                            name = "song_name",
                            referencedColumnName = "song_name"
                    ),
                    @JoinColumn(
                            name = "song_artist",
                            referencedColumnName = "song_artist"
                    )
            },
            inverseJoinColumns = @JoinColumn(
                    name = "genre",
                    referencedColumnName = "genre"
            )
    )
    private Set<Genre> genres = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "song_subgenre_map",
            joinColumns = {
                    @JoinColumn(
                            name = "song_name",
                            referencedColumnName = "song_name"
                    ),
                    @JoinColumn(
                            name = "song_artist",
                            referencedColumnName = "song_artist"
                    )
            },
            inverseJoinColumns = @JoinColumn(
                    name = "subgenre",
                    referencedColumnName = "subgenre"
            )
    )
    private Set<Subgenre> subgenres = new HashSet<>();

    public Song() {
    }

    public Song(SongId id) {
        this.id = id;
    }

}

