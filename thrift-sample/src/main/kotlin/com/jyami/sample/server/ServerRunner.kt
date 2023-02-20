package com.jyami.sample.server

import com.jyami.java.thrift.service.PostService
import org.apache.thrift.server.TServer.Args
import org.apache.thrift.server.TSimpleServer
import org.apache.thrift.transport.TServerSocket

fun main(){
    val serverTransport = TServerSocket(9090)
    val postServiceImpl = PostServiceImpl()
    val server = TSimpleServer(Args(serverTransport).processor(PostService.Processor(postServiceImpl)))

    println("Starting the simple server")
    server.serve()
}
