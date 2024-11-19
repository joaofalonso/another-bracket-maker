package com.alonso.abm.service;
thid
import com.alonso.abm.domain.tournament.UpdateTournament;
import com.alonso.abm.dao.BasicDAO;
import com.alonso.abm.domain.tournament.CreateTournament;
import com.alonso.abm.domain.tournament.Tournament;
import com.alonso.abm.domain.tournament.TournamentBuilder;
import com.alonso.abm.domain.tournament.exception.TournamentNotFoundException;
import com.alonso.abm.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public Tournament save(CreateTournament createTournament){
        Tournament tournament = new TournamentBuilder().Name(createTournament.name())
                .startDate(createTournament.startDate())
                .finalDate(createTournament.finalDate())
                .build();
        return this.tournamentRepository.save(tournament);
    }

    public List<Tournament> getAll(){
        return this.tournamentRepository.findAll();
    }

    public Tournament getById(Long id){
        Optional<Tournament> tournament = this.tournamentRepository.findById(id);
        if(tournament.isEmpty())
            throw  new TournamentNotFoundException();
        return tournament.orElseThrow();
    }

    public boolean delete(Long id){
        Tournament tournament = this.getById(id);

        if(tournament != null){
            this.tournamentRepository.delete(tournament);
            return true;
        }

        return false;
    }

    public boolean updateTournament(UpdateTournament updateTournament) {
        Tournament tournamentDb = this.getById(updateTournament.id());
        if(tournamentDb == null)
            throw new RuntimeException("Tournament not found");

        this.tournamentRepository.save(tournamentDb);
        return true;
    }
}
