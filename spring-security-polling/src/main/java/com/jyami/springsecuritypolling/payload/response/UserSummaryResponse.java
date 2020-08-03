package com.jyami.springsecuritypolling.payload.response;

import com.jyami.springsecuritypolling.security.UserPrincipal;
import lombok.*;

/**
 * Created by jyami on 2020/07/31
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserSummaryResponse {
    private Long id;
    private String username;
    private String name;

    public static UserSummaryResponse convertFromCurrentUser(UserPrincipal currentUser){
        return UserSummaryResponse.builder()
                .id(currentUser.getId())
                .username(currentUser.getUsername())
                .name(currentUser.getName())
                .build();
    }

}
