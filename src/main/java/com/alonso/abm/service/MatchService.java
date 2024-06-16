package com.alonso.abm.service;

import com.alonso.abm.dao.BasicDAO;
import com.alonso.abm.dao.MatchRepostiroy;
import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.match.MatchStands;
import org.springframework.beans.factory.annotation.Autowired;

public class MatchService {

    @Autowired
    private BasicDAO<Match> matchRepostiroy;

    public void receiveReport(MatchStands matchStands) throws Exception {

        Match match = this.matchRepostiroy.getById(matchStands.getMatchId());
        if(match == null)
            throw  new Exception("Match not found!");

        if(!matchStands.getPlayerId().equals(match.getPlayer1().getId())
                || !matchStands.getPlayerId().equals(match.getPlayer2().getId()))
            throw new IllegalArgumentException("The player reporting is not related to this match");

    }

    public Match getById(Long id){
        return this.matchRepostiroy.getById(id);
    }

    public Match save(Match match) {
        return this.matchRepostiroy.save(match);
    }

}
