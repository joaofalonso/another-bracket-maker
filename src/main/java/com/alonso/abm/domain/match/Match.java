package com.alonso.abm.domain.match;

import com.alonso.abm.domain.player.Player;

import java.util.HashSet;
import java.util.Set;

public class Match {

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;

    public Long getId() {
        return id;
    }

    private Player player1;
    private Player player2;
    private MatchState matchState;

    private Set<MatchStands> matchStands;
    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchState = MatchState.OPEN;
        this.matchStands = new HashSet<MatchStands>();
    }

    public Player getPlayer1() {
        return player1;
    }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public MatchState getMatchState() {
        return matchState;
    }

    public void setMatchState(MatchState matchState) {
        this.matchState = matchState;
    }

    public Set<MatchStands> getMatchStands() {
        return matchStands;
    }

    public void setMatchStands(Set<MatchStands> matchStands) {
        this.matchStands = matchStands;
    }

    public Player getPlayer2() {
        return player2;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }
}
