package com.prometeo.application.controller;

import com.prometeo.application.entity.Song;
import com.prometeo.application.service.SongService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/songs")
public class SongController {

    private final SongService songService;

    public SongController(SongService songService) {
        this.songService = songService;
    }

    @GetMapping
    public List<Song> getSongs() {
        return songService.findAll();
    }

    @GetMapping("/{artist}/{songName}")
    public Song getSong(
            @PathVariable String artist,
            @PathVariable String songName
    ) {
        return songService.findById(songName, artist);
    }

    @PostMapping
    public Song createSong(@RequestBody Song song) {
        return songService.save(song);
    }

    @DeleteMapping("/{artist}/{songName}")
    public void deleteSong(
            @PathVariable String artist,
            @PathVariable String songName
    ) {
        songService.delete(songName, artist);
    }
}