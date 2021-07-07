package com.suncj.geektask.week03.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Classname HeaderHttpResponseFilter
 * @Description TODO
 * @Date 2021/7/7 10:04 上午
 * @Created by sunchangji
 */
public class HeaderHttpResponseFilter implements HttpResponseFilter{

    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("resfilter", "java-1-nio");
    }
}
