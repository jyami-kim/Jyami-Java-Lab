package com.jyami.springsecuritypolling.controller;

import com.jyami.springsecuritypolling.service.PollReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * Created by jyami on 2020/07/31
 */
@RestController
@RequestMapping("api/poll")
@RequiredArgsConstructor
public class PollController {

    private final PollReadService pollReadService;
//
//    @GetMapping
//    public PageResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
//                                               @RequestParam(value = "page",
//                                                       defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
//                                               @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
//        return pollService.getAllPolls(currentUser, page, size);
//    }
//
//    @PostMapping
//    @PreAuthorize("hasRole('USER')")
//    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {
//        Poll poll = pollService.createPoll(pollRequest);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{pollId}").buildAndExpand(poll.getId()).toUri();
//        return ResponseEntity.created(location).body(new ApiResponse(true, "Poll Created Successfully"));
//    }

//    @GetMapping("/{pollId}")
//    public PollResponse getPollById(@CurrentUser UserPrincipal currentUser,
//                                    @PathVariable Long pollId) {
//        return pollService.getPollById(pollId, currentUser);
//    }
//
//    @PostMapping("/{pollId}/votes")
//    @PreAuthorize("hasRole('USER')")
//    public PollResponse castVote(@CurrentUser UserPrincipal currentUser,
//                                 @PathVariable Long pollId,
//                                 @Valid @RequestBody VoteRequest voteRequest) {
//        return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, currentUser);
//    }


}
