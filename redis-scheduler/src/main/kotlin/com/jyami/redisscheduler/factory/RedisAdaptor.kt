package com.jyami.redisscheduler.factory

import com.jyami.redisscheduler.config.RedisConfig
import io.lettuce.core.Consumer
import io.lettuce.core.Limit
import io.lettuce.core.Range
import io.lettuce.core.RedisClient
import io.lettuce.core.StreamMessage
import io.lettuce.core.XClaimArgs
import io.lettuce.core.XGroupCreateArgs
import io.lettuce.core.XPendingArgs
import io.lettuce.core.XReadArgs
import io.lettuce.core.api.StatefulRedisConnection
import io.lettuce.core.api.sync.RedisCommands
import io.lettuce.core.models.stream.PendingMessage
import org.springframework.stereotype.Service
import java.time.Duration
import javax.annotation.PreDestroy


@Service
class RedisAdaptor(
    private val redisConfig: RedisConfig
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

    fun pendingXGroup(consumerName: String): MutableList<PendingMessage> {
        return redisSyncCommand.xpending(
            redisConfig.stream.key,
            XPendingArgs.Builder
                .xpending(Consumer.from(redisConfig.stream.group, consumerName), Range.unbounded(), Limit.from(10))
                .idle(Duration.ofMinutes(redisConfig.stream.limitPendingTime))
        )
    }

    fun claimXGroup(id: String): MutableList<StreamMessage<String, String>>? {
        return redisSyncCommand.xclaim(
            redisConfig.stream.key,
            Consumer.from(redisConfig.stream.group, getOtherConsumer()),
            XClaimArgs.Builder.justid(),
            id
        )
    }

    private fun getOtherConsumer(): String {
        return redisSyncCommand.srandmember(redisConfig.set.consumerKey)
    }

    fun addXGroup(id: String, value: Any) {
        redisSyncCommand.xadd(id, value)
    }

    fun ackXGroup(id: String) {
        redisSyncCommand.xack(redisConfig.stream.key, redisConfig.stream.group, id)
    }

    fun addDuplicationKeyToSet(value: String): Boolean {
        val result = redisSyncCommand.sadd(redisConfig.set.duplicationKey, value)
        return result > 0
    }

    fun addConsumerNameToSet(value: String): Boolean {
        val result = redisSyncCommand.sadd(redisConfig.set.consumerKey, value)
        return result > 0
    }

    fun removeDuplicationKeyToSet(value: String) {
        redisSyncCommand.srem(redisConfig.set.duplicationKey, value)
    }

    fun removeConsumerNameToSet(value: String) {
        redisSyncCommand.srem(redisConfig.set.consumerKey, value)
    }

    fun getLeader(): String? {
        return redisSyncCommand.get(redisConfig.leaderKey)
    }

    @PreDestroy
    fun close() {
        connect.close()
    }
}
