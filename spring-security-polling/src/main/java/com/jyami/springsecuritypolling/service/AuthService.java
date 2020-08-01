package com.jyami.springsecuritypolling.service;

import com.jyami.springsecuritypolling.exception.AppException;
import com.jyami.springsecuritypolling.exception.BadRequestException;
import com.jyami.springsecuritypolling.payload.request.SignUpReqeust;
import com.jyami.springsecuritypolling.domain.user.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Collections;

/**
 * Created by jyami on 2020/07/31
 */
@Service
@AllArgsConstructor
@Slf4j
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Transactional
    public URI registerUser(SignUpReqeust signUpRequest) {
        if (userRepository.existsByEmailOrUsername(signUpRequest.getEmail(), signUpRequest.getUsername())) {
            log.debug("user is already taken!");
            throw new BadRequestException("user is already taken!");
        }

        User user = signUpRequest.toEntity();
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
                .orElseThrow(() -> new AppException("User Role not set."));
        user.setRoles(Collections.singleton(userRole));

        User save = userRepository.save(user);

        return ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/api/users/{username}")
                .buildAndExpand(save.getUsername()).toUri();

    }
}
