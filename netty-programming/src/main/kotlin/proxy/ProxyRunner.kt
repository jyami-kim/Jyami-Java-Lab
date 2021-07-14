package proxy

import java.net.InetSocketAddress
private const val PORT = 8080

fun main(args: Array<String>){
    val endpoint = ProxyServer()
    val future = endpoint.start(InetSocketAddress(PORT))
    Runtime.getRuntime().addShutdownHook(object: Thread() {
        override fun run() {
            endpoint.destory()
        }
    })
    future.channel().closeFuture().syncUninterruptibly()
}
