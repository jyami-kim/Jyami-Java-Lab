package com.jyami.demo.service

import org.springframework.stereotype.Service
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter.SseEventBuilder
import java.io.IOException

@Service
class CommentService {
    val container : MutableSet<SseEmitter> = mutableSetOf()

    fun connect(): SseEmitter{
        val sseEmitter = SseEmitter(300_000L)

        val sseEventBuilder = SseEmitter.event()
            .name("connect")
            .data("connected!")
            .reconnectTime(3000L)

        sendEvent(sseEmitter, sseEventBuilder)

        container.add(sseEmitter)
        sseEmitter.onCompletion {
            container.remove(sseEmitter)
        }

        return sseEmitter
    }

    fun sendComment(){
        val sseEmitterBuilder = SseEmitter.event()
            .name("newComment")
            .data("comment")
            .reconnectTime(3000L)

        container
            .forEach{
                sendEvent(it, sseEmitterBuilder)
            }
    }

    fun sendEvent(sseEmitter: SseEmitter, sseEventBuilder: SseEventBuilder){
        try {
            sseEmitter.send(sseEventBuilder)
        } catch (e: IOException) {
            sseEmitter.complete()
        }
    }

}


