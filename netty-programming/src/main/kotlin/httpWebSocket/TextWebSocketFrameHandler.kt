package httpWebSocket

import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.channel.group.ChannelGroup
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler

class TextWebSocketFrameHandler(val group: ChannelGroup) : SimpleChannelInboundHandler<TextWebSocketFrame>() {

    override fun userEventTriggered(ctx: ChannelHandlerContext, evt: Any) {
        if(evt == WebSocketServerProtocolHandler.ServerHandshakeStateEvent.HANDSHAKE_COMPLETE){
            ctx.pipeline().remove(HttpRequestHandler::class.java)
            group.writeAndFlush(TextWebSocketFrame("Client ${ctx.channel()} Joined"))
            group.add(ctx.channel())
        }else{
            super.userEventTriggered(ctx, evt)
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: TextWebSocketFrame) {
        group.writeAndFlush(msg.retain())
    }
}
