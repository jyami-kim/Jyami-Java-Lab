package com.jyami.demo.controller

import com.jyami.demo.service.CommentService
import org.springframework.http.*
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter


@Controller
class ConnectController(
    val commentService: CommentService
) {

    @GetMapping(value = ["/connect"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    fun connect(): ResponseEntity<SseEmitter> {
        val emitter: SseEmitter = commentService.connect()

        val header: HttpHeaders = HttpHeaders()
        header.add(HttpHeaders.CONTENT_TYPE,  MediaType.TEXT_EVENT_STREAM_VALUE)

        return ResponseEntity(emitter, header, HttpStatus.OK)
    }

    @GetMapping("post")
    fun post(): ResponseEntity<String> {
        commentService.sendComment()
        return ResponseEntity.ok().body("ok")
    }

}