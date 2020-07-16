package com.jyami.springbootgettingstartedmaven.testing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyami on 2020/07/13
 */
@RestController
public class SampleTestController {

    Logger log = LoggerFactory.getLogger(SampleTestController.class);

    @Autowired
    private SampleService sampleService;

    @GetMapping("/helloName")
    public String hello() {
        log.info("jyami minjeong");
        System.out.println("hello~~");
        return "hello " + sampleService.getName();
    }

}
