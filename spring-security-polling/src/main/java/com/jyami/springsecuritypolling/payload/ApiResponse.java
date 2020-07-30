package com.jyami.springsecuritypolling.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jyami on 2020/07/31
 */
@AllArgsConstructor
@Getter
public class ApiResponse {
    private boolean success;
    private String message;
}
