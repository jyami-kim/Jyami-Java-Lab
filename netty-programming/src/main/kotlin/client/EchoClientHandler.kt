package client

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.util.CharsetUtil

@ChannelHandler.Sharable
class EchoClientHandler : SimpleChannelInboundHandler<ByteBuf>(){
    override fun channelActive(ctx: ChannelHandlerContext?) {
        ctx?.writeAndFlush(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8)) // 채널 활성화 알림을 받으면 메세지 전송
    }

    override fun channelRead0(ctx: ChannelHandlerContext?, msg: ByteBuf?) {
        println("Client Received: ${msg?.toString(CharsetUtil.UTF_8)}")
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }
}
