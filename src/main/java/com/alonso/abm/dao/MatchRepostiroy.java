package com.alonso.abm.dao;

import com.alonso.abm.domain.match.Match;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MatchRepostiroy implements  BasicDAO<Match> {
    @Override
    public Match save(Match obj) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public Match getById(Long id) {
        return null;
    }
}
