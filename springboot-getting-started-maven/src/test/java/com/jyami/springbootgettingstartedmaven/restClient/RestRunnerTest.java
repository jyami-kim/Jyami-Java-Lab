package com.jyami.springbootgettingstartedmaven.restClient;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/07/22
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class RestRunnerTest {

    @Autowired
    RestTemplateBuilder restTemplateBuilder;

    @Test
    void test() throws Exception {
        RestTemplate restTemplate = restTemplateBuilder.build();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        //TODO hello 호출
        //security 때문인지 403에러로 인해 RestRunner 클래스를 다른 서버에 띄어서 테스트
        String helloResult = restTemplate.getForObject("http://localhost:9000/sampleRest/hello", String.class);
        System.out.println(helloResult);

        //TODO word 호출
        String wordResult = restTemplate.getForObject("http://localhost:9000/sampleRest/world", String.class);
        System.out.println(wordResult);

        stopWatch.stop();
        System.out.println(stopWatch.prettyPrint());
    }
}