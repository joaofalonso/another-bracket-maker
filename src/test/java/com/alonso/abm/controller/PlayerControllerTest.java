package com.alonso.abm.controller;


import com.alonso.abm.domain.player.CreatePlayer;
import com.alonso.abm.domain.player.Player;
import com.alonso.abm.domain.player.PlayerBuilder;
import com.alonso.abm.domain.player.UpdatePlayer;
import com.alonso.abm.domain.player.exception.InvalidEmailException;
import com.alonso.abm.domain.player.exception.PlayerNotFoundException;
import com.alonso.abm.service.PlayerService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;


import java.nio.charset.StandardCharsets;
import java.time.LocalDate;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));
    }

    @Test
    @WithMockUser
    public void testGetResponse404() throws Exception{
        when(playerService.getById(99L)).thenThrow(PlayerNotFoundException.class);
        this.mockMvc.perform(get("/player/99"))
                .andExpect(status().isNotFound());
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
                .characterEncoding(StandardCharsets.UTF_8)
                .content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("location","/player/1"));
    }

    @Test
    @WithMockUser
    public void testPostResponse400() throws Exception {
        CreatePlayer createPlayer = new CreatePlayer("teste",
                "teste",
                "teste@test.", // not really necessary
                "testNick",
                LocalDate.now().minusYears(19));

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String payload = objectMapper.writeValueAsString(createPlayer);

        when(playerService.save(createPlayer)).thenThrow(InvalidEmailException.class);
        this.mockMvc.perform(post("/player")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.ALL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(payload))
                .andExpect(status().isBadRequest());

    }

    @Test
    @WithMockUser
    public void testPutResponse200() throws Exception {
        UpdatePlayer updatePlayer = new UpdatePlayer(player.getId(), player.getFirstName(), player.getLastName(), player.getEmail(), player.getDateOfBirth());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String payload = objectMapper.writeValueAsString(updatePlayer);

        when(playerService.updatePlayer(updatePlayer)).thenReturn(true);
        this.mockMvc.perform(put("/player")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.ALL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("location","/player/1"));
    }

    @Test
    @WithMockUser
    public void testDeleteResponse204() throws Exception {
        when(this.playerService.delete(1L)).thenReturn(true);
        this.mockMvc.perform(delete("/player/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf()))
                .andExpect(status().isNoContent());
    }

}
