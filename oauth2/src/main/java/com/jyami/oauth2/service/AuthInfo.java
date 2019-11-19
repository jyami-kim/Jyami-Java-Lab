package com.jyami.oauth2.service;


import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class AuthInfo {
    private String clientId;
    private String clientSecret;
}
