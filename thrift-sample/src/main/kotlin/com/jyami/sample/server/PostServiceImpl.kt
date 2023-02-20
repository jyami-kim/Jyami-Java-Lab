package com.jyami.sample.server

import com.jyami.java.thrift.service.CommonResponse
import com.jyami.java.thrift.service.PostService
import com.jyami.java.thrift.service.RequestWithFlag
import com.jyami.sample.common.Mapper
import java.nio.ByteBuffer

class PostServiceImpl : PostService.Iface{

    private val memory = HashMap<String, String>()
    override fun ping() {
        print("ping")
    }

    override fun read(request: RequestWithFlag): CommonResponse {
        val data = Mapper.bson.writeValueAsString(request.bsonData)
        print("read request : {$data}")

        val response = Mapper.bson.writeValueAsString("hello")

        val commonResponse = CommonResponse(200)
        commonResponse.setBsonBody(response.encodeToByteArray())
        return commonResponse
    }

    override fun save(bsonData: ByteBuffer?): CommonResponse {
        print("save request")
        return CommonResponse()
    }

    override fun remove(bsonData: ByteBuffer?): CommonResponse {
        print("remove request")
        return CommonResponse()
    }

}
