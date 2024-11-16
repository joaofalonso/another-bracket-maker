package com.alonso.abm.controller;

import com.alonso.abm.domain.tournament.*;
import com.alonso.abm.service.TournamentService;
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

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;

@WebMvcTest(TournamentController.class)
public class TournamentControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TournamentService tournamentService;

    private Tournament t;
    @BeforeEach
    public void setup(){
        TournamentBuilder tournamentBuilder = new TournamentBuilder();
        t  = tournamentBuilder.Name("Battle Royalle")
                .startDate(LocalDateTime.now().plusDays(1))
                .finalDate(LocalDateTime.now().plusDays(3))
                .build();
        t.setId(1L);
    }
    @Test
    @WithMockUser
    public void testGetResponse200() throws Exception {
        when(tournamentService.getById(t.getId())).thenReturn(t);
        this.mockMvc.perform(get("/tournament/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE));

    }

    @Test
    @WithMockUser
    public void testPostResponse201() throws Exception {
        CreateTournament createTournament = new CreateTournament(t.getName(), t.getStartDay(), t.getFinalDay());
        when(tournamentService.save(createTournament)).thenReturn(t);


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String payload = objectMapper.writeValueAsString(createTournament);

        this.mockMvc.perform(post("/tournament")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.ALL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(payload))
                .andExpect(status().isCreated())
                .andExpect(header().stringValues("location","/tournament/1"));


    }

    @Test
    @WithMockUser
    public void testPutResponse200() throws Exception {
        UpdateTournament updateTournament = new UpdateTournament(t.getId(), t.getName(), t.getStartDay(), t.getFinalDay(), TournamentState.RUNNING);
        when(tournamentService.updateTournament(updateTournament)).thenReturn(true);


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String payload = objectMapper.writeValueAsString(updateTournament);

        this.mockMvc.perform(put("/tournament")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.ALL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(payload))
                .andExpect(status().isOk())
                .andExpect(header().stringValues("location","/tournament/1"));


    }

    @Test
    @WithMockUser
    public void testDeleteResponse204() throws Exception {
        when(tournamentService.delete(1L)).thenReturn(true);
        this.mockMvc.perform(delete("/tournament/1")
                .with(SecurityMockMvcRequestPostProcessors.csrf())).andExpect(status().isNoContent());
    }

}
