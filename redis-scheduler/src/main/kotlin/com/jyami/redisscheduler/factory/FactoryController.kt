package com.jyami.redisscheduler.factory

import com.jyami.redisscheduler.item.Album
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("factory")
class FactoryController(val redisAdaptor: RedisAdaptor) {

    @PostMapping
    fun registerAlbum(@RequestBody album: Album) {
        if (redisAdaptor.addSet(album.id.toString())){
            redisAdaptor.addXGroup(album.id.toString(), album)
        }
    }

}
