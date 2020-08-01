package com.jyami.springsecuritypolling.payload.response;

import lombok.*;

import java.time.Instant;

/**
 * Created by jyami on 2020/07/31
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserProfileResponse {
    private Long id;
    private String username;
    private String name;
    private Instant joinedAt;
    private long pollCount;
    private long voteCount;
}
