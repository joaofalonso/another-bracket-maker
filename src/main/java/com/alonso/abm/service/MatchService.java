package com.alonso.abm.service;

import com.alonso.abm.dao.BasicDAO;

import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.match.MatchStandReport;
import com.alonso.abm.domain.match.MatchStands;
import com.alonso.abm.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MatchService {

    @Autowired
    MatchRepository matchRepository;

    public void receiveReport(MatchStandReport matchStands) throws Exception {

        Match match = this.getById(matchStands.matchId());

        if(matchStands.playerId().equals(match.getPlayer1().getId())
                || matchStands.playerId().equals(match.getPlayer2().getId())){
            //TODO: Match stand reports logic
            match.getMatchStands().add(new MatchStands());
        }
        else
            throw new IllegalArgumentException("The player reporting is not related to this match");

    }

    public Match getById(Long id){

        Optional<Match> match = this.matchRepository.findById(id);
        if(match.isEmpty())
            throw  new RuntimeException("Match not found!");

        return match.orElseThrow();
    }

    public Match save(Match match) {
        return this.matchRepository.save(match);
    }

}
