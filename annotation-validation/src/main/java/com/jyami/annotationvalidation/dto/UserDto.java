package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.*;

/**
 * Created by jyami on 2020/03/25
 */
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class UserDto {

    @NotNull
    private String name;

    @Email
    private String email;
}
