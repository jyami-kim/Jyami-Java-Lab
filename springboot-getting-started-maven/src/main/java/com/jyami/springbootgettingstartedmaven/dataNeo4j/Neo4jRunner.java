package com.jyami.springbootgettingstartedmaven.dataNeo4j;

import com.jyami.springbootgettingstartedmaven.dataNeo4j.developer.Developer;

import com.jyami.springbootgettingstartedmaven.dataNeo4j.developer.DeveloperRepository;
import com.jyami.springbootgettingstartedmaven.dataNeo4j.developer.Role;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

/**
 * Created by jyami on 2020/07/21
 */
public class Neo4jRunner implements ApplicationRunner {


    @Autowired
    DeveloperRepository developerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Developer developer = new Developer();
        developer.setEmail("mor222232@gmail.com");
        developer.setUsername("minjeong");

        Role role= new Role();
        role.setName("user");

        developer.getRoles().add(role);

        developerRepository.save(developer);
    }
}
