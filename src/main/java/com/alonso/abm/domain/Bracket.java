package com.alonso.abm.domain;

import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.player.Player;

import java.util.*;

public class Bracket {
    Long id;
    List<Round> rounds;

    private Round currentRound;
    public Bracket(Set<Player> players){
        this.rounds = new ArrayList<>();
    }

    public void createRound(){

    }

}
