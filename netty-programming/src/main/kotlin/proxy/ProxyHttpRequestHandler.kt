package proxy

import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.*

class ProxyHttpRequestHandler : SimpleChannelInboundHandler<FullHttpRequest>() {

    override fun channelRead0(ctx: ChannelHandlerContext, request: FullHttpRequest) {
        println("http Request Handler Channel Read")
        if (HttpHeaders.is100ContinueExpected(request)) {
            send100Continue(ctx)
        }
//        DefaultFullHttpResponse
        val response = DefaultHttpResponse(request.protocolVersion, HttpResponseStatus.OK)
        response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
        val responseText = "<html>hello</html>"
        val keepAlive = HttpHeaders.isKeepAlive(request)
        if (keepAlive) {
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, responseText.length)
            response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE)
        }
        ctx.write(response)
        ctx.write(responseText)

        val future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT)
        if (!keepAlive) {
            future.addListener { ChannelFutureListener.CLOSE }
        }
    }

    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.flush()
    }

    private fun send100Continue(ctx: ChannelHandlerContext) {
        val response = DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.CONTINUE)
        ctx.writeAndFlush(response)
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }
}
