package proxy

import io.netty.channel.Channel
import io.netty.handler.ssl.SslContext
import io.netty.handler.ssl.SslHandler

class SecureProxyInitializer(private val context: SslContext): ProxyInitializer() {
    override fun initChannel(ch: Channel) {
        super.initChannel(ch)
        val sslEngine = context.newEngine(ch.alloc())
        ch.pipeline().addFirst(SslHandler(sslEngine, true))
    }
}
