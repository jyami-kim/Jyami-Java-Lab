package proxy

import io.netty.handler.ssl.SslContextBuilder
import io.netty.handler.ssl.util.SelfSignedCertificate
import java.net.InetSocketAddress

private const val PORT = 8080

fun main(args: Array<String>){
    val cert = SelfSignedCertificate()
    val context = SslContextBuilder.forServer(cert.certificate(), cert.privateKey()).build()
    val endpoint = SecureProxyServer(context)
    val future = endpoint.start(InetSocketAddress(PORT))
    Runtime.getRuntime().addShutdownHook(object: Thread() {
        override fun run() {
            endpoint.destory()
        }
    })
    future.channel().closeFuture().syncUninterruptibly()
}
