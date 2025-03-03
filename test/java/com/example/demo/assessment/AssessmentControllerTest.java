package com.example.shiksha.controller;

import com.example.shiksha.model.Assessment;
import com.example.shiksha.service.AssessmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AssessmentController.class)
class AssessmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssessmentService assessmentService;

    @Autowired
    private ObjectMapper objectMapper;

    private Assessment assessment;

    @BeforeEach
    void setUp() {
        assessment = new Assessment();
        assessment.setAssessmentId(1L);
        assessment.setType("Quiz");
        assessment.setMaxScore(100);
    }

    @Test
    void testCreateAssessment_Success() throws Exception {
        when(assessmentService.createAssessment(any(Assessment.class), eq(1L), eq(101L)))
                .thenReturn(assessment);

        mockMvc.perform(post("/assessment/createAssessment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(assessment))
                        .param("userId", "1")
                        .param("courseId", "101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.assessmentId").value(1))
                .andExpect(jsonPath("$.type").value("Quiz"));
    }

    @Test
    void testGetAssessmentByCourse_Success() throws Exception {
        when(assessmentService.getAssessmentByCourse(101L)).thenReturn(List.of(assessment));

        mockMvc.perform(get("/assessment/getAssessment")
                        .param("courseId", "101"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1));
    }

    @Test
    void testDeleteAssessment_Success() throws Exception {
        doNothing().when(assessmentService).deleteById(1L, 1L);

        mockMvc.perform(delete("/assessment/deleteAssessment")
                        .param("userId", "1")
                        .param("assessmentId", "1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Assessment deleted successfully"));
    }
}