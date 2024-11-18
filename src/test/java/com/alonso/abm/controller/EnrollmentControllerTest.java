package com.alonso.abm.controller;

import com.alonso.abm.domain.tournament.Enrollment;
import com.alonso.abm.domain.tournament.exception.EnrollmentClosedException;
import com.alonso.abm.service.EnrollService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@WebMvcTest(EnrollmentController.class)
public class EnrollmentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    EnrollService enrollSvc;

    @Test
    @WithMockUser
    public void testPostResponse201() throws Exception {
        when(this.enrollSvc.enrollment(0, 0)).thenReturn(true);

        Enrollment enrollment = new Enrollment(0, 0);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String payload = objectMapper.writeValueAsString(enrollment);

        this.mockMvc.perform(post("/enroll")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.ALL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(payload))
                .andExpect(status().isNoContent());

    }

    @Test
    @WithMockUser
    public void testPutResponse201() throws Exception {
        when(this.enrollSvc.removeEnrollment(0, 0)).thenReturn(true);

        Enrollment enrollment = new Enrollment(0, 0);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String payload = objectMapper.writeValueAsString(enrollment);

        this.mockMvc.perform(put("/enroll")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.ALL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(payload))
                .andExpect(status().isNoContent());

    }

    @Test
    @WithMockUser
    public void testPostResponse423() throws Exception {
        when(this.enrollSvc.removeEnrollment(0, 0)).thenThrow(EnrollmentClosedException.class);

        Enrollment enrollment = new Enrollment(0, 0);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
        String payload = objectMapper.writeValueAsString(enrollment);

        this.mockMvc.perform(put("/enroll")
                        .with(SecurityMockMvcRequestPostProcessors.csrf())
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.ALL)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(payload))
                .andExpect(status().isLocked());

    }
}

