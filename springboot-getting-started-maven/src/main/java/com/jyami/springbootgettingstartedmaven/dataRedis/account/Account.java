package com.jyami.springbootgettingstartedmaven.dataRedis.account;

import org.springframework.data.redis.core.RedisHash;

import javax.persistence.Id;

/**
 * Created by jyami on 2020/07/21
 */
@RedisHash("accounts")
public class Account {
    @Id
    private String id;

    private String username;

    private String email;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
