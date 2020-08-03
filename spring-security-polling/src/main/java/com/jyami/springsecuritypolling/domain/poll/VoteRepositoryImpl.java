package com.jyami.springsecuritypolling.domain.poll;

import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.jyami.springsecuritypolling.domain.poll.QVote.vote;

/**
 * Created by jyami on 2020/07/31
 */
public class VoteRepositoryImpl extends QuerydslRepositorySupport implements VoteRepositoryCustom {

    public VoteRepositoryImpl(Class<?> domainClass) {
        super(Vote.class);
    }

}
