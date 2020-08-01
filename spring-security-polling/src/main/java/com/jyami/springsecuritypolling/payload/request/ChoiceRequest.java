package com.jyami.springsecuritypolling.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Created by jyami on 2020/07/31
 */
@NoArgsConstructor
@Getter
public class ChoiceRequest {

    @NotBlank
    @Size(max = 40)
    private String text;

}
