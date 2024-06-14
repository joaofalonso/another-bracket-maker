package com.alonso.abm.service;

import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.tournament.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollService {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TournamentService tournamentService;

    public EnrollService() {

    }

    public boolean enrollment(long tournamentId, long playerId){

        Player player = this.playerService.getById(playerId);
        if(player == null)
            throw new RuntimeException("Player not found by id " + playerId);

        Tournament tournament = this.tournamentService.getById(tournamentId);
        if(tournament == null)
            throw new RuntimeException("Tournament not found by id " + playerId);

        return tournament.getPlayersEnrolled().add(player);
    }
}
