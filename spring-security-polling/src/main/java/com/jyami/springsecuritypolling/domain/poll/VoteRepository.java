package com.jyami.springsecuritypolling.domain.poll;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyami on 2020/07/31
 */
public interface VoteRepository extends JpaRepository<Vote, Long>, VoteRepositoryCustom {
}
