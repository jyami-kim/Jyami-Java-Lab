package server

import io.netty.buffer.ByteBuf
import io.netty.buffer.Unpooled
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandler
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.CharsetUtil
import java.util.*

// 비즈니스 로직 구현 : 인바운드 메세지에 대한 알림을 받는다.
@ChannelHandler.Sharable
class EchoServerHandler : ChannelInboundHandlerAdapter(){

    override fun channelRead(ctx: ChannelHandlerContext?, msg: Any?) {
        val input = msg as ByteBuf
        println("Server received: ${input.toString(CharsetUtil.UTF_8)}")
        ctx?.write(input) // 아웃바운드 메세지로 플러시 하지 않고 받은 메세지를 발신자로 출력함
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext?) {
        ctx?.writeAndFlush(Unpooled.EMPTY_BUFFER) // 대기 중인 메세지를 원격 피어로 플러시하고 채널을 닫음
            ?.addListener(ChannelFutureListener.CLOSE)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        cause?.printStackTrace()
        ctx?.close()
    }

}
