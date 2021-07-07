package com.suncj.geektask.week03.inbound;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

import java.util.List;

/**
 * @Classname HttpInboundInitializer
 * @Description TODO
 * @Date 2021/7/7 9:59 上午
 * @Created by sunchangji
 */
public class HttpInboundInitializer extends ChannelInitializer<SocketChannel> {
    private List<String> proxyServer;

    public HttpInboundInitializer(List<String> proxyServer) {
        this.proxyServer = proxyServer;
    }

    @Override
    protected void initChannel(SocketChannel ch) {
        ChannelPipeline p = ch.pipeline();
        p.addLast(new HttpServerCodec());
        p.addLast(new HttpObjectAggregator(1024 * 1024));
        p.addLast(new HttpInboundHandler(this.proxyServer));
    }
}
