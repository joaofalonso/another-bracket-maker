package com.alonso.abm.service;

import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.tournament.Tournament;
import com.alonso.abm.domain.tournament.TournamentState;
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

        Tournament tournament = this.tournamentService.getById(tournamentId);

        if(tournament.getState() != TournamentState.OPEN)
            throw new RuntimeException("Enrollments to this tournament are closed!");

        //TODO: should update player list in database
        return tournament.getPlayersEnrolled().add(player);
    }

    public boolean removeEnrollment(long tournamentId, long playerId){
        Player player = this.playerService.getById(playerId);

        Tournament tournament = this.tournamentService.getById(tournamentId);

        tournament.getPlayersEnrolled().removeIf( p -> p.getId().equals(player.getId()));
        //TODO: should update player list in database
        return true;
    }
}
