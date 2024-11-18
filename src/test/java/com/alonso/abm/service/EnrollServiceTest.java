package com.alonso.abm.service;

import com.alonso.abm.domain.player.CreatePlayer;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.player.exception.PlayerNotFoundException;
import com.alonso.abm.domain.tournament.CreateTournament;
import com.alonso.abm.domain.tournament.Tournament;
import com.alonso.abm.domain.tournament.TournamentState;
import com.alonso.abm.domain.tournament.exception.EnrollmentClosedException;
import com.alonso.abm.domain.tournament.exception.TournamentNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalUnit;

import static org.junit.jupiter.api.Assertions.*;

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
    private Tournament closedTournament;
    @BeforeEach
    public void setup(){
        player = playerService.save(new CreatePlayer("Jhon",
                "Doe",
                "joe@doe.who",
                "who",
                LocalDate.now().minusYears(20)
        ));


        tournament = this.tournamentService.save(new CreateTournament("Ove 2024", LocalDateTime.now(), LocalDateTime.now().plusDays(3)));

        closedTournament = this.tournamentService.save(new CreateTournament("Ove 2023", LocalDateTime.now(), LocalDateTime.now().plusDays(3)));
        closedTournament.setState(TournamentState.RUNNING);
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
        assertThrows(PlayerNotFoundException.class,
                () -> this.enrollService.enrollment (tournament.getId(), invalidId)
                );

    }

    @Test
    public void testEnrollmentTournamentNotFound(){

        long invalidId = 99L;
        assertThrows(TournamentNotFoundException.class,
                () -> this.enrollService.enrollment(invalidId, player.getId())
        );
    }

    @Test
    public void testEnrollmentFailTournamentNotOpen(){
        assertThrows(EnrollmentClosedException.class,
                () -> this.enrollService.enrollment(player.getId(), closedTournament.getId())
        );
    }

}
