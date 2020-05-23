package com.jyami.springbootgettingstartedmaven;

import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;

/**
 * Created by jyami on 2020/05/24
 */
@Component
public class ArgumentPrinter {
    public ArgumentPrinter(ApplicationArguments arguments){
        System.out.println("foo: " + arguments.containsOption("foo"));
        System.out.println("bar: " + arguments.containsOption("bar"));
    }
}
