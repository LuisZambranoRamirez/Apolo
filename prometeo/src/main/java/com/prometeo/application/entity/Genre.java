package com.prometeo.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "genre")
public class Genre {

    @Id
    @Column(name = "genre", length = 100)
    private String genre;

    @ManyToMany(mappedBy = "genres")
    private Set<Song> songs = new HashSet<>();

    public Genre() {
    }

    public Genre(String genre) {
        this.genre = genre;
    }

}
