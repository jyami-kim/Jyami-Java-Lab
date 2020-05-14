package com.jyami.interfacebean;

import org.springframework.stereotype.Service;

/**
 * Created by jyami on 2020/04/27
 */
@Service
public final class MyServiceImpl implements MyService {
    @Override
    public void doSomething() {
        System.out.println("hello Im Impl Service");
    }
}
