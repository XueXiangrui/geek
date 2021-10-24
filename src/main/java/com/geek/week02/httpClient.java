package com.geek.week02;


import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by xiangrui.xue on 2021/10/11.
 */
public class httpClient {
    public static CloseableHttpClient httpclient = HttpClients.createDefault();

    // GET 调用
    public static String getAsString(String url) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response1 = null;
        try {
            response1 = httpclient.execute(httpGet);
            System.out.println(response1.getStatusLine());
            HttpEntity entity1 = response1.getEntity();
            return EntityUtils.toString(entity1, "UTF-8");
        } finally {
            if (null != response1) {
                response1.close();
            }
        }
    }

    /**
     * 第二周作业
     * 6.（必做）写一段代码，使用 HttpClient 或 OkHttp 访问 http://localhost:8801
     */

    public static void fangwen(){
        try {
            getAsString("http://localhost:8801");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}