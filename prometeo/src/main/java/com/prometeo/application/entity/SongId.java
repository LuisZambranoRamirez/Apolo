package com.prometeo.application.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Setter
@Getter
@Embeddable
public class SongId implements Serializable {

    @Column(name = "song_name", length = 255)
    private String songName;

    @Column(name = "song_artist", length = 255)
    private String songArtist;

    public SongId() {
    }

    public SongId(String songName, String songArtist) {
        this.songName = songName;
        this.songArtist = songArtist;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SongId songId)) return false;

        return Objects.equals(songName, songId.songName)
                && Objects.equals(songArtist, songId.songArtist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(songName, songArtist);
    }
}
