package com.jyami.redisscheduler.factory

import com.jyami.redisscheduler.factory.FactoryCorrecter.Companion.consumerName
import com.jyami.redisscheduler.config.SchedulerConfig
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalTime
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit
import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

@Component
class FactoryExecutor(
    private val schedulerConfig: SchedulerConfig,
    private val redisAdaptor: RedisAdaptor
) {
    private val executor: ScheduledExecutorService = Executors.newScheduledThreadPool(schedulerConfig.corePoolSize)
    private var running: Boolean = true

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostConstruct
    fun initialize() {
        redisAdaptor.createXGroup()
        redisAdaptor.addConsumerNameToSet(consumerName)
        executor()
    }

    private fun executor() {
        Executors.newSingleThreadExecutor().submit {
            while (running) {
                val messages = redisAdaptor.readXGroup(consumerName)
                for (message in messages) {
                    message.body.entries
                        .map { x -> Pair(x.key, x.value) }
                        .forEach { x ->
                            CompletableFuture.runAsync({ processJob(x, message.id) }, executor)
                        }
                }
            }
        }
    }

    private fun processJob(
        entry: Pair<String, String>,
        id: String
    ) {
        val eof = processAlbumFactoryJob(entry, id)
        if (eof) {
            redisAdaptor.removeDuplicationKeyToSet(id)
        } else {
            // TODO : eof 가 아닐 경우 재 등록
//            redisAdaptor.ackXGroup(id)
        }
        redisAdaptor.ackXGroup(id)
    }

    /**
     * return 은 eof 여부
     */
    private fun processAlbumFactoryJob(entry: Pair<String, String>, id: String): Boolean {
        val (key, value) = entry
        println("consuming process : $key-$value : StreamID $id")
        Thread.sleep(2000)
        return false
    }


    @PreDestroy
    fun close() {
        running = false
        redisAdaptor.removeConsumerNameToSet(consumerName)
        executor.shutdown()
        if (executor.awaitTermination(schedulerConfig.executorTimeout, TimeUnit.SECONDS)) {
            logger.info("${LocalTime.now()} All jobs are terminated")
        } else {
            logger.info("${LocalTime.now()} some jobs are not terminated")
            executor.shutdownNow()
        }
    }

}
