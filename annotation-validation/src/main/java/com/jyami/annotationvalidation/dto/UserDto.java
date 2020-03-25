package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jyami on 2020/03/25
 */
@ToString
@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDto {

    @NotNull
    @Builder.Default
    private String name = "jyami";
    @Email
    @Builder.Default
    private String email = "mor2222@naver.com";
    @Pattern(regexp = "^(19|20)\\d{2}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[0-1])$")
    @Builder.Default
    private String birthDay = "1998-05-30";
    @Negative
    @Builder.Default
    private int negativeNumber = -1;
    @Positive
    @Builder.Default
    private int positiveNumber = 1;
    @PositiveOrZero
    @Builder.Default
    private int positiveNumberOrZero = 0;
//    @Size(min = 3, max = 10 )
//    @Builder.Default
//    private List<String> hello = new ArrayList<>();
    @Builder.Default
    @Size(min = 8, max = 20 )
    private String password = "passwordExample";


}
