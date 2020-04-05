package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.*;

/**
 * Created by jyami on 2020/03/25
 */
@NoArgsConstructor
@Getter
@ToString
@Builder
@AllArgsConstructor
public class NotDto {
    @NotNull
    private String notNull;
    @NotEmpty
    private String notEmpty;
    @NotBlank
    private String notBlank;
}
