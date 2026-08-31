package com.prometeo.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "subgenre")
public class Subgenre {

    @Id
    @Column(name = "subgenre", length = 100)
    private String subgenre;

    @ManyToMany(mappedBy = "subgenres")
    private Set<Song> songs = new HashSet<>();

    public Subgenre() {
    }

    public Subgenre(String subgenre) {
        this.subgenre = subgenre;
    }

}
