package proxy

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.handler.logging.LogLevel
import io.netty.handler.logging.LoggingHandler
import java.net.InetSocketAddress

open class ProxyServer {
//    private val channelGroup = DefaultChannelGroup(ImmediateEventExecutor.INSTANCE)
    private val group = NioEventLoopGroup()
    private var channel: Channel? = null

    fun start(address: InetSocketAddress): ChannelFuture {
        println("server Start")
        val bootstrap = ServerBootstrap()
        bootstrap.group(group)
            .channel(NioServerSocketChannel::class.java)
            .handler(LoggingHandler(LogLevel.INFO))
            .childHandler(createInitializer())

        return bootstrap.bind(address).sync()
    }

    protected open fun createInitializer() : ChannelInitializer<Channel>{
        return ProxyInitializer()
    }

    fun destory() {
        if (channel != null) {
            channel?.close()
        }
        group.shutdownGracefully()
    }
}
