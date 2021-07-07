package com.suncj.geektask;


import com.google.common.collect.Lists;
import com.suncj.geektask.week03.inbound.HttpInboundServer;

import java.util.List;

public class GeekTaskApplication {
    public final static String GATEWAY_NAME = "NIOGateway";
    public final static String GATEWAY_VERSION = "3.0.0";
    private static int port = 8888;

    /**
     * 这是之前的单个后端url的例子
     * http://localhost:8888/api/hello  ==> gateway API
     * http://localhost:8088/api/hello  ==> backend service
     * java -Xmx512m gateway-server-0.0.1-SNAPSHOT.jar  #作为后端服务
     */
    private static List<String> proxyServers = Lists.newArrayList("http://localhost:8801","http://localhost:8802");

    public static void main(String[] args) {
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" starting...");
        HttpInboundServer server = new HttpInboundServer(port, proxyServers);
        System.out.println(GATEWAY_NAME + " " + GATEWAY_VERSION +" started at http://localhost:" + port + " for server:" + server.toString());
        try {
            server.run();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
