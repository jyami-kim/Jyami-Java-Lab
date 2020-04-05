package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.Email;

/**
 * Created by jyami on 2020/04/05
 */
@NoArgsConstructor
@Getter
@ToString
@Builder
@AllArgsConstructor
public class EmailDto {
    @Email
    private String email;
}
