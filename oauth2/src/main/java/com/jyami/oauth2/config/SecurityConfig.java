package com.jyami.oauth2.config;

import com.jyami.oauth2.security.handler.LoginSuccessHandler;
import com.jyami.oauth2.security.user.accessUser.AccessUserManager;
import com.jyami.oauth2.security.user.accessUser.AccessUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
@RequiredArgsConstructor
@PropertySource("classpath:application-google.yml")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final LoginSuccessHandler loginSuccessHandler;
    private final AccessUserManager accessUserManager;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/**")
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/login/**").permitAll()
                .anyRequest().authenticated()
                .and().exceptionHandling().authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and().oauth2Login()
//                .defaultSuccessUrl("/loginSuccess")
                .successHandler(loginSuccessHandler)
//                .and().logout().logoutSuccessUrl("/").permitAll()
//                .and().headers().frameOptions().sameOrigin()
                .userInfoEndpoint()
                .userService(new AccessUserService(accessUserManager));

    }
}