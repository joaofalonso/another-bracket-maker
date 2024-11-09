package com.alonso.abm.controller;

import com.alonso.abm.domain.match.MatchStandReport;
import com.alonso.abm.domain.match.MatchStands;
import com.alonso.abm.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/match")
public class MatchController {

    @Autowired
    private MatchService matchService;

    @PostMapping
    public ResponseEntity<?> reportMatchStand(@RequestParam MatchStandReport stands){
        try {
            this.matchService.receiveReport(stands);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(e);
        }
    }


}
