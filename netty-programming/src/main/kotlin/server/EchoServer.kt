package server

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelInitializer
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.SocketChannel
import io.netty.channel.socket.nio.NioServerSocketChannel
import java.net.InetSocketAddress

/**
 * 부트스트랩의 단꼐
 * 1. 서버를 부트스트랩하고 바인딩하는데 이용할 ServerBootstrap 인스턴스를 생성한다
 * 2. 새로운 연결 수락, 데이터 읽기/쓰기 같은 이벤트 처리를 수행할 NioEventLoopGroup 인스턴스를 생성하고 할당함
 * 3. 서버가 바인딩하는 로컬 InetSocketAddress 지정
 * 4. server.EchoServerHandler 인스턴스를 이용해 새로운 각 Channel 초기화
 * 5. ServerBootstrap.bind()를 호출해 서버를 바인딩
 */
class EchoServer(private val port: Int) {
    fun start(){
        val serverHandler = EchoServerHandler()
        val group = NioEventLoopGroup() // EventLoopGroup 생성
        try {
            val b = ServerBootstrap() // ServerBootStrap 생성
            b.group(group) // EventLoopGroup 사용 설정
                .channel(NioServerSocketChannel::class.java) // Nio 채널을 사용하게 설정
                .localAddress(InetSocketAddress(port)) //지정 포트 이용해 소켓 주소 설정
                .childHandler(object : ChannelInitializer<SocketChannel?>() { // EchoServerHandler를 pipeline으로 등록
                    @Throws(Exception::class)
                    override fun initChannel(ch: SocketChannel?) {
                        ch?.pipeline()?.addLast(serverHandler)
                    }
                })
            val future = b.bind().sync() // 서버를 비동기 바인딩. (근데 동기적으로 바인딩 완료 기다림)
            future.channel().closeFuture().sync() // 채널의 CloseFuture를 얻고 완료될 때까지 현재 스레드 블로킹
        }finally {
            group.shutdownGracefully().sync()
        }
    }

}

// 서버를 부트스트랩한다.
fun main(args: Array<String>){
    if (args.size != 1){
        System.err.println("Usage: ${EchoServer::class.java.simpleName} <port>")
    }
    val port: Int = Integer.parseInt(args[0])
    EchoServer(port).start()
}
