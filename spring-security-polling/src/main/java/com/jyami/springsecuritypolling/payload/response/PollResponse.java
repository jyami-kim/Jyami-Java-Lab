package com.jyami.springsecuritypolling.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.time.Instant;
import java.util.List;

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
    private Instant creationDateTime;
    private Instant expirationDateTime;
    private boolean isExpired;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long selectedChoice;
    private long totalVotes;
}
