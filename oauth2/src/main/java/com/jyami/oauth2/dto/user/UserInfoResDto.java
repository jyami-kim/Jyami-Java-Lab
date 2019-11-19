package com.jyami.oauth2.dto.user;

import com.jyami.oauth2.dto.base.BaseTimeEntity;
import com.jyami.oauth2.security.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfoResDto extends BaseTimeEntity {
    private String email;
    private String name;
    private String profile;

    @Builder
    public UserInfoResDto(String email, String name, String profile) {
        this.email = email;
        this.name = name;
        this.profile = profile;
    }

    public static UserInfoResDto toDto(User user){
        return UserInfoResDto.builder()
                .email(user.getEmail())
                .name(user.getName())
                .profile(user.getProfile())
                .build();
    }
}
