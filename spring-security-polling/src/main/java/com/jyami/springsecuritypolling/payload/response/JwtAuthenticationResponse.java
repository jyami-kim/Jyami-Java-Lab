package com.jyami.springsecuritypolling.payload.response;

import lombok.Getter;

/**
 * Created by jyami on 2020/07/31
 */
@Getter
public class JwtAuthenticationResponse {
    private String accessToken;
    private String tokenType = "Bearer";

    public JwtAuthenticationResponse(String accessToken) {
        this.accessToken = accessToken;
    }
}