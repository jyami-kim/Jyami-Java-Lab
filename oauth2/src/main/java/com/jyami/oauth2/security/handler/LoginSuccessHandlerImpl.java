package com.jyami.oauth2.security.handler;

import com.jyami.oauth2.security.user.UserService;
import com.jyami.oauth2.security.user.accessUser.AccessUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@Component
public class LoginSuccessHandlerImpl implements LoginSuccessHandler {

    private static final String SESSION_ATTRIBUTE_NAME_USER_INFO = "USER_INFO";
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        AccessUser accessUser = (AccessUser)request.getSession().getAttribute(SESSION_ATTRIBUTE_NAME_USER_INFO);
        if (userService.findByUserEmail(accessUser.getEmail()) == null) {
            response.sendRedirect("/registration");
            return;
        }
        response.sendRedirect("/page");
    }
}
