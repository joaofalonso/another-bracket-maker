package com.alonso.abm.service;

import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.tournament.Tournament;
import com.sun.source.tree.BinaryTree;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.util.stream.Collectors;

public class BracketService {

    @Autowired
    private MatchService matchService;
    public HashSet<Match> createBracket(Tournament tournament){
        Stack<Player> players = new Stack<>();
        players.addAll(tournament.getPlayersEnrolled());
        Collections.shuffle(players);
        HashSet<Match> matches = new HashSet<>();
        while (players.peek() != null){
            Match match = new Match(players.pop(), players.pop());
            match.setTournamentId(tournament.getId());
            matches.add(match);
        }
        matches.stream().forEach(m -> matchService.save(m));
        return matches;
    }
}
