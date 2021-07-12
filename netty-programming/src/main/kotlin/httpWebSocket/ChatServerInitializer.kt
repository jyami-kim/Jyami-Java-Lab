package httpWebSocket

import io.netty.channel.Channel
import io.netty.channel.ChannelInitializer
import io.netty.channel.group.ChannelGroup
import io.netty.handler.codec.http.HttpObjectAggregator
import io.netty.handler.codec.http.HttpServerCodec

class ChatServerInitializer(val group: ChannelGroup) : ChannelInitializer<Channel>() {
    override fun initChannel(ch: Channel) {
        val pipeline = ch.pipeline()
        pipeline.addLast(HttpServerCodec())
        pipeline.addLast(HttpObjectAggregator(64 * 1024))
        pipeline.addLast(HttpRequestHandler("/ws"))
//        pipeline.addLast(WebSocketServerProtocolHandler("/ws"))
//        pipeline.addLast(TextWebSocketFrameHandler(group))
    }
}
