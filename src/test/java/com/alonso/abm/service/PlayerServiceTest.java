package com.alonso.abm.service;

import com.alonso.abm.domain.player.Player;
import com.alonso.abm.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.event.annotation.BeforeTestClass;

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

        Player savedPlayer = this.service.save(player);
        assertEquals(1L, savedPlayer.getId());
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
