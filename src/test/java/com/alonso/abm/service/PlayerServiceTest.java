package com.alonso.abm.service;

import com.alonso.abm.domain.player.CreatePlayer;
import com.alonso.abm.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PlayerServiceTest {

    @Autowired
    private PlayerService service;


    private Player testPlayer;
    @BeforeEach
    public void setup()
    {

        testPlayer= service.save(new CreatePlayer("Jhon",
                "Doe",
                "joe@doe.who",
                "who",
                LocalDate.now().minusYears(20)
        ));

    }


    @Test
    public void testSaveSuccess(){

        CreatePlayer hinata = new CreatePlayer("Hinata",
                "Shoyo",
                "hinata.shoyo@karasuno.com",
                "Little Giant",
                LocalDate.now().minusYears(16)
        );

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
    public void testGetByIdNotFound(){
        Player player = this.service.getById(99L);
        assertNull(player);
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
    public void testDeleteFalse(){
        boolean isDeleted = this.service.delete(99L);
        assertFalse(isDeleted);
    }

}
