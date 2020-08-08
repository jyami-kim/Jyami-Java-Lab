package com.jyami.springsecuritypolling.domain.poll;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Created by jyami on 2020/08/08
 */
@AllArgsConstructor
@Getter
public class ChoiceVoteMap {
    private Long choiceId;
    private Long voteId;
}
