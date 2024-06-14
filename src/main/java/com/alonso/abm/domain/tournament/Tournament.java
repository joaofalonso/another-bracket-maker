package com.alonso.abm.domain.tournament;


import com.alonso.abm.domain.Bracket;
import com.alonso.abm.domain.player.Player;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Tournament {

    private Long id;
    private String name;
    private LocalDateTime startDay;
    private LocalDateTime finalDay;
    private final Set<Player> playersEnrolled;
    private Bracket bracket;

    public Tournament(){
        this.playersEnrolled = new HashSet<>();
    }

    public void initiate(){
        this.bracket = new Bracket(this.playersEnrolled);
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

    public Bracket getBracket() {
        return bracket;
    }

    public void setBracket(Bracket bracket) {
        this.bracket = bracket;
    }
}
