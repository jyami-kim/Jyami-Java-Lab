package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.Digits;

/**
 * Created by jyami on 2020/04/05
 */
@NoArgsConstructor
@Getter
@ToString
@Builder
@AllArgsConstructor
public class DigitsDto {
    @Digits(integer = 5, fraction = 5)
    private Integer digits;
}
