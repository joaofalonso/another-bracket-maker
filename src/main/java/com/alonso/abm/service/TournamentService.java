package com.alonso.abm.service;

import com.alonso.abm.dao.BasicDAO;
import com.alonso.abm.dao.TournamentInMemoryDAO;
import com.alonso.abm.domain.tournament.Tournament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private BasicDAO<Tournament> dao;

    public Tournament save(Tournament tournament){
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
}
