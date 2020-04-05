package com.jyami.annotationvalidation.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class NotDtoTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("NotDto 에러 테스트 : '' ")
    void notDto1() throws Exception {

        NotDto notDto = NotDto.builder()
                .notBlank("")
                .notNull("")
                .notEmpty("")
                .build();

        String jsonString = objectMapper.writeValueAsString(notDto);

        mockMvc.perform(post("/test1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    @DisplayName("NotDto 에러 테스트 : null ")
    void notDto2() throws Exception {

        NotDto notDto = NotDto.builder()
                .build();

        String jsonString = objectMapper.writeValueAsString(notDto);

        mockMvc.perform(post("/test1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    @DisplayName("NotDto 에러 테스트 : ' ' ")
    void notDto3() throws Exception {

        NotDto notDto = NotDto.builder()
                .notBlank(" ")
                .notNull(" ")
                .notEmpty(" ")
                .build();

        String jsonString = objectMapper.writeValueAsString(notDto);

        mockMvc.perform(post("/test1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}