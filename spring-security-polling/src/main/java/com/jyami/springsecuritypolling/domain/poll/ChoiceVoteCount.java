package com.jyami.springsecuritypolling.domain.poll;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jyami on 2020/07/31
 */
@AllArgsConstructor
@Getter
public class ChoiceVoteCount {
    private Long choiceId;
    private Long voteCount;
}
