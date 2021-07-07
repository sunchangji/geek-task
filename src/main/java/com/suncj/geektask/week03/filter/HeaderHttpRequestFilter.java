package com.suncj.geektask.week03.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Classname HeaderHttpRequestFilter
 * @Description TODO
 * @Date 2021/7/7 10:04 上午
 * @Created by sunchangji
 */
public class HeaderHttpRequestFilter implements HttpRequestFilter{

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("testFilter", "reqfilter");
    }
}
