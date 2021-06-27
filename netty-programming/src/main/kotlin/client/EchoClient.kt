package client

import io.netty.bootstrap.Bootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioSocketChannel
import java.net.InetSocketAddress

class EchoClient(private val host: String,
                 private val port: Int) {
    fun start(){
        val group = NioEventLoopGroup()
        try{
            val b = Bootstrap() // bootstrap 생성
            b.group(group) // 클라이언트 이벤트를 처리할 EventLoopGroup을 지정함 (NIO)
                .channel(NioSocketChannel::class.java) // 채널 유형으로 NIO 지정
                .remoteAddress(InetSocketAddress(host, port)) // 서버의 InetSocketAddress 지정
                .handler(object: ChannelInitializer<SocketChannel?>(){ // 채널이 생성될 때 파이프라인에 EchoClientHandler 하나 추가
                    override fun initChannel(ch: SocketChannel?) {
                        ch?.pipeline()?.addLast(EchoClientHandler());
                    }
                })
            val future = b.connect().sync() // 원격 피어로 연결 블로킹
            future.channel().closeFuture().sync() // 채널 닫힐때까지 블로킹
        }finally {
            group.shutdownGracefully().sync()
        }
    }

}


fun main(args: Array<String>){
    if(args.size != 2){
        System.err.println("Usage: ${EchoClient::class.java.simpleName} <host> <port>")
    }
    val host = args[0]
    val port = Integer.parseInt(args[1])
    EchoClient(host, port).start()
}
