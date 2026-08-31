package com.prometeo.application.repository;

import com.prometeo.application.entity.Song;
import com.prometeo.application.entity.SongId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, SongId> {
}
