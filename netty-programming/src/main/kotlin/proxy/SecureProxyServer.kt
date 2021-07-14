package proxy

import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.handler.ssl.SslContext

class SecureProxyServer(private val context: SslContext): ProxyServer() {

    override fun createInitializer() : ChannelInitializer<Channel> {
        return SecureProxyInitializer(context)
    }
}
