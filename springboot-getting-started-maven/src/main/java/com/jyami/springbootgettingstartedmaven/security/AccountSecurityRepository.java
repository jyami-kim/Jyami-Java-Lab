package com.jyami.springbootgettingstartedmaven.security;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Created by jyami on 2020/07/21
 */
public interface AccountSecurityRepository extends JpaRepository<AccountSecurity, Long> {
    Optional<AccountSecurity> findByUsername(String username);
}
