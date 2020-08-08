package com.jyami.springsecuritypolling.domain.poll;

import java.util.List;

/**
 * Created by jyami on 2020/07/31
 */
public interface VoteRepositoryCustom {
    List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(List<Long> pollIds);
}
