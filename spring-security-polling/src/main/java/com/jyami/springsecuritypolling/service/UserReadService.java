package com.jyami.springsecuritypolling.service;

import com.jyami.springsecuritypolling.domain.user.User;
import com.jyami.springsecuritypolling.domain.user.UserRepository;
import com.jyami.springsecuritypolling.exception.ResourceNotFoundException;
import com.jyami.springsecuritypolling.payload.response.UserProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by jyami on 2020/08/03
 */
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserReadService {

    private final PollReadService pollReadService;
    private final UserRepository userRepository;

    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean existsByEmailOrUsername(String email, String username){
        return userRepository.existsByEmailOrUsername(email, username);
    }

    public UserProfileResponse getUserProfileWithUsername(String username){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        long pollCount = pollReadService.getUserIdPollCount(user.getId());
        long voteCount = pollReadService.getUserIdVoteCount(user.getId());

        return UserProfileResponse.convertFromUserData(user, pollCount, voteCount);
    }


}
