package com.alonso.abm.service;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BracketServiceTest {

    @Test
    public void createEvenPlayersBracket(){
        //TODO: Succesfully create a even bracket
    }

    @Test
    public void createOddPlayersBracket(){
        /*TODO: Successfully create a even bracket
            - Odd Numbers of players
            - One of the matches already has a winner
         */
    }

    @Test
    public void createBracketNoPlayersError(){
        //TODO: Error caused by no enrolled players
    }

    @Test
    public void createBracketNotOpenTournamentError(){
        //TODO: Tournament object with state different from OPEN
    }


}
