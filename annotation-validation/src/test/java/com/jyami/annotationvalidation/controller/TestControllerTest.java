package com.jyami.annotationvalidation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jyami.annotationvalidation.dto.UserDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * Created by jyami on 2020/03/25
 */
@SpringBootTest
@AutoConfigureMockMvc
class TestControllerTest {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    @Test
    @DisplayName("@NotNull 에러 테스트")
    void isValidObject() throws Exception {

        UserDto userDto = UserDto.builder()
                .email("mjung1798@gmail.com")
                .build();

        String userDtoJsonString = objectMapper.writeValueAsString(userDto);

        mockMvc.perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .content(userDtoJsonString))
                .andExpect(status().isBadRequest());
    }
}