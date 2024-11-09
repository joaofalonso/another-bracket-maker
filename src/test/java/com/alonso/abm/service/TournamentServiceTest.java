package com.alonso.abm.service;

import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.tournament.CreateTournament;
import com.alonso.abm.domain.tournament.Tournament;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class TournamentServiceTest {

    @Autowired
    private TournamentService service;
    private Tournament sample;

    @Autowired
    private PlayerService playerService;
    @BeforeEach
    public void setup(){
        this.sample = this.service.save(new CreateTournament("Ove 2024", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2)));
    }

    @Test
    public void testSaveSuccess(){

        CreateTournament createTournament = new CreateTournament("First Tournament", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
        Tournament save = service.save(createTournament);

        assertEquals(createTournament.name(), save.getName());
        assertEquals(createTournament.startDate(), save.getStartDay());

    }

    @Test
    public void testSaveTournamentInvalidName(){
        CreateTournament createTournament = new CreateTournament("", LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(2));
        assertThrows(RuntimeException.class,
                () ->  service.save(createTournament),
                "Invalid name!");
    }

    @Test
    public void testSaveTournamentInvalidStartDate(){
        CreateTournament createTournament = new CreateTournament("First Tournament", LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(2));
        assertThrows(RuntimeException.class,
                () ->  service.save(createTournament),
                "Invalid start date!");
    }

    @Test
    public void testSaveTournamentInvalidEndDateEqualToStartDate(){
        CreateTournament createTournament = new CreateTournament("First Tournament", LocalDateTime.now(), LocalDateTime.now());
        assertThrows(RuntimeException.class,
                () ->  service.save(createTournament),
                "Invalid final date!");
    }

    @Test
    public void testSaveTournamentInvalidEndDateBeforeToStartDate(){
        CreateTournament createTournament = new CreateTournament("First Tournament", LocalDateTime.now(), LocalDateTime.now().minusDays(1));
        assertThrows(RuntimeException.class,
                () ->  service.save(createTournament),
                "Invalid final date!");
    }

    @Test
    public void testGetAll(){
        List<Tournament> all = this.service.getAll();
        assertEquals(1, all.size());
    }

    @Test
    public void testGetByIdSuccess(){
        Tournament t = this.service.getById(sample.getId());

        assertEquals(sample.getId(), t.getId());
        assertEquals(sample.getName(), t.getName());
        assertEquals(sample.getStartDay(), t.getStartDay());
    }

    @Test
    public void testGetByIdNotFound(){
        Tournament tournament = this.service.getById(99L);
        assertNull(tournament);
    }

    @Test
    public void testDeleteTrue(){

        Tournament tournamentToDelete = this.service.save(new CreateTournament("2nd Tournament", LocalDateTime.now(),LocalDateTime.now().plusDays(2)));

        boolean isDeleted = this.service.delete(tournamentToDelete.getId());

        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteFalse(){
        boolean isDeleted = this.service.delete(9999999L);
        assertFalse(isDeleted);
    }


}
