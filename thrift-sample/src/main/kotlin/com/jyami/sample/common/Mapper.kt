package com.jyami.sample.common

import com.fasterxml.jackson.databind.ObjectMapper
import de.undercouch.bson4jackson.BsonFactory

object Mapper {
    val bson = ObjectMapper(BsonFactory())
}
