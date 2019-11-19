package com.jyami.oauth2.security.user;

import com.jyami.oauth2.dto.user.UserInfoResDto;
import com.jyami.oauth2.security.user.accessUser.AccessUser;
import com.jyami.oauth2.security.user.accessUser.AccessUserManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class UserService {

    private final AccessUserManager accessUserManager;
    private final UserRepository userRepository;

    public User findByUserEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public UserInfoResDto saveUser() {
        AccessUser accessUser = accessUserManager.loadUserInfo();
        User user = accessUser.toCreateUserEntity();
        userRepository.save(user);
        return UserInfoResDto.toDto(user);
    }

    public UserInfoResDto getUserInfoFromAccessUser() {
        AccessUser accessUser = accessUserManager.loadUserInfo();
        User user = userRepository.findByEmail(accessUser.getEmail());
        return UserInfoResDto.toDto(user);
    }
}
