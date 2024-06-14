package com.alonso.abm.service;

import com.alonso.abm.domain.player.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

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
        testPlayer = new Player();
        testPlayer.setNickName("Jhon Doe");
        testPlayer.setLastName("dur");
        service.save(testPlayer);
    }


    @Test
    public void testSaveSuccess(){
        Player player = new Player();
        player.setFirstName("Hinata");
        player.setLastName("Shoyo");
        player.setNickName("Little Giant");
        player.setEmail("hinata.shoyo@karasuno.com");

        Player savedPlayer = this.service.save(player);
        assertEquals(player.getFirstName(), savedPlayer.getFirstName());
        assertEquals(player.getLastName(), savedPlayer.getLastName());
        assertEquals(player.getNickName(), savedPlayer.getNickName());
        assertEquals(player.getEmail(), savedPlayer.getEmail());
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
        Player player = new Player();
        Player save = this.service.save(player);

        boolean isDeleted = this.service.delete(save.getId());
        assertTrue(isDeleted);
    }

    @Test
    public void testDeleteFalse(){
        boolean isDeleted = this.service.delete(99L);
        assertFalse(isDeleted);
    }

}
