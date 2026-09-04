package com.prometeo.application.repository;

import com.prometeo.application.entity.ListeningHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListeningHistoryRepository extends JpaRepository<ListeningHistory, Long> {

    List<ListeningHistory> findByUserUsername(String username);

    List<ListeningHistory> findBySongIdSongNameAndSongIdSongArtist(
            String songName,
            String artist
    );
}
