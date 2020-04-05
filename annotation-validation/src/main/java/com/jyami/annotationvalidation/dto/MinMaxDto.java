package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.Max;
import java.math.BigInteger;

/**
 * Created by jyami on 2020/04/05
 */
@NoArgsConstructor
@Getter
@ToString
@Builder
@AllArgsConstructor
public class MinMaxDto {
    @DecimalMax(value = "1000000000")
    private BigInteger decimalMax;
    @DecimalMax(value = "1")
    private BigInteger decimalMin;
    @Max(value = 1000)
    private Integer max;
    @Max(value = 1)
    private Integer min;
}
