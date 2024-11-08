package com.alonso.abm.service;

import com.alonso.abm.domain.match.Match;
import com.alonso.abm.domain.player.CreatePlayer;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.player.PlayerBuilder;
import com.alonso.abm.domain.tournament.CreateTournament;
import com.alonso.abm.domain.tournament.Tournament;
import com.alonso.abm.domain.tournament.TournamentBuilder;
import com.alonso.abm.domain.tournament.TournamentState;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import javax.management.InvalidAttributeValueException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class BracketServiceTest {

    @Autowired
    private BracketService bracketService;

    @Autowired
    private EnrollService enrollService;
    @Autowired
    private TournamentService tournamentService;

    @Autowired
    private PlayerService playerService;
    private Tournament evenPlayersTournament;
    private Tournament oddPlayersTournament;
    @BeforeEach

    public void init(){
        evenPlayersTournament = tournamentService.save(new CreateTournament("Even Tournament", LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        oddPlayersTournament = tournamentService.save(new CreateTournament("Odd Tournament", LocalDateTime.now(), LocalDateTime.now().plusDays(1)));
        Player player1 = playerService.save(
                new CreatePlayer("Dummy1", "Test","test@email.com","dummyPlayer", LocalDate.now().minusYears(2)));
        Player player2 = playerService.save(
                new CreatePlayer("Dummy1", "Test","test@email.com","dummyPlayer", LocalDate.now().minusYears(2)));
        Player player3 = playerService.save(
                new CreatePlayer("Dummy1", "Test","test@email.com","dummyPlayer", LocalDate.now().minusYears(2)));

        enrollService.enrollment(evenPlayersTournament.getId(), player1.getId());
        enrollService.enrollment(evenPlayersTournament.getId(), player2.getId());

        enrollService.enrollment(oddPlayersTournament.getId(), player1.getId());
        enrollService.enrollment(oddPlayersTournament.getId(), player2.getId());
        enrollService.enrollment(oddPlayersTournament.getId(), player3.getId());
    }
    @Test
    public void createEvenPlayersBracket() throws Exception {
        HashSet<Match> bracket = this.bracketService.createBracket(evenPlayersTournament);
        List<Match> openMatchesList = bracket.stream().filter(m -> m.getMatchStands().isEmpty()).toList();
        Assertions.assertEquals(bracket.size(), openMatchesList.size());

    }

    @Test
    public void createOddPlayersBracket() throws Exception{
        /*TODO: Successfully create a even bracket
            - Odd Numbers of players
            - One of the matches already has a winner
         */
        HashSet<Match> bracket = this.bracketService.createBracket(oddPlayersTournament);
        List<Match> openMatchesList = bracket.stream().filter(m -> m.getMatchStands().isEmpty()).toList();
        Assertions.assertNotEquals(bracket.size(), openMatchesList.size());
    }

    @Test
    public void createBracketNoPlayersError(){
        Tournament noPlayers = new TournamentBuilder().Name("No players").build();

        assertThrows(RuntimeException.class,
                () -> this.bracketService.createBracket(noPlayers),
                "Tournament has no enrolled players!");
    }

    @Test
    public void createBracketNotOpenTournamentError(){
        Tournament closedTournament = new TournamentBuilder().Name("No players").build();
        closedTournament.setState(TournamentState.RUNNING);

        assertThrows(RuntimeException.class,
                () -> this.bracketService.createBracket(closedTournament),
                "Tournament is not OPEN!");
    }


}
