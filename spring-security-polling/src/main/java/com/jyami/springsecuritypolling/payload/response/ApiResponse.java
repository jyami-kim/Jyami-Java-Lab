package com.jyami.springsecuritypolling.payload.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jyami on 2020/07/31
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class ApiResponse {
    private boolean success;
    private String message;

    public static ApiResponse failRespone(String message){
        return new ApiResponse(false, message);
    }

    public static ApiResponse successRespone(String message){
        return new ApiResponse(true, message);
    }
}
