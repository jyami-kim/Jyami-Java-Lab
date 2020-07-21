package com.jyami.springbootgettingstartedmaven.dataMongo.userData;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Created by jyami on 2020/07/21
 */
public interface UserDataRepository extends MongoRepository<UserData, String> {
    Optional<UserData> findByEmail(String email);
}
