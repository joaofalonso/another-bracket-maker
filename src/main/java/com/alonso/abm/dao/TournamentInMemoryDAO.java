package com.alonso.abm.dao;

import com.alonso.abm.domain.tournament.Tournament;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TournamentInMemoryDAO implements  BasicDAO<Tournament> {

    private static Long nextId = 0L;
    private List<Tournament> tournaments = new ArrayList<Tournament>();
    @Override
    public Tournament save(Tournament t) {
        t.setId(nextId++);
        this.tournaments.add(t);
        return t;
    }

    @Override
    public void delete(Long id) {
        this.tournaments.remove(id);
    }

    @Override
    public List<Tournament> getAll() {
        return this.tournaments;
    }
}
