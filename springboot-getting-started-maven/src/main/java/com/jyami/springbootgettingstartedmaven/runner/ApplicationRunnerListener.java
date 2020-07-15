package com.jyami.springbootgettingstartedmaven.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/05/24
 */
@Component
public class ApplicationRunnerListener implements ApplicationRunner {

    Logger loggerFactory = LoggerFactory.getLogger(ApplicationRunnerListener.class);

    @Autowired
    private JyamiProperties jyamiProperties;

    @Autowired
    private String hello;

    @Override
    public void run(ApplicationArguments args){
        loggerFactory.debug("======application Runner=======");
        loggerFactory.debug(String.valueOf(args.getOptionNames()));
        loggerFactory.debug("===========");
        loggerFactory.debug(jyamiProperties.getName());
        loggerFactory.debug(String.valueOf(jyamiProperties.getAge()));
        loggerFactory.debug("===========");
        loggerFactory.debug(hello);

    }
}
