package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.AssertTrue;

/**
 * Created by jyami on 2020/04/05
 */
@NoArgsConstructor
@Getter
@ToString
@Builder
@AllArgsConstructor
public class BooleanDto {
    @AssertTrue
    private boolean assertTrue;
    @AssertFalse
    private boolean assertFalse;
}
