package com.jyami.oauth2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class Oauth2Application {

    private static final String PROPERTIES = "spring.config.location=classpath:/application-google.yml";
    private static final String APPLICATION_PROPERTIES = "spring.config.location=classpath:/application.yml";

    public static void main(String[] args) {
        new SpringApplicationBuilder(Oauth2Application.class)
                .properties(PROPERTIES)
                .properties(APPLICATION_PROPERTIES)
                .run(args);
    }

}
