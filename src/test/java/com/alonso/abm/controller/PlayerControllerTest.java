package com.alonso.abm.controller;


import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.player.PlayerBuilder;
import com.alonso.abm.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;

import static org.mockito.Mockito.when;

@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    private Player player;
    @BeforeEach
    public void init(){
        new PlayerBuilder()
                .firstName("John")
                .lastName("Doe")
                .nickName("johnD")
                .email("test@email.com")
                .dateOfBirth(LocalDate.now().minusYears(18))
                .build();
    }
    @Test
    @WithMockUser
    public void testGetResponse200() throws Exception{
        when(playerService.getById(1L)).thenReturn(player);
        this.mockMvc.perform(get("/player/1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostResponse201(){
        //TODO: Test get request with response code 201
    }

    @Test
    public void testPutResponse201(){
        //TODO: Test get request with response code 200
    }

}
