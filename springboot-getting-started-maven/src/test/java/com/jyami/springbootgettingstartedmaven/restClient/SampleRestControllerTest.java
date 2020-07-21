package com.jyami.springbootgettingstartedmaven.restClient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/07/22
 */
@SpringBootTest
@ExtendWith(SpringExtension.class)
class SampleRestControllerTest {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    void name() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //TODO hello 호출
        String helloResult = restTemplate.getForObject("http://localhost:9000/sampleRest/hello", String.class);
        System.out.println(helloResult);

        //TODO word 호출
        String wordResult = restTemplate.getForObject("http://localhost:9000/sampleRest/world", String.class);
        System.out.println(wordResult);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}