package com.jyami.springsecuritypolling.service;

import com.jyami.springsecuritypolling.domain.poll.*;
import com.jyami.springsecuritypolling.domain.user.User;
import com.jyami.springsecuritypolling.domain.user.UserRepository;
import com.jyami.springsecuritypolling.exception.ResourceNotFoundException;
import com.jyami.springsecuritypolling.payload.response.PagedResponse;
import com.jyami.springsecuritypolling.payload.response.PollResponse;
import com.jyami.springsecuritypolling.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Created by jyami on 2020/08/02
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PollReadService {

    private final PollRepository pollRepository;
    private final VoteRepository voteRepository;
    private final UserRepository userRepository;

    public PagedResponse<PollResponse> getAllPolls(UserPrincipal currentUser, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.Direction.DESC, "createdAt");
        Page<Poll> polls = pollRepository.findAll(pageable);

        return null;
    }

    public long getUserIdPollCount(long userID) {
        return pollRepository.countByCreatedBy(userID);
    }

    public long getUserIdVoteCount(long userId) {
        return voteRepository.countByUserId(userId);
    }

    public PagedResponse<?> getPollsCreatedBy(String username, UserPrincipal currentUser, Pageable pageable) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // Retrieve all polls created by the given username
        Page<Poll> polls = pollRepository.findByCreatedBy(user.getId(), pageable);
        if (polls.getNumberOfElements() == 0) {
            return PagedResponse.getEmptyPages(polls);
        }

        // Map Polls to PollResponses containing vote counts and poll creator details
        List<Long> pollIds = polls.map(Poll::getId).getContent();
        List<ChoiceVoteCount> choiceVoteCountMap = voteRepository.countByPollIdInGroupByChoiceId(pollIds);
        List<ChoiceVoteMap> pollUserVoteMap = voteRepository.findByUserIdAndPollIdIn(currentUser.getId(), pollIds)
                .stream().map(vote -> new ChoiceVoteMap(vote.getId(), vote.getChoice().getId()))
                .collect(Collectors.toList());
        List<PollResponse> pollResponses = polls.map(poll -> {
            return ModelMapper.mapPollToPollResponse(poll, choiceVoteCountMap, user, pollUserVoteMap == null ? null : pollUserVoteMap.getOrDefault(poll.getId(), null));
        }).getContent();
        return PagedResponse.of(pollResponses, polls);

    }



}
