package com.jyami.springbootgettingstartedmaven.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

/**
 * Created by jyami on 2020/07/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SampleControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private SampleService sampleService;

    @Test
    public void name() {

        when(sampleService.getName()).thenReturn("minjeong");

        String forObject = testRestTemplate.getForObject("/helloName", String.class);
        assertThat(forObject).isEqualTo("hello minjeong");

    }
}