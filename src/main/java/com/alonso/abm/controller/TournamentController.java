package com.alonso.abm.controller;

import com.alonso.abm.domain.tournament.CreateTournament;
import com.alonso.abm.domain.tournament.Tournament;
import com.alonso.abm.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    private TournamentService service;
    @GetMapping()
    public ResponseEntity<?> getTournaments(){
        List<Tournament> tournaments = this.service.getAll();
        return ResponseEntity.ok(tournaments);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTournamentById(@PathVariable long id){
        Tournament tournament = this.service.getById(id);
        return ResponseEntity.ok(tournament);
    }

    @PostMapping
    public ResponseEntity<?> createTournament(@RequestBody CreateTournament createTournament){
        Tournament save = this.service.save(createTournament);
        UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/tournament/" + save.getId()).build();
        return ResponseEntity.created(uriComponents.toUri()).build();
    }

    @PutMapping
    public ResponseEntity<?> updateTournament(@RequestBody UpdateTournament updateTournament){
        boolean b = this.service.updateTournament(updateTournament);
        UriComponents uriComponents = UriComponentsBuilder.newInstance().path("/tournament/" + updateTournament.id()).build();
        return ResponseEntity.ok().header("location", uriComponents.toUri().toString()).build();
    }

}
