package com.jyami.redisscheduler

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class RedisSchedulerApplication

fun main(args: Array<String>) {
    runApplication<RedisSchedulerApplication>(*args)
}
