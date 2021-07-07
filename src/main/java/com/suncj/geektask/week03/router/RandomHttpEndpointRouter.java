package com.suncj.geektask.week03.router;

import java.util.List;
import java.util.Random;

/**
 * @Classname RandomHttpEndpointRouter
 * @Description TODO
 * @Date 2021/7/7 10:05 上午
 * @Created by sunchangji
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter{

    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        return urls.get(random.nextInt(size));
    }
}
