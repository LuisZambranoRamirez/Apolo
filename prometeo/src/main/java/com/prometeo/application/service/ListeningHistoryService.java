package com.prometeo.application.service;

import com.prometeo.application.entity.ListeningHistory;
import com.prometeo.application.repository.ListeningHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningHistoryService {

    private final ListeningHistoryRepository listeningHistoryRepository;

    public ListeningHistoryService(
            ListeningHistoryRepository listeningHistoryRepository
    ) {
        this.listeningHistoryRepository = listeningHistoryRepository;
    }

    public List<ListeningHistory> findAll() {
        return listeningHistoryRepository.findAll();
    }

    public ListeningHistory findById(Long id) {
        return listeningHistoryRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Listening history not found")
                );
    }

    public List<ListeningHistory> findByUsername(String username) {
        return listeningHistoryRepository
                .findByUserUsername(username);
    }

    public List<ListeningHistory> findBySong(
            String songName,
            String artist
    ) {
        return listeningHistoryRepository
                .findBySongIdSongNameAndSongIdSongArtist(
                        songName,
                        artist
                );
    }

    public ListeningHistory save(ListeningHistory history) {
        return listeningHistoryRepository.save(history);
    }

    public void delete(Long id) {
        listeningHistoryRepository.deleteById(id);
    }
}
