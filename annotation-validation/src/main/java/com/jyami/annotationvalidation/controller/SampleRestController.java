package com.jyami.annotationvalidation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sampleRest")
public class SampleRestController {

    @GetMapping("hello")
    public String hello() throws InterruptedException {
        Thread.sleep(5000);
        return "hello";
    }

    @GetMapping("world")
    public String world() throws InterruptedException {
        Thread.sleep(3000);
        return "world";
    }

}
