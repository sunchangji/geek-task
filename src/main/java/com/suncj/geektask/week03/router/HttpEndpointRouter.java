package com.suncj.geektask.week03.router;

import java.util.List;

/**
 * @Classname HttpEndpointRouter
 * @Description TODO
 * @Date 2021/7/7 10:05 上午
 * @Created by sunchangji
 */
public interface HttpEndpointRouter {

    String route(List<String> endpoints);
}
