package com.jyami.springsecuritypolling.service;

import com.jyami.springsecuritypolling.domain.poll.Poll;
import com.jyami.springsecuritypolling.domain.poll.PollRepository;
import com.jyami.springsecuritypolling.domain.poll.VoteRepository;
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

    public PagedResponse<PollResponse> getPollsCreatedBy(String username, UserPrincipal currentUser, Pageable pageable) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "username", username));

        // Retrieve all polls created by the given username
        Page<Poll> polls = pollRepository.findByCreatedBy(user.getId(), pageable);


        // Map Polls to PollResponses containing vote counts and poll creator details
        List<Long> pollIds = polls.map(Poll::getId).getContent();
        Map<Long, Long> choiceVoteCountMap = getChoiceVoteCountMap(pollIds);
        Map<Long, Long> pollUserVoteMap = getPollUserVoteMap(currentUser, pollIds);
        List<PollResponse> pollResponses = polls.map(poll -> {
            return ModelMapper.mapPollToPollResponse(poll, choiceVoteCountMap, user, pollUserVoteMap == null ? null : pollUserVoteMap.getOrDefault(poll.getId(), null));
        }).getContent();
        return new PagedResponse<>(pollResponses, polls.getNumber(), polls.getSize(), polls.getTotalElements(), polls.getTotalPages(), polls.isLast());
    }


}
