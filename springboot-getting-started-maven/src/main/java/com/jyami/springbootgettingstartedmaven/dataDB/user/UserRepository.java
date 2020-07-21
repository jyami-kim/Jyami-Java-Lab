package com.jyami.springbootgettingstartedmaven.dataDB.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * Created by jyami on 2020/07/17
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
