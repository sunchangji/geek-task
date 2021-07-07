package com.suncj.geektask.week03.inbound;

import com.suncj.geektask.week03.filter.HeaderHttpRequestFilter;
import com.suncj.geektask.week03.filter.HttpRequestFilter;
import com.suncj.geektask.week03.outbound.httpclinet.HttpOutboundHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

import java.util.List;

/**
 * @Classname HttpInboundHandler
 * @Description TODO
 * @Date 2021/7/7 10:00 上午
 * @Created by sunchangji
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {
    private final List<String> proxyServer;
    private HttpOutboundHandler handler;
    private HttpRequestFilter filter = new HeaderHttpRequestFilter();

    public HttpInboundHandler(List<String> proxyServer) {
        this.proxyServer = proxyServer;
        this.handler = new HttpOutboundHandler(this.proxyServer);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        try {
            FullHttpRequest fullRequest = (FullHttpRequest) msg;
            handler.handle(fullRequest, ctx, filter);
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            ReferenceCountUtil.release(msg);
        }
    }
}
