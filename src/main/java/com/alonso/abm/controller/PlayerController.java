package com.alonso.abm.controller;

import com.alonso.abm.domain.player.CreatePlayer;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/player")
public class PlayerController {

    @Autowired
    private PlayerService service;
    public ResponseEntity<?> getPlayer(@RequestParam long playerId){
        try{
            Player player = this.service.getById(playerId);
            return ResponseEntity.ok(player);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex);

        }
    }

    public ResponseEntity<?> createPlayer(@RequestBody CreatePlayer createPlayer){
        Player save = this.service.save(createPlayer);
        return ResponseEntity.ok().body(null);
    }
}
