package com.suncj.geektask.week03.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * @Classname HttpRequestFilter
 * @Description TODO
 * @Date 2021/7/7 10:03 上午
 * @Created by sunchangji
 */
public interface HttpRequestFilter {
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}
