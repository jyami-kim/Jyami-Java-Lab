package httpWebSocket

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.Channel
import io.netty.channel.ChannelFuture
import io.netty.channel.ChannelHandler
import io.netty.channel.group.DefaultChannelGroup
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import io.netty.util.concurrent.ImmediateEventExecutor
import java.net.InetSocketAddress

class ChatServer {
    private val channelGroup = DefaultChannelGroup(ImmediateEventExecutor.INSTANCE)
    private val group = NioEventLoopGroup()
    private var channel: Channel? = null

    fun start(address: InetSocketAddress): ChannelFuture {
        val bootstrap = ServerBootstrap()
        bootstrap.group(group)
            .channel(NioServerSocketChannel::class.java)
            .childHandler(createInitializer(channelGroup))
        val future = bootstrap.bind(address)
        future.syncUninterruptibly()
        channel = future.channel()
        return future
    }

    private fun createInitializer(channelGroup: DefaultChannelGroup): ChannelHandler {
        return ChatServerInitializer(channelGroup)
    }

    fun destory() {
        if (channel != null) {
            channel?.close()
        }
        channelGroup.close()
        group.shutdownGracefully()
    }
}
