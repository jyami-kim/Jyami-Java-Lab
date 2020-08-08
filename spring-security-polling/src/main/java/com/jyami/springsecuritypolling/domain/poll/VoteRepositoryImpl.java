package com.jyami.springsecuritypolling.domain.poll;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

import static com.jyami.springsecuritypolling.domain.poll.QVote.vote;

/**
 * Created by jyami on 2020/07/31
 */
public class VoteRepositoryImpl extends QuerydslRepositorySupport implements VoteRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public VoteRepositoryImpl(JPAQueryFactory queryFactory) {
        super(Vote.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<ChoiceVoteCount> countByPollIdInGroupByChoiceId(List<Long> pollIds) {
        return queryFactory.select(Projections.fields(ChoiceVoteCount.class,
                vote.choice.id.as("choiceId"),
                vote.count()).as("voteCount"))
                .where(vote.poll.id.in(pollIds))
                .groupBy(vote.choice.id)
                .fetch();
    }
}
