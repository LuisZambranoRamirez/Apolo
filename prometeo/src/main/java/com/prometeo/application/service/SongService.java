package com.prometeo.application.service;

import com.prometeo.application.entity.Genre;
import com.prometeo.application.entity.Song;
import com.prometeo.application.entity.SongId;
import com.prometeo.application.entity.Subgenre;
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

    public List<SimilarSongDTO> findSimilarSongs(String songName, String artist) {
        Song song = findById(songName, artist);

        ModeloMachineLearning<SongId> model = createModel();

        return model.findSimilarAnalysisUnits(song)
                .stream()
                .map(this::toSimilarSongDTO)
                .toList();
    }


    private ModeloMachineLearning<SongId> createModel() {
        List<Song> songs = songRepository.findAll();

        return new ModeloMachineLearning<>(new HashSet<>(songs));
    }


    private SimilarSongDTO toSimilarSongDTO(ModeloMachineLearning.SimilarityResult<SongId> result) {
        Song song = (Song) result.analysisUnit();

        return new SimilarSongDTO(
                song.getId().getSongName(),
                song.getId().getSongArtist(),
                song.getDurationMs().longValue(),
                song.getGenres()
                        .stream()
                        .map(Genre::getGenre)
                        .toList(),
                song.getSubgenres()
                        .stream()
                        .map(Subgenre::getSubgenre)
                        .toList(),
                song.getSongPopularity(),
                result.similarity()
        );
    }

    public record SimilarSongDTO(
            String songName,
            String artist,
            Long durationSeconds,
            List<String> genres,
            List<String> subgenres,
            Double popularity,
            Double similarity
    ) {
    }
}