package com.jyami.redisscheduler.factory

import com.jyami.redisscheduler.item.Album
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("factory")
class FactoryController(val redisAdaptor: RedisAdaptor) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping
    fun registerAlbum(@RequestBody album: Album) {
        if (redisAdaptor.addDuplicationKeyToSet(album.id.toString())){
            logger.info("REGISTER ${album.id} ${album.artist} ${album.name}")
            redisAdaptor.addXGroup(album.id.toString(), album)
        }else{
            logger.info("ALREADY REGISTER ${album.id}")
        }
    }

}
