package com.prometeo.application.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "listening_history")
public class ListeningHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long historyId;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
            name = "username",
            referencedColumnName = "username",
            nullable = false
    )
    private AppUser user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(
                    name = "song_name",
                    referencedColumnName = "song_name",
                    nullable = false
            ),
            @JoinColumn(
                    name = "song_artist",
                    referencedColumnName = "song_artist",
                    nullable = false
            )
    })
    private Song song;

    @Column(name = "played_at", nullable = false)
    private LocalDateTime playedAt;

    @Column(name = "duration_played_ms")
    private Long durationPlayedMs;

    @Column(nullable = false)
    private Boolean completed = false;

    public ListeningHistory() {
    }
}
