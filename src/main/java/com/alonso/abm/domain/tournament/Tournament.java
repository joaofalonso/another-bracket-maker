package com.alonso.abm.domain.tournament;

import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.player.Player;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Tournament implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDateTime startDay;
    @Column(nullable = false)
    private LocalDateTime finalDay;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "player_tournament",
            joinColumns = { @JoinColumn(name = "tournament_id") },
            inverseJoinColumns = { @JoinColumn(name = "player_id") }
    )
    private final Set<Player> playersEnrolled;
    @Column(nullable = false)
    private TournamentState state;

    public Tournament(){
        this.playersEnrolled = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDateTime startDay) {
        this.startDay = startDay;
    }

    public LocalDateTime getFinalDay() {
        return finalDay;
    }

    public void setFinalDay(LocalDateTime finalDay) {
        this.finalDay = finalDay;
    }

    public Set<Player> getPlayersEnrolled() {
        return playersEnrolled;
    }

    public TournamentState getState() {
        return state;
    }

    public void setState(TournamentState state) {
        this.state = state;
    }
}
