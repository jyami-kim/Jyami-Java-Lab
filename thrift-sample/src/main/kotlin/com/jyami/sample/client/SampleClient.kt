package com.jyami.sample.client

import com.jyami.java.thrift.service.PostService
import org.apache.thrift.protocol.TBinaryProtocol
import org.apache.thrift.transport.TSocket

fun main(){
    val transport = TSocket("localhost", 9090)
    transport.open()

    val protocol = TBinaryProtocol(transport)
    val client = PostService.Client(protocol)

//    val data = Mapper.bson.writeValueAsBytes("""{"name" : "jyami"}""")
//
//    println("client req read")
//    val response = client.read(RequestWithFlag(ByteBuffer.wrap(data), true))
//
//    val resData = Mapper.bson.readValue(response.bsonBody.array(), String::class.java)
//
//    println("client recv read ${response.status} - $resData")

    client.ping()

    transport.close()
}
