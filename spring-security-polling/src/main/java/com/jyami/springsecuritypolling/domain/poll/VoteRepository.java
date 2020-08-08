package com.jyami.springsecuritypolling.domain.poll;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by jyami on 2020/07/31
 */
public interface VoteRepository extends JpaRepository<Vote, Long>, VoteRepositoryCustom {

    long countByUserId(final long userId);

    List<Vote> findByUserIdAndPollIdIn(final Long user_id, final Collection<Long> poll_id);

}
