package com.jyami.annotationvalidation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * Created by jyami on 2020/03/25
 */
@NoArgsConstructor
@Getter
@ToString
public class NotDto {
    @NotNull
    private String notNull;
    @NotEmpty
    private String notEmpty;
    @NotBlank
    private String notBlank;
}
