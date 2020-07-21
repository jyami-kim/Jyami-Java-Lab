package com.jyami.springbootgettingstartedmaven.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/07/21
 */
@Component
public class WebAccountSettingConfig implements ApplicationRunner {

    @Autowired
    AccountSecurityService accountSecurityService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
//        AccountSecurity jyami = accountSecurityService.createAccount("jyami", "1234");
//        System.out.println("username: " + jyami.getUsername() + " password: " + jyami.getPassword());
    }
}
