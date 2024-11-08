package com.alonso.abm.service;

import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.player.PlayerBuilder;
import com.alonso.abm.domain.tournament.Tournament;
import com.alonso.abm.domain.tournament.TournamentState;
import com.sun.source.tree.BinaryTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.InvalidAttributeValueException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BracketService {

    @Autowired
    private MatchService matchService;
    public HashSet<Match> createBracket(Tournament tournament) throws Exception {
        if(tournament.getPlayersEnrolled().isEmpty())
            throw new RuntimeException("Tournament has no enrolled players!");
        if(tournament.getState() != TournamentState.OPEN)
            throw new RuntimeException("Tournament is not OPEN!");
        Stack<Player> players = new Stack<>();
        players.addAll(tournament.getPlayersEnrolled());
        Collections.shuffle(players);
        HashSet<Match> matches = new HashSet<>();
        while (players.peek() != null){
            Player p1 = null;
            Player p2 = null;
            if(!players.isEmpty())
                p1 = players.pop();
            if(!players.isEmpty())
                p2 = players.pop();

            Match match = null;
            if(p2 == null)
                match = new Match(p1);

            else
                match = new Match(p1, p2);
            match.setTournamentId(tournament.getId());
            matches.add(match);

            if(players.isEmpty())
                break;
        }


        matches.stream().forEach(m -> matchService.save(m));
        return matches;
    }
}
