package com.jyami.springsecuritypolling.controller;

import com.jyami.springsecuritypolling.exception.BadRequestException;
import com.jyami.springsecuritypolling.payload.response.*;
import com.jyami.springsecuritypolling.security.CurrentUser;
import com.jyami.springsecuritypolling.security.UserPrincipal;
import com.jyami.springsecuritypolling.service.PollReadService;
import com.jyami.springsecuritypolling.service.UserReadService;
import com.jyami.springsecuritypolling.util.AppConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


/**
 * Created by jyami on 2020/08/02
 */
@RestController
@RequestMapping("api")
@RequiredArgsConstructor
public class UserController {

    private final UserReadService userReadService;
    private final PollReadService pollReadService;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public UserSummaryResponse getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        return UserSummaryResponse.convertFromCurrentUser(currentUser);
    }

    @GetMapping("/user/check/username")
    public UserIdentityAvailabilityResponse checkUsernameAvailability(@RequestParam(value = "username") String username) {
        return new UserIdentityAvailabilityResponse(!userReadService.existsByUsername(username));
    }

    @GetMapping("/user/check/email")
    public UserIdentityAvailabilityResponse checkEmailAvailability(@RequestParam(value = "email") String email) {
        return new UserIdentityAvailabilityResponse(!userReadService.existsByEmail(email));
    }

    @GetMapping("/user/")
    public UserIdentityAvailabilityResponse checkUsernameAndEmailAvailability(@RequestParam(value = "email") String email,
                                                                              @RequestParam(value = "username") String username) {
        return new UserIdentityAvailabilityResponse(!userReadService.existsByEmailOrUsername(email, username));
    }

    @GetMapping("/users/{username}")
    public UserProfileResponse getUserProfile(@PathVariable(value = "username") String username) {
        return userReadService.getUserProfileWithUsername(username);
    }

    @GetMapping("/users/{username}/polls")
    public PagedResponse<PollResponse> getPollsCreatedBy(@PathVariable(value = "username") String username,
                                                         @CurrentUser UserPrincipal currentUser,
                                                         @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                         @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        validatePageNumberAndSize(page, size);
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        return pollReadService.getPollsCreatedBy(username, currentUser, pageable);
    }

    @GetMapping("/users/{username}/votes")
    public PagedResponse<PollResponse> getPollsVotedBy(@PathVariable(value = "username") String username,
                                                       @CurrentUser UserPrincipal currentUser,
                                                       @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
                                                       @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        validatePageNumberAndSize(page, size);
        return pollService.getPollsVotedBy(username, currentUser, page, size);
    }

    private void validatePageNumberAndSize(int page, int size) {
        if (page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }
        if (size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }


}
