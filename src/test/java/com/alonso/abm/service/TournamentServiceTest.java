package com.alonso.abm.service;

import com.alonso.abm.domain.tournament.Tournament;
import com.alonso.abm.service.TournamentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TournamentServiceTest {

    @Autowired
    private TournamentService service;

    @Test
    public void saveSuccess(){
        Tournament tournament = new Tournament();
        tournament.setName("First Tourmant");
        Tournament save = service.save(tournament);

        Assertions.assertEquals(0, save.getId());
    }
}
