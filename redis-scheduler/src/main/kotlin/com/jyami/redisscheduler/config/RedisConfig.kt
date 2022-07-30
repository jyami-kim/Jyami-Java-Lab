package com.jyami.redisscheduler.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "redis")
data class RedisConfig(
    val url: String,
    val stream: Stream,
    val set: Set
//    val corePendingTime: Int,
//    val limitPendingTime: Long,
//    val xReadGroupTimeout: Long,
//    val executorTimeout: Long,
) {
    data class Stream(
        val key: String,
        val group: String,
        val defaultOffset: String,
        val groupReadTimeout: Long
    )

    data class Set(
        val key: String
    )
}
