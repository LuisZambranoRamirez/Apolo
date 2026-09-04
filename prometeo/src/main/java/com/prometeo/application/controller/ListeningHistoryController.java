package com.prometeo.application.controller;

import com.prometeo.application.entity.ListeningHistory;
import com.prometeo.application.service.ListeningHistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/listening-history")
public class ListeningHistoryController {

    private final ListeningHistoryService listeningHistoryService;

    public ListeningHistoryController(
            ListeningHistoryService listeningHistoryService
    ) {
        this.listeningHistoryService = listeningHistoryService;
    }

    @GetMapping
    public List<ListeningHistory> getHistory() {
        return listeningHistoryService.findAll();
    }

    @GetMapping("/{id}")
    public ListeningHistory getHistoryById(
            @PathVariable Long id
    ) {
        return listeningHistoryService.findById(id);
    }

    @GetMapping("/user/{username}")
    public List<ListeningHistory> getUserHistory(
            @PathVariable String username
    ) {
        return listeningHistoryService.findByUsername(username);
    }

    @GetMapping("/song/{artist}/{songName}")
    public List<ListeningHistory> getSongHistory(
            @PathVariable String artist,
            @PathVariable String songName
    ) {
        return listeningHistoryService.findBySong(
                songName,
                artist
        );
    }

    @PostMapping
    public ListeningHistory createHistory(
            @RequestBody ListeningHistory history
    ) {
        return listeningHistoryService.save(history);
    }

    @DeleteMapping("/{id}")
    public void deleteHistory(
            @PathVariable Long id
    ) {
        listeningHistoryService.delete(id);
    }
}
