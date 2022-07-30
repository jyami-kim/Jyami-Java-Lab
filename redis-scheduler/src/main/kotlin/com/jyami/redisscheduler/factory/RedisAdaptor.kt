package com.jyami.redisscheduler.factory

import com.jyami.redisscheduler.config.RedisConfig
import com.jyami.redisscheduler.item.Album
import io.lettuce.core.Consumer
import io.lettuce.core.RedisClient
import io.lettuce.core.StreamMessage
import io.lettuce.core.XGroupCreateArgs
import io.lettuce.core.XReadArgs
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import io.lettuce.core.codec.StringCodec
import org.springframework.stereotype.Service
import java.time.Duration


@Service
class RedisAdaptor(
    val redisConfig: RedisConfig
) {
    private val redisClient: RedisClient = RedisClient.create(redisConfig.url)
    private val connect: StatefulRedisConnection<String, String> = redisClient.connect()
    private val redisSyncCommand: RedisCommands<String, String> = connect.sync()

    fun createXGroup() {
        redisSyncCommand.xgroupCreate(
            XReadArgs.StreamOffset.from(redisConfig.stream.key, redisConfig.stream.key),
            redisConfig.stream.group,
            XGroupCreateArgs.Builder.mkstream()
        )
    }

    fun readXGroup(consumerName: String): MutableList<StreamMessage<String, String>> {
        return redisSyncCommand.xreadgroup(
            Consumer.from(redisConfig.stream.group, consumerName),
            XReadArgs.Builder.block(Duration.ofSeconds(redisConfig.stream.groupReadTimeout)),
            XReadArgs.StreamOffset.lastConsumed(redisConfig.stream.key)
        )
    }

    fun addXGroup(id: String, value: Any) {
        redisSyncCommand.xadd(id, value)
    }

    fun addSet(value: String): Boolean {
        val result = redisSyncCommand.sadd(redisConfig.set.key, value)
        return result > 0
    }
}
