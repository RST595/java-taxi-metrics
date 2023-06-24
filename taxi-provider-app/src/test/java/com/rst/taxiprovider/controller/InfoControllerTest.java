package com.rst.taxiprovider.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rst.metrics.dto.Coordinate;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class InfoControllerTest {
    @Autowired
    private MockMvc mockMvc;
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Test
    void getClasses() throws Exception {
        // GIVEN | WHEN | THEN
        this.mockMvc.perform(get("/info/classes"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ECONOMY")));
    }

    @RepeatedTest(5)
    void getLength() throws Exception {
        // GIVEN
        Coordinate start = new Coordinate("50.189683", "53.222579");
        Coordinate finish = new Coordinate("50.102834", "53.199131");

        // WHEN | THEN
        this.mockMvc.perform(post("/info/distance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(OBJECT_MAPPER.writeValueAsString(List.of(start, finish))))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("0.09")));
    }
}
