package com.jyami.redisscheduler.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "redis")
data class RedisConfig(
    val url: String,
    val stream: Stream,
    val set: Set,
    val leaderKey: String
) {
    data class Stream(
        val key: String,
        val group: String,
        val defaultOffset: String,
        val groupReadTimeout: Long,
        val limitPendingTime: Long
    )

    data class Set(
        val duplicationKey: String,
        val consumerKey: String
    )
}
