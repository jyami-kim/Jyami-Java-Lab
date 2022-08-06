package com.jyami.redisscheduler.config

import com.jyami.redisscheduler.factory.LeaderElectionManager
import com.jyami.redisscheduler.factory.RedisAdaptor
import com.jyami.redisscheduler.factory.RedisLeaderExectionManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class FactoryConfig(val redisAdaptor: RedisAdaptor) {

    @Bean
    fun leaderElectionManager(): LeaderElectionManager = RedisLeaderExectionManager(redisAdaptor)

}
