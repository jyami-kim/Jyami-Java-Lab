package http

import io.netty.buffer.Unpooled
import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.*
import io.netty.handler.codec.http.HttpResponseStatus.*
import io.netty.handler.codec.http.HttpVersion.HTTP_1_1
import io.netty.util.CharsetUtil


class CustomHttpServerHandler : SimpleChannelInboundHandler<Any?>() {
    private var request: HttpRequest? = null
    var responseData = StringBuilder()

    override fun channelReadComplete(ctx: ChannelHandlerContext) {
        ctx.flush()
    }

    override fun channelRead0(ctx: ChannelHandlerContext, msg: Any?) {
        if (msg is HttpRequest) {
            request = msg
            if (HttpUtil.is100ContinueExpected(request)) {
                writeResponse(ctx)
            }
            responseData.setLength(0)
            responseData.append(RequestUtils.formatParams(request))
        }
        responseData.append(RequestUtils.evaluateDecoderResult(request))

        if (msg is HttpContent) {
            responseData.append(RequestUtils.formatBody(msg))
            responseData.append(RequestUtils.evaluateDecoderResult(request))
            if (msg is LastHttpContent) {
                responseData.append(RequestUtils.prepareLastResponse(request, msg))
                writeResponse(ctx, msg, responseData)
            }
        }
    }

    private fun writeResponse(ctx: ChannelHandlerContext) {
        val response: FullHttpResponse = DefaultFullHttpResponse(HTTP_1_1, CONTINUE, Unpooled.EMPTY_BUFFER)
        ctx.write(response)
    }

    private fun writeResponse(
        ctx: ChannelHandlerContext, trailer: LastHttpContent,
        responseData: StringBuilder
    ) {
        val keepAlive = HttpUtil.isKeepAlive(request)
        val httpResponse: FullHttpResponse = DefaultFullHttpResponse(
            HTTP_1_1,
            if ((trailer as HttpObject).decoderResult().isSuccess) OK else BAD_REQUEST,
            Unpooled.copiedBuffer(responseData.toString(), CharsetUtil.UTF_8)
        )
        httpResponse.headers()[HttpHeaderNames.CONTENT_TYPE] = "text/plain; charset=UTF-8"
        if (keepAlive) {
            httpResponse.headers().setInt(
                HttpHeaderNames.CONTENT_LENGTH,
                httpResponse.content().readableBytes()
            )
            httpResponse.headers()[HttpHeaderNames.CONNECTION] = HttpHeaderValues.KEEP_ALIVE
        }
        ctx.write(httpResponse)
        if (!keepAlive) {
            ctx.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE)
        }
    }


    override fun exceptionCaught(ctx: ChannelHandlerContext, cause: Throwable) {
        cause.printStackTrace()
        ctx.close()
    }
}
