package com.jyami.springbootgettingstartedmaven;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/05/24
 */
@Component
public class ApplicationRunnerListener implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("======application Runner=======");
        System.out.println(args.getOptionNames());
    }
}
