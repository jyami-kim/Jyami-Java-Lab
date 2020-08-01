package com.jyami.springsecuritypolling.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jyami on 2020/07/30
 */
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsernameOrEmail(final String username, final String email);

    boolean existsByEmailOrUsername(String email, String username);
}
