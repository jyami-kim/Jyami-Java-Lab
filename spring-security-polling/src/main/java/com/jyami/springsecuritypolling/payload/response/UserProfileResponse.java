package com.jyami.springsecuritypolling.payload.response;

import com.jyami.springsecuritypolling.domain.user.User;
import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime joinedAt;
    private long pollCount;
    private long voteCount;

    public static UserProfileResponse convertFromUserData(User user, long pollCount, long voteCount){
        return UserProfileResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .joinedAt(user.getCreatedDate())
                .pollCount(pollCount)
                .voteCount(voteCount)
                .build();
    }
}
