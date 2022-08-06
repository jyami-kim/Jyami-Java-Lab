package com.jyami.redisscheduler.factory

import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.net.InetAddress

@Component
class FactoryCorrecter(val redisAdaptor: RedisAdaptor, val leaderExectionManager: LeaderElectionManager) {

    companion object {
        val consumerName: String = InetAddress.getLocalHost().hostName
    }

    @Scheduled(cron = "0 */1 * * * *")
    fun checkOldPendingJob() {
        if (leaderExectionManager.isLeader()) {
            redisAdaptor.pendingXGroup(consumerName)
                .map { it.id }
                .forEach { redisAdaptor.claimXGroup(it) }
        }
    }

}
