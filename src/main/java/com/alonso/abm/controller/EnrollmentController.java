package com.alonso.abm.controller;

import com.alonso.abm.service.EnrollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/enroll")
public class EnrollmentController {

    @Autowired
    private EnrollService enrollService;

    @PostMapping
    public ResponseEntity<?> enrollPlayerToTournament(@RequestBody long tournamentId, @RequestBody long playerId){
        this.enrollService.enrollment(tournamentId, playerId);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping
    public ResponseEntity<?> removePlayerEnrollmentFromTournament(@RequestBody long tournamentId, @RequestBody long playerId){
        this.enrollService.removeEnrollment(tournamentId, playerId);
        return ResponseEntity.noContent().build();
    }
}
