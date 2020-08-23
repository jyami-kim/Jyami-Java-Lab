package com.jyami.springsecuritypolling.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by jyami on 2020/07/31
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class PollResponse {
    private Long id;
    private String question;
    private List<ChoiceResponse> choices;
    private UserSummaryResponse createdBy;
    private LocalDateTime creationDateTime;
    private LocalDateTime expirationDateTime;
    private boolean isExpired;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long selectedChoice;
    private long totalVotes;
}
