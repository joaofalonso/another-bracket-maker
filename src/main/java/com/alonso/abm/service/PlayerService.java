package com.alonso.abm.service;

import com.alonso.abm.dao.BasicDAO;
import com.alonso.abm.domain.player.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private BasicDAO<Player> dao;

    public Player save(Player player){
        return this.dao.save(player);
    }

    public List<Player> getAll(){
        return this.dao.getAll();
    }

    public boolean delete(Long id){
        Player player = getById(id);
        if(player != null){
            this.dao.delete(id);
            return true;
        }

        return false;
    }

    public Player getById(Long id){

        return this.dao.getById(id);
    }
}
