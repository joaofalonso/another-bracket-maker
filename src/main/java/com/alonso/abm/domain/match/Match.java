package com.alonso.abm.domain.match;

import com.alonso.abm.domain.player.Player;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Player player1;
    private Player player2;
    private MatchState matchState;
    private Set<MatchStands> matchStands;
    private Long tournamentId;

    public Match(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.matchState = MatchState.OPEN;
        this.matchStands = new HashSet<MatchStands>();
    }

    public Match(Player player1){
        this.player1 = player1;
        this.matchState = MatchState.CLOSED;
        this.matchStands = new HashSet<MatchStands>();
        MatchStands stand = new MatchStands();
        stand.setWinner(this.player1);
        stand.setPlayerId(this.player1.getId());
        this.matchStands.add(stand);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getTournamentId() {
        return tournamentId;
    }
    public void setTournamentId(Long tournamentId) {
        this.tournamentId = tournamentId;
    }
}
