package com.jyami.springsecuritypolling.domain.poll;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



/**
 * Created by jyami on 2020/07/31
 */
public interface PollRepository extends JpaRepository<Poll, Long> {
    long countByCreatedBy(final long createdBy);

    Page<Poll> findByCreatedBy(Long createdBy, Pageable pageable);
}
