package httpWebSocket

import io.netty.channel.ChannelFutureListener
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.DefaultFileRegion
import io.netty.channel.SimpleChannelInboundHandler
import io.netty.handler.codec.http.*
import io.netty.handler.ssl.SslHandler
import io.netty.handler.stream.ChunkedNioFile
import java.io.File
import java.io.RandomAccessFile
import java.lang.IllegalStateException
import java.net.URISyntaxException

class HttpRequestHandler(private val wsUri: String) : SimpleChannelInboundHandler<FullHttpRequest>() {

    companion object {
        val INDEX: File = initFile()
        private fun initFile(): File {
            val location = HttpRequestHandler::class.java.protectionDomain.codeSource.location
            try {
                val uri = "${location.toURI()}index.html"
                val path = if (!uri.contains("file:")) uri else uri.substring(5)
                return File(path)
            } catch (e: URISyntaxException) {
                throw IllegalStateException("Unable to locate index.html", e)
            }
        }
    }

    override fun channelRead0(ctx: ChannelHandlerContext, request: FullHttpRequest) {
        if (wsUri.equals(request.uri(), ignoreCase = true)) {
            ctx.fireChannelRead(request.uri)
        } else {
            if (HttpHeaders.is100ContinueExpected(request)) {
                send100Continue(ctx)
            }
            val file = RandomAccessFile(INDEX, "r")
            val response = DefaultHttpResponse(request.protocolVersion, HttpResponseStatus.OK)
            response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain; charset=UTF-8");
            val keepAlive = HttpHeaders.isKeepAlive(request)
            if (keepAlive) {
                response.headers().set(HttpHeaders.Names.CONTENT_LENGTH, file.length())
                response.headers().set(HttpHeaders.Names.CONNECTION, HttpHeaders.Values.KEEP_ALIVE)
            }
            ctx.write(response)
            if(ctx.pipeline().get(SslHandler::class.java) == null){
                ctx.write(DefaultFileRegion(file.channel, 0, file.length()))
            }else{
                ctx.write(ChunkedNioFile(file.channel))
            }
            val future = ctx.writeAndFlush(LastHttpContent.EMPTY_LAST_CONTENT)
            if(!keepAlive){
                future.addListener { ChannelFutureListener.CLOSE }
            }
        }
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
