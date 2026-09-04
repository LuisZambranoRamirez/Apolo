package com.prometeo.application.entity;

import com.prometeo.application.entity.statistics.AnalysisUnit;
import com.prometeo.application.entity.statistics.Continuous;
import com.prometeo.application.entity.statistics.Nominal;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Setter
@Getter
@Entity
@Table(name = "song")
public class Song extends AnalysisUnit<SongId> {

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
        super(new SongId());
    }

    public Song(SongId id) {
        super(id);
    }

    @Override
    public void initializeVariables() {
        try {
            addVariable(new Continuous("songPopularity", songPopularity));
            addVariable(new Continuous("danceability", danceability));
            addVariable(new Continuous("energy", energy));
            addVariable(new Continuous("key", key));
            addVariable(new Continuous("loudness", loudness));
            addVariable(new Continuous("mode", mode));
            addVariable(new Continuous("speechiness", speechiness));
            addVariable(new Continuous("acousticness", acousticness));
            addVariable(new Continuous("instrumentalness", instrumentalness));
            addVariable(new Continuous("liveness", liveness));
            addVariable(new Continuous("valence", valence));
            addVariable(new Continuous("tempo", tempo));
            addVariable(new Continuous("durationMs", durationMs));

            Set<String> genreNames = genres.stream()
                    .map(Genre::getGenre)
                    .collect(Collectors.toSet());

            addVariable(new Nominal("genres", genreNames));

            Set<String> subgenreNames = subgenres.stream()
                    .map(Subgenre::getSubgenre)
                    .collect(Collectors.toSet());

            addVariable(new Nominal("subgenres", subgenreNames));

        } catch (Exception e) {
            throw new IllegalStateException(
                    "Error extracting Song variables.",
                    e
            );
        }
    }


}

