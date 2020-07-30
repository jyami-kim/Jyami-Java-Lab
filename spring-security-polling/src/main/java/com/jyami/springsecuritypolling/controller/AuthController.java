package com.jyami.springsecuritypolling.controller;

import com.jyami.springsecuritypolling.payload.request.SignUpReqeust;
import com.jyami.springsecuritypolling.payload.response.ApiResponse;
import com.jyami.springsecuritypolling.payload.response.JwtAuthenticationResponse;
import com.jyami.springsecuritypolling.payload.request.LoginRequest;
import com.jyami.springsecuritypolling.security.JwtTokenProvider;
import com.jyami.springsecuritypolling.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URI;

/**
 * Created by jyami on 2020/07/31
 */
@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final AuthService authService;
    private final JwtTokenProvider tokenProvider;

    @PostMapping("signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsernameOrEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

    @PostMapping("signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpReqeust signUpRequest){
        URI uri = authService.registerUser(signUpRequest);
        return ResponseEntity.created(uri).body(ApiResponse.successRespone("User registered successfully"));
    }
}
