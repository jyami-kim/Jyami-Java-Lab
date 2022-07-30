package com.jyami.redisscheduler.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "scheduler")
data class SchedulerConfig(val corePoolSize: Int)
