package com.jyami.springsecuritypolling.properties;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Created by jyami on 2020/07/30
 */
@Getter
@ConfigurationProperties("app")
@EnableConfigurationProperties(AppProperties.class)
public class AppProperties {
    private String jwtSecret;
    private int jwtExpirationInMs;

}
