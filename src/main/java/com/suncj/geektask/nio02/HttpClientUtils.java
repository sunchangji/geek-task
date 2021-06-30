package com.suncj.geektask.nio02;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Classname HttpClientUtils
 * @Description TODO
 * @Date 2021/6/30 10:24 下午
 * @Created by sunchangji
 */
public class HttpClientUtils {

    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    // GET 调用
    public static String getAsString(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = httpclient.execute(httpGet);
        try {
            System.out.println(response.getStatusLine());
            HttpEntity entity1 = response.getEntity();
            return EntityUtils.toString(entity1, "UTF-8");
        } finally {
            response.close();
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:8801";
        String text = getAsString(url);
        System.out.println("url: " + url + " ; response: \n" + text);
    }
}
