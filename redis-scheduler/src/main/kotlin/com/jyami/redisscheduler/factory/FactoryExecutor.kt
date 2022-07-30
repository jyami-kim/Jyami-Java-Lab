package com.jyami.redisscheduler.factory

import com.jyami.redisscheduler.config.SchedulerConfig
import java.net.InetAddress
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService


class FactoryExecutor(
    schedulerConfig: SchedulerConfig,
    private val redisAdaptor: RedisAdaptor
) {
    private val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(schedulerConfig.corePoolSize)
    private val consumerName: String = InetAddress.getLocalHost().hostName
    private val running: Boolean = true

    val thread = Executors.newSingleThreadExecutor().submit {
        while (running) {
            val messages = redisAdaptor.readXGroup(consumerName)
            for (message in messages) {
                message.body.entries.forEach { (key, value) ->
                    CompletableFuture.runAsync(
                        { processAlbumFactoryJob(key, value, message.id) },
                        executor
                    )
                }
            }
        }
    }

    private fun processAlbumFactoryJob(key: String, value: String, id: String){
        println("consuming process : $key-$value : StreamID $id")
    }


}
