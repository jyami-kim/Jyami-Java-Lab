package com.jyami.springsecuritypolling.domain.poll;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

/**
 * Created by jyami on 2020/07/31
 */
@RequiredArgsConstructor
public class VoteRepositoryImpl implements VoteRepositoryCustom {

    private final JPAQueryFactory queryFactory;


}
