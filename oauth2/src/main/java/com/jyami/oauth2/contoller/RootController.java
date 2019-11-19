package com.jyami.oauth2.contoller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class RootController {
    @GetMapping("")
    public ResponseEntity<String> testing() {
        return new ResponseEntity<>("testing success", HttpStatus.OK);
    }
}
