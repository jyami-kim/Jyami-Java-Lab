package com.jyami.oauth2.contoller;

import com.jyami.oauth2.dto.user.UserInfoResDto;
import com.jyami.oauth2.security.user.User;
import com.jyami.oauth2.security.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("user")
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping()
    public ResponseEntity<UserInfoResDto> sighUp() {
        UserInfoResDto userInfoResDto= userService.saveUser();
        log.info("회원가입: {}", userInfoResDto.getEmail());
        return new ResponseEntity<>(userInfoResDto, HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<UserInfoResDto> findUserInfoFromAccessUser() {
        UserInfoResDto userInfoResDto= userService.getUserInfoFromAccessUser();
        return new ResponseEntity<>(userInfoResDto, HttpStatus.OK);
    }
}
