package com.jyami.springbootgettingstartedmaven.dataMongo;

import com.jyami.springbootgettingstartedmaven.dataMongo.userData.UserData;
import com.jyami.springbootgettingstartedmaven.dataMongo.userData.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/07/21
 */
@Component
public class MongoRunner implements ApplicationRunner {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserDataRepository userDataRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UserData userData = new UserData();
        userData.setEmail("mor222293@gmail.com");
        userData.setUsername("minjeong");

        userDataRepository.insert(userData);
    }
}
