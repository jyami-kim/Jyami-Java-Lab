package com.jyami.springsecuritypolling.controller;

import com.jyami.springsecuritypolling.exception.BadRequestException;
import com.jyami.springsecuritypolling.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by jyami on 2020/07/31
 */
@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse> badRequestException(BadRequestException e) {
        return ResponseEntity.badRequest().body(ApiResponse.failRespone(e.getMessage()));
    }


}
