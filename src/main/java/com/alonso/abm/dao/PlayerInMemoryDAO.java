package com.alonso.abm.dao;

import com.alonso.abm.domain.player.Player;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class PlayerInMemoryDAO implements BasicDAO<Player> {

    private static Long nextId = 0L;
    private final HashMap<Long, Player> players = new HashMap<Long, Player>();

    @Override
    public Player save(Player player) {
        player.setId(nextId++);
        this.players.put(player.getId(), player);
        return player;
    }

    @Override
    public void update(Player player){
        this.players.put(player.getId(), player);
    }

    @Override
    public void delete(Long id) {
        this.players.remove(id);
    }

    @Override
    public List<Player> getAll() {
        return this.players.values().stream().toList();
    }

    @Override
    public Player getById(Long id) {
        return this.players.get(id);
    }
}
