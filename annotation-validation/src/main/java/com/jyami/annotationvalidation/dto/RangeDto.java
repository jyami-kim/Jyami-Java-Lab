package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.Negative;
import javax.validation.constraints.NegativeOrZero;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

/**
 * Created by jyami on 2020/04/05
 */
@NoArgsConstructor
@Getter
@ToString
@Builder
@AllArgsConstructor
public class RangeDto {
    @Positive
    private Integer positive;
    @PositiveOrZero
    private Integer positiveOrZero;
    @Negative
    private Integer negative;
    @NegativeOrZero
    private Integer negativeOrZero;
}
