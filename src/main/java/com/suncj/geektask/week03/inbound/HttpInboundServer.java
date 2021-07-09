package com.suncj.geektask.week03.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.List;

/**
 * @Classname HttpInboundServer
 * @Description
 * @Date 2021/7/7 9:57 上午
 * @Created by sunchangji
 */
public class HttpInboundServer {

    private int port;
    private List<String> proxyServers;

    public HttpInboundServer(int port, List<String> proxyServers) {
        this.port=port;
        this.proxyServers = proxyServers;
    }

    /**
     * 参考文章:https://www.cnblogs.com/java-chen-hao/p/11459808.html
     * @throws Exception
     */
    public void run() throws Exception {
        /**
         * NioEventLoopGroup就是一个线程池，NioEventLoop就是一个线程
         * NioEventLoopGroup线程池中有N个NioEventLoop线程
         */
        //负责处理客户端的 TCP 连接请求，如果系统只有一个服务端端口需要监听，则建议bossGroup线程组线程数设置为1
        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        //这里如果不指定线程数，则线程数为：CPU的核数*２
        EventLoopGroup workerGroup = new NioEventLoopGroup(16);

        try {
            ServerBootstrap b = new ServerBootstrap();
            /**
             * 主要是设置TCP连接中的一些可选项,而且这些属性是作用于每一个连接到服务器被创建的channel
             */
            //TCP建立连接时处于半连接状态的数量
            b.option(ChannelOption.SO_BACKLOG, 128)
                    //如果TCP_NODELAY没有设置为true,那么底层的TCP为了能减少交互次数,会将网络数据积累到一定的数量后,服务器端才发送出去,会造成一定的延迟。在互联网应用中,通常希望服务是低延迟的,建议将TCP_NODELAY设置为true
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    //利用TCP的SO_KEEPALIVE属性,当SO_KEEPALIVE=true的时候,服务端可以探测客户端的连接是否还存活着,如果客户端因为断电或者网络问题或者客户端挂掉了等,那么服务端的连接可以关闭掉,释放资源
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    //处于TIME-WAIT状态地址可以复用
                    //比如某个进程占用了80端口,然后重启进程,原来的socket1处于TIME-WAIT状态,进程启动后,使用一个新的socket2,要占用80端口,如果这个时候不设置SO_REUSEADDR=true,那么启动的过程中会报端口已被占用的异常
                    .childOption(ChannelOption.SO_REUSEADDR, true)
                    .childOption(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .childOption(ChannelOption.SO_SNDBUF, 32 * 1024)
                    .childOption(EpollChannelOption.SO_REUSEPORT, true)
                    .childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);

            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new HttpInboundInitializer(proxyServers));
            Channel ch = b.bind(port).sync().channel();
            System.out.println("开启netty http服务器，监听地址和端口为 http://127.0.0.1:" + port + '/');
            ch.closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}
