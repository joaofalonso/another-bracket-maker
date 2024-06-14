package com.alonso.abm.service;

import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.tournament.Tournament;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)

public class EnrollServiceTest {

    @Autowired
    private EnrollService enrollService;

    @Autowired
    private PlayerService playerService;

    @Autowired
    private TournamentService tournamentService;


    private Player player;

    private Tournament tournament;

    @BeforeEach
    public void setup(){
        player = new Player();
        player = this.playerService.save(player);

        tournament = new Tournament();
        tournament = this.tournamentService.save(tournament);
    }
    @Test
    public void testEnrollmentSuccess(){

        boolean enrollment = this.enrollService.enrollment(tournament.getId(), player.getId());
        assertTrue(enrollment);

        Tournament tournament1 = tournamentService.getById(tournament.getId());
        boolean isPlayerEnrolled = tournament1.getPlayersEnrolled().contains(player);
        assertTrue(isPlayerEnrolled);
    }

    @Test
    public void testEnrollmentPlayerNotFound(){
        long invalidId = 99L;
        assertThrows(RuntimeException.class,
                () -> this.enrollService.enrollment(invalidId, tournament.getId()),
                "Player not found by id " + Long.toString(invalidId));

    }

    @Test
    public void testEnrollmentTournamentNotFound(){

        long invalidId = 99L;
        assertThrows(RuntimeException.class,
                () -> this.enrollService.enrollment(player.getId(), invalidId),
                "Tournament not found by id " + Long.toString(invalidId));
    }

}
