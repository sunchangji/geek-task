package com.suncj.geektask.week03.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * @Classname HttpResponseFilter
 * @Description TODO
 * @Date 2021/7/7 10:03 上午
 * @Created by sunchangji
 */
public interface HttpResponseFilter {
    void filter(FullHttpResponse response);
}
