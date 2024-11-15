package com.alonso.abm.dao;

import com.alonso.abm.domain.tournament.Tournament;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class TournamentInMemoryDAO implements  BasicDAO<Tournament> {

    private static Long nextId = 0L;
    private final HashMap<Long, Tournament> tournaments = new HashMap<Long, Tournament>();
    @Override
    public Tournament save(Tournament t) {
        t.setId(nextId++);
        this.tournaments.put(t.getId(), t);
        return t;
    }

    @Override
    public void update(Tournament tournament) {
        this.tournaments.put(tournament.getId(), tournament);
    }

    @Override
    public void delete(Long id) {
        this.tournaments.remove(id);
    }

    @Override
    public List<Tournament> getAll() {
        return this.tournaments.values().stream().toList();
    }

    @Override
    public Tournament getById(Long id) {
        return this.tournaments.get(id);
    }
}
