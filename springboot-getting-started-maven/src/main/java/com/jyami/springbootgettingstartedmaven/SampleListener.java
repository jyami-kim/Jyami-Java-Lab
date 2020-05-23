package com.jyami.springbootgettingstartedmaven;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;

/**
 * Created by jyami on 2020/05/24
 */
public class SampleListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent applicationStartingEvent) {
        System.out.println("================");
        System.out.println("Application is Starting");
        System.out.println("================");
    }
}
