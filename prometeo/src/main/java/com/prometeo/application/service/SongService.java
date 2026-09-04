package com.prometeo.application.service;

import com.prometeo.application.entity.Song;
import com.prometeo.application.entity.SongId;
import com.prometeo.application.entity.machineLearning.ModeloMachineLearning;
import com.prometeo.application.repository.SongRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
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

    public List<Song> findSimilarSongs(String songName, String artist) {

        Song song = findById(songName, artist);

        List<Song> songs = songRepository.findAll();

        ModeloMachineLearning<SongId> model =
                new ModeloMachineLearning<>(new HashSet<>(songs));

        return model.findSimilarAnalysisUnits(song)
                .stream()
                .map(s -> (Song) s)
                .toList();
    }
}