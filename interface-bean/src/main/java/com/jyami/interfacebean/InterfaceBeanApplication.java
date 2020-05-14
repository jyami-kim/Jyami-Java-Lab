package com.jyami.interfacebean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InterfaceBeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterfaceBeanApplication.class, args);
    }

    @Autowired
    private MyServiceImpl myService;


    @Bean
    ApplicationRunner applicationRunner() {
        return (args) -> {
            myService.doSomething();
        };
    }
}
