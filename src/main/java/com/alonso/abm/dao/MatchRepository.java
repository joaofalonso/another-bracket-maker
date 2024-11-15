package com.alonso.abm.dao;

import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.player.Player;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class MatchRepository implements  BasicDAO<Match> {

    private static Long nextId = 0L;
    private final HashMap<Long, Match> matches = new HashMap<Long, Match>();

    @Override
    public Match save(Match match) {
        match.setId(nextId++);
        this.matches.put(match.getId(), match);
        return match;
    }

    @Override
    public void update(Match obj) {

    }

    @Override
    public void delete(Long id) {
        this.matches.remove(id);
    }

    @Override
    public List<Match> getAll() {
        return this.matches.values().stream().toList();
    }

    @Override
    public Match getById(Long id) {
        return this.matches.get(id);
    }
}
