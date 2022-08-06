package com.jyami.redisscheduler.factory

import com.jyami.redisscheduler.factory.FactoryCorrecter.Companion.consumerName

class RedisLeaderExectionManager(private val redisAdaptor: RedisAdaptor) : LeaderElectionManager {

    override fun isLeader(): Boolean {
        return redisAdaptor.getLeader() == consumerName
    }

}
