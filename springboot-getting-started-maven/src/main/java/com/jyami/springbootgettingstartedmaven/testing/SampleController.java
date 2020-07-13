package com.jyami.springbootgettingstartedmaven.testing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jyami on 2020/07/13
 */
@RestController
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @GetMapping("/helloName")
    public String hello() {
        return "hello " + sampleService.getName();
    }

}
