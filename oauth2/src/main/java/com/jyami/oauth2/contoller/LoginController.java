package com.jyami.oauth2.contoller;

import com.jyami.oauth2.security.user.accessUser.AccessUser;
import com.jyami.oauth2.security.user.accessUser.AccessUserManager;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("login")
@RequiredArgsConstructor
public class LoginController {

    private final AccessUserManager accessUserManager;

    @GetMapping("/user")
    public AccessUser home() {
        return accessUserManager.loadUserInfo();
    }
}
