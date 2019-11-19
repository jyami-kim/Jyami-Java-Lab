package com.jyami.oauth2.contoller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("test")
public class TestController {
    @GetMapping()
    public ResponseEntity<String> getTest() {
        return new ResponseEntity<>("test", HttpStatus.OK);
    }
}
