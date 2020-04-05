package com.jyami.annotationvalidation.dto;

import lombok.*;

import javax.validation.constraints.Future;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;

/**
 * Created by jyami on 2020/04/05
 */
@NoArgsConstructor
@Getter
@ToString
@Builder
@AllArgsConstructor
public class TimeDto {
    @Future
    private Date future;
    @FutureOrPresent
    private Date futureOrPresent;
    @Past
    private Date past;
    @PastOrPresent
    private Date pastOrPresent;
}
