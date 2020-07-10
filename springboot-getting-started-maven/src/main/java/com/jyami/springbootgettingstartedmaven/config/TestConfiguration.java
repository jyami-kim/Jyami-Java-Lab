package com.jyami.springbootgettingstartedmaven.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * Created by jyami on 2020/07/09
 */
@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean
    public String hello(){
        return "hello test";
    }

}
