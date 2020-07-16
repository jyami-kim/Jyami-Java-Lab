package com.jyami.springbootgettingstartedmaven.webMVC.user;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlHeading1;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


/**
 * Created by jyami on 2020/07/16
 */
@RunWith(SpringRunner.class)
@WebMvcTest(SampleController.class)
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    WebClient webClient;

    @Test
    public void hello() throws Exception {
        // 요청 : /sample/hello
        // 응답
        // - 모델 name: jyami
        // - 뷰이름 : hello
        mockMvc.perform(get("/sample/hello"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(view().name("hello"))
                .andExpect(model().attribute("name", "jyami"));
    }

    @Test
    public void htmlUnit() throws IOException {
        HtmlPage page = webClient.getPage("/sample/hello");
        HtmlHeading1 h1 = page.getFirstByXPath("//h1");
        assertThat(h1.getTextContent()).isEqualTo("jyami");
    }

    @Test
    public void hatoes() throws Exception {
        mockMvc.perform(get("/sample/selfLink"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.self").exists());
    }
}