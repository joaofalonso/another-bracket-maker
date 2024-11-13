package com.alonso.abm.controller;

import com.alonso.abm.domain.player.CreatePlayer;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.player.UpdatePlayer;
import com.alonso.abm.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerService service;
    @GetMapping("/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable long id){
        try{
            Player player = this.service.getById(id);
            return ResponseEntity.ok(player);
        }catch (Exception ex){
            return ResponseEntity.internalServerError().body(ex);

        }
    }

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody CreatePlayer createPlayer){
        Player save = this.service.save(createPlayer);
        UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/player/" + save.getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping
    public ResponseEntity<?> updatePlayer(@RequestBody UpdatePlayer updatePlayer){
        return ResponseEntity.ok().body(null);
    }
}
