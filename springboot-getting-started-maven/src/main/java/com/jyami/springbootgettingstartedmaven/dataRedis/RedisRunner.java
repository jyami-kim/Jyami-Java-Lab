package com.jyami.springbootgettingstartedmaven.dataRedis;

import com.jyami.springbootgettingstartedmaven.dataRedis.account.Account;
import com.jyami.springbootgettingstartedmaven.dataRedis.account.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Created by jyami on 2020/07/21
 */
@Component
public class RedisRunner implements ApplicationRunner {
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    AccountRepository accountRepository;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        ValueOperations<String, String> forValue = stringRedisTemplate.opsForValue();
        forValue.set("jyami","minjeong");
        forValue.set("hello","world");
        forValue.set("springboot","2.0");

        Account account = new Account();
        account.setUsername("jyami");
        account.setEmail("mor2222@naver.com");

        accountRepository.save(account);

        Optional<Account> byId = accountRepository.findById(account.getId());
        System.out.println(byId.get().getUsername());
        System.out.println(byId.get().getEmail());
    }
}
