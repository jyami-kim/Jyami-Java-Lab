package com.jyami.springbootgettingstartedmaven;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/05/24
 */
@Component
public class ApplicationRunnerListener implements ApplicationRunner {

    @Autowired
    private JyamiProperties jyamiProperties;

    @Autowired
    private String hello;

    @Override
    public void run(ApplicationArguments args){
        System.out.println("======application Runner=======");
        System.out.println(args.getOptionNames());
        System.out.println("===========");
        System.out.println(jyamiProperties.getName());
        System.out.println(jyamiProperties.getAge());
        System.out.println("===========");
        System.out.println(hello);

    }
}
