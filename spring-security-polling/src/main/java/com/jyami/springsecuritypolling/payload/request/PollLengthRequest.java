package com.jyami.springsecuritypolling.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * Created by jyami on 2020/07/31
 */
@NoArgsConstructor
@Getter
public class PollLengthRequest {
    @NotNull
    @Max(7)
    private int days;

    @NotNull
    @Max(23)
    private int hours;
}
