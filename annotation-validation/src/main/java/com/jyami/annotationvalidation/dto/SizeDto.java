package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.Size;

/**
 * Created by jyami on 2020/04/05
 */
@NoArgsConstructor
@Getter
@ToString
@AllArgsConstructor
@Builder
public class SizeDto {
    @Size(max = 5, min = 3)
    private String size;
}
