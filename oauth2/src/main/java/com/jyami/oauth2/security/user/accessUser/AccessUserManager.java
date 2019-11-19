package com.jyami.oauth2.security.user.accessUser;

import com.jyami.oauth2.security.user.User;
import com.jyami.oauth2.security.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

@Component
@RequiredArgsConstructor
public class AccessUserManager {
    private static final String SESSION_ATTRIBUTE_NAME_USER_INFO = "USER_INFO";

    private HttpSession httpSession;

    public void saveUserInfo(AccessUser accessUser) {
        httpSession.setAttribute(SESSION_ATTRIBUTE_NAME_USER_INFO, accessUser);
    }

    public AccessUser loadUserInfo() {
        return (AccessUser) httpSession.getAttribute(SESSION_ATTRIBUTE_NAME_USER_INFO);
    }
}
