package com.jyami.springsecuritypolling.user;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jyami on 2020/07/30
 */
public interface UserRepository extends JpaRepository<User, Long> {

}
