package com.example.shiksha.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.shiksha.model.Enrollment;
import com.example.shiksha.service.EnrollmentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(EnrollmentController.class)
class EnrollmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EnrollmentService enrollmentService;

    private Enrollment enrollment;

    @Autowired
    private ObjectMapper objectMapper; // Used to convert Java objects to JSON

    @BeforeEach
    void setUp() {
        enrollment = new Enrollment();
        enrollment.setEnrollmentId(1L);
    }

    @Test
    void testEnrollStudent() throws Exception {
        when(enrollmentService.enrollStudent(1L, 1L)).thenReturn(enrollment);

        mockMvc.perform(post("/enroll/1/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testGetAllEnrollments() throws Exception {
        mockMvc.perform(get("/enrollments")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void testUpdateProgress() throws Exception {
        when(enrollmentService.updateProgress(1L, 1L, 50)).thenReturn("Progress updated: 50%");

        mockMvc.perform(put("/updateProgress/1/1?progress=50")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Progress updated: 50%"));
    }
}