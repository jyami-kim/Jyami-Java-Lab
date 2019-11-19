package com.jyami.oauth2.security.user.accessUser;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
public class AccessUserService extends DefaultOAuth2UserService {

    private final AccessUserManager accessUserManager;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oauth2User = super.loadUser(userRequest);
        AccessUser accessUser = AccessUser.of(oauth2User);
        accessUserManager.saveUserInfo(accessUser);
        return oauth2User;
    }
}
