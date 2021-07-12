package httpWebSocket

import java.net.InetSocketAddress
import kotlin.system.exitProcess

fun main(args: Array<String>){
    if(args.size != 1){
        System.err.println("Please give port as argument")
        exitProcess(1)
    }
    val port = args[0].toInt()
    val endpoint = ChatServer()
    val future = endpoint.start(InetSocketAddress(port))
    Runtime.getRuntime().addShutdownHook(object: Thread() {
        override fun run() {
            endpoint.destory()
        }
    })
    future.channel().closeFuture().syncUninterruptibly()
}
