package com.alonso.abm.service;

import com.alonso.abm.dao.BasicDAO;
import com.alonso.abm.dao.TournamentInMemoryDAO;
import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.tournament.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Service
public class TournamentService {

    @Autowired
    private BasicDAO<Tournament> dao;

    public Tournament save(Tournament tournament){
        return this.dao.save(tournament);
    }

    public List<Tournament> getAll(){
        return this.dao.getAll();
    }

    public Tournament getById(Long id){
        return this.dao.getById(id);
    }

    public boolean delete(Long id){
        Tournament tournament = getById(id);

        if(tournament != null){
            this.dao.delete(id);
            return true;
        }

        return false;
    }
    public void pairPlayers(Tournament tournament) {

        Stack<Player> playersStack = new Stack<>();
        List<Player> players = tournament.getPlayersEnrolled().stream().collect(Collectors.toList());
        Collections.shuffle(players);
        playersStack.addAll(players);

        while (!playersStack.isEmpty())
        {
            tournament.addMatch(new Match(playersStack.pop(), playersStack.pop()));
        }

    }
}
