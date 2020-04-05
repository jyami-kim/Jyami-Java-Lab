package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.Pattern;

/**
 * Created by jyami on 2020/04/05
 */
@NoArgsConstructor
@Getter
@ToString
@Builder
@AllArgsConstructor
public class PatternDto {
    //yyyy-mm-dd 형태를 가지는 패턴 조사
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$")
    private String pattern;
}
