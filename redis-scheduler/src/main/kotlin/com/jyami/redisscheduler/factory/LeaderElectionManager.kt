package com.jyami.redisscheduler.factory

interface LeaderElectionManager {
    fun isLeader(): Boolean
}
