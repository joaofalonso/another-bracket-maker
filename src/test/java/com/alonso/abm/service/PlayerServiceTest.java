package com.alonso.abm.service;

import com.alonso.abm.domain.player.CreatePlayer;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.player.exception.InvalidEmailException;
import com.alonso.abm.domain.player.exception.PlayerNotFoundException;
import com.alonso.abm.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ExtendWith(SpringExtension.class)
public class PlayerServiceTest {

    @Autowired
    private PlayerService service;
    private Player testPlayer;

    @MockBean
    PlayerRepository playerRepository;

    @BeforeEach
    public void setup()
    {

        testPlayer= service.save(new CreatePlayer("Hinata",
                "Shoyo",
                "hinata.shoyo@karasuno.com",
                "Little Giant",
                LocalDate.now().minusYears(18)
        ));

    }

    @Test
    public void testSaveSuccess(){

        CreatePlayer hinata = new CreatePlayer("Hinata",
                "Shoyo",
                "hinata.shoyo@karasuno.com",
                "Little Giant",
                LocalDate.now().minusYears(18)
        );

        doReturn(testPlayer).when(playerRepository).save(any());

        Player savedPlayer = this.service.save(hinata);
        assertEquals(hinata.firstName(), savedPlayer.getFirstName());
        assertEquals(hinata.lastName(), savedPlayer.getLastName());
        assertEquals(hinata.nickName(), savedPlayer.getNickName());
        assertEquals(hinata.email(), savedPlayer.getEmail());
    }

    @Test
    public void testGetAll(){
        List<Player> all = this.service.getAll();
        assertEquals(1, all.size());
    }

    @Test
    public void testGetById(){
        Player playerDb = this.service.getById(testPlayer.getId());

        assertEquals(testPlayer.getFirstName(), playerDb.getFirstName());
        assertEquals(testPlayer.getLastName(), playerDb.getLastName());
    }

    @Test
    public void testGeyByIdPlayerNotFoundEx(){
        assertThrows(PlayerNotFoundException.class,
                () -> this.service.getById(99L));
    }



    @Test
    public void testDeleteTrue(){
        Player save = this.service.save(new CreatePlayer("Jhon",
                "Doe",
                "joe@doe.who",
                "who",
                LocalDate.now().minusYears(20)
        ));

        boolean isDeleted = this.service.delete(save.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteThrowsPlayerNotFoundException(){
        assertThrows(PlayerNotFoundException.class,
                () -> this.service.delete(99L));
    }

}
