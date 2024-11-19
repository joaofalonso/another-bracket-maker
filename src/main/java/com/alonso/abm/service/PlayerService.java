package com.alonso.abm.service;

import com.alonso.abm.dao.BasicDAO;
import com.alonso.abm.domain.player.CreatePlayer;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.player.PlayerBuilder;
import com.alonso.abm.domain.player.UpdatePlayer;
import com.alonso.abm.domain.player.exception.PlayerNotFoundException;
import com.alonso.abm.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository playerRepository;

    public Player save(CreatePlayer createPlayer){

        PlayerBuilder playerBuilder = new PlayerBuilder();
        Player player = playerBuilder.firstName(createPlayer.firstName())
                .lastName(createPlayer.lastName())
                .nickName(createPlayer.nickName())
                .email(createPlayer.email())
                .dateOfBirth(createPlayer.dateOfBirth())
                .build();
        return this.playerRepository.save(player);
    }

    public List<Player> getAll(){
        return this.playerRepository.findAll();
    }

    public boolean delete(Long id){
        Player player = getById(id);
        if(player != null){
            this.playerRepository.delete(player);
            return true;
        }

        return false;
    }

    public boolean updatePlayer(UpdatePlayer updatePlayer){
        Player playerDb = this.getById(updatePlayer.id());
        this.playerRepository.save(playerDb);
        return true;
    }

    public Player getById(Long id){
        Optional<Player> player = this.playerRepository.findById(id);
        if(player.isEmpty())
            throw new PlayerNotFoundException();
         return player.orElseThrow();
    }
}
