package com.jyami.redisscheduler.item

data class Album(
    val id: Int,
    val name: String,
    val artist: String,
    val trackList: List<String>
) : Item(id)
