package com.prometeo.application.service;

import com.prometeo.application.entity.Song;
import com.prometeo.application.entity.SongId;
import com.prometeo.application.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SongService {

    private final SongRepository songRepository;

    public SongService(SongRepository songRepository) {
        this.songRepository = songRepository;
    }

    public List<Song> findAll() {
        return songRepository.findAll();
    }

    public Song findById(String songName, String artist) {
        SongId id = new SongId(songName, artist);

        return songRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Song not found")
                );
    }

    public Song save(Song song) {
        return songRepository.save(song);
    }

    public void delete(String songName, String artist) {
        SongId id = new SongId(songName, artist);
        songRepository.deleteById(id);
    }
}
