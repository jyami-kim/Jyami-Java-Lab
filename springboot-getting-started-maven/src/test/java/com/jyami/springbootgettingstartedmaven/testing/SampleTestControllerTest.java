package com.jyami.springbootgettingstartedmaven.testing;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.rule.OutputCapture;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by jyami on 2020/07/13
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SampleTestController.class)
public class SampleTestControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    SampleService sampleService;

    @Rule
    public OutputCapture outputCapture = new OutputCapture();


    @Test
    public void name() throws Exception {

        when(sampleService.getName()).thenReturn("minjeong");

        mockMvc.perform(get("/helloName"))
                .andExpect(status().isOk())
                .andExpect(content().string("hello minjeong"))
                .andDo(print());

        assertThat(outputCapture.toString())
                .contains("jyami minjeong")
                .contains("hello~~");
    }
}