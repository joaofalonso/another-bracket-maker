package com.alonso.abm.service;

import com.alonso.abm.domain.player.Player;
import com.alonso.abm.service.PlayerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import java.util.List;

@SpringBootTest
public class PlayerServiceTest {

    @Autowired
    private PlayerService service;

    @BeforeTestClass
    public void setup()
    {
        Player player = new Player();
        player.setNickName("Jhon Doe");
        player.setLastName("dur");
        service.save(player);
    }


    @Test
    public void testAddSuccess(){
        Player player = new Player();
        player.setFirstName("Hinata");
        player.setLastName("Shoyo");

        Player savedPlayer = this.service.save(player);
        Assertions.assertEquals(savedPlayer.getId(), 0);
    }

    @Test
    public void testGetAll(){
        List<Player> all = this.service.getAll();
        Assertions.assertEquals(1, all.size());
    }
}
