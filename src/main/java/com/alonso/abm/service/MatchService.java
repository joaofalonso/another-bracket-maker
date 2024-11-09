package com.alonso.abm.service;

import com.alonso.abm.dao.BasicDAO;
import com.alonso.abm.dao.MatchRepository;
import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.match.MatchStandReport;
import com.alonso.abm.domain.match.MatchStands;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private BasicDAO<Match> matchRepository;

    public void receiveReport(MatchStandReport matchStands) throws Exception {

        Match match = this.matchRepository.getById(matchStands.matchId());
        if(match == null)
            throw  new Exception("Match not found!");

        if(matchStands.playerId().equals(match.getPlayer1().getId())
                || matchStands.playerId().equals(match.getPlayer2().getId())){
            //TODO: Match stand reports logic
        }
        else
            throw new IllegalArgumentException("The player reporting is not related to this match");

    }

    public Match getById(Long id){
        return this.matchRepository.getById(id);
    }

    public Match save(Match match) {
        return this.matchRepository.save(match);
    }

}
