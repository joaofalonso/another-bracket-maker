package com.alonso.abm.controller;

import com.alonso.abm.domain.tournament.CreateTournament;
import com.alonso.abm.domain.tournament.Tournament;
import com.alonso.abm.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    public ResponseEntity<?> createTournament(@RequestBody CreateTournament createTournament){
        Tournament save = this.service.save(createTournament);
        return ResponseEntity.ok(save);
    }

}
