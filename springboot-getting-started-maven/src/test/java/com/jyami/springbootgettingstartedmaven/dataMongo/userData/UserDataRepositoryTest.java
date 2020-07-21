package com.jyami.springbootgettingstartedmaven.dataMongo.userData;

import com.jyami.springbootgettingstartedmaven.dataRedis.account.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by jyami on 2020/07/21
 */

@RunWith(SpringRunner.class)
@DataMongoTest
public class UserDataRepositoryTest {

    @Autowired
    UserDataRepository userDataRepository;

    @Test
    public void findByEmail() {
        UserData userData = new UserData();
        userData.setUsername("jyam_mi");
        userData.setEmail("mjung1798@ewhain.net");

        userDataRepository.save(userData);

        Optional<UserData> byId = userDataRepository.findById(userData.getId());
        assertThat(byId).isNotEmpty();

        Optional<UserData> byEmail = userDataRepository.findByEmail(userData.getEmail());
        assertThat(byEmail).isNotEmpty();
        assertThat(byEmail.get().getUsername()).isEqualTo("jyam_mi");
    }
}