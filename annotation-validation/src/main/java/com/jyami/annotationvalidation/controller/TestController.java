package com.jyami.annotationvalidation.controller;

import com.jyami.annotationvalidation.dto.NotDto;
import com.jyami.annotationvalidation.dto.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by jyami on 2020/03/25
 */
@RestController
@Slf4j
public class TestController {

    @PostMapping("/user")
    public ResponseEntity<String> savePost(final @Valid @RequestBody UserDto userDto) {
        log.info(userDto.toString());
        return ResponseEntity.ok().body("postDto 객체 검증 성공");
    }

    @PostMapping("/test1")
    public ResponseEntity<String> test1(final @Valid @RequestBody NotDto notDto) {
        log.info(notDto.toString());
        return ResponseEntity.ok().body("not관련 객체 검증 성공");
    }


    @GetMapping("/user")
    public ResponseEntity<String> getUser(final @Valid @NotNull @RequestParam(required = false) String userCode) {
        log.info(userCode);
        return ResponseEntity.ok().body("userCode String 검증 성공");
    }

}
