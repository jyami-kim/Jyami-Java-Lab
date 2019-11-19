package com.jyami.oauth2.security.user.accessUser;

import com.jyami.oauth2.security.user.User;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.util.StringUtils;

import java.util.Map;

@Getter
public class AccessUser {

    private String name;
    private String email;
    private String picture;

    @Builder
    public AccessUser(String name, String email, String picture) {
        this.name = name;
        this.email = email;
        this.picture = picture;
    }

    public User toCreateUserEntity() {
        return User.builder()
                .email(this.email)
                .name(this.name)
                .profile(this.picture)
                .build();
    }

    public static AccessUser of(OAuth2User oAuth2User) {
        Map<String, Object> attributes = oAuth2User.getAttributes();
        String email = (String) attributes.get("email");
        String name = (String) attributes.get("name");
        String picture = (String) attributes.get("picture");

        validate(email, name);

        return AccessUser.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .build();
    }

    private static void validate(String email, String name) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(name)) {
            throw new OAuth2AuthenticationException(new OAuth2Error(OAuth2ErrorCodes.ACCESS_DENIED));
        }
    }
}
