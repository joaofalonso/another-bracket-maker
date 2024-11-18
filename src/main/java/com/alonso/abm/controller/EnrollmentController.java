package com.alonso.abm.controller;

import com.alonso.abm.domain.tournament.Enrollment;
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
    public ResponseEntity<?> enrollPlayerToTournament(@RequestBody Enrollment enrollment){
        this.enrollService.enrollment(enrollment.tournamentId(), enrollment.playerId());
        return ResponseEntity.noContent().build();

    }

    @PutMapping
    public ResponseEntity<?> removePlayerEnrollmentFromTournament(@RequestBody Enrollment enrollment){
        this.enrollService.removeEnrollment(enrollment.tournamentId(), enrollment.playerId());
        return ResponseEntity.noContent().build();
    }
}
