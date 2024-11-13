package com.alonso.abm.controller;


import com.alonso.abm.domain.player.CreatePlayer;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.player.PlayerBuilder;
import com.alonso.abm.service.PlayerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.nio.charset.StandardCharsets;
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
       player =  new PlayerBuilder()
                .firstName("John")
                .lastName("Doe")
                .nickName("johnD")
                .email("test@email.com")
                .dateOfBirth(LocalDate.now().minusYears(18))
                .build();
       player.setId(1L);
    }
    @Test
    @WithMockUser
    public void testGetResponse200() throws Exception{
        when(playerService.getById(1L)).thenReturn(player);
        this.mockMvc.perform(get("/player/1"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    public void testPostResponse201() throws Exception {
        CreatePlayer createPlayer = new CreatePlayer("teste",
                "teste",
                "teste@test.com",
                "testNick",
                LocalDate.now().minusYears(19));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String payload = objectMapper.writeValueAsString(createPlayer);

        when(playerService.save(createPlayer)).thenReturn(player);
        this.mockMvc.perform(post("/player")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.ALL)
                .characterEncoding(StandardCharsets.UTF_8).content(payload)).andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    public void testPutResponse201(){
        //TODO: Test get request with response code 200
    }

}
