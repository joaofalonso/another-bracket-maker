package com.alonso.abm.service;

import com.alonso.abm.domain.tournament.UpdateTournament;
import com.alonso.abm.dao.BasicDAO;
import com.alonso.abm.domain.tournament.CreateTournament;
import com.alonso.abm.domain.tournament.Tournament;
import com.alonso.abm.domain.tournament.TournamentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private BasicDAO<Tournament> dao;

    public Tournament save(CreateTournament createTournament){
        Tournament tournament = new TournamentBuilder().Name(createTournament.name())
                .startDate(createTournament.startDate())
                .finalDate(createTournament.finalDate())
                .build();
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

    public boolean updateTournament(UpdateTournament updateTournament) {
        Tournament tournamentDb = this.getById(updateTournament.id());
        if(tournamentDb == null)
            throw new RuntimeException("Tournament not found");
        return true;
    }
}
