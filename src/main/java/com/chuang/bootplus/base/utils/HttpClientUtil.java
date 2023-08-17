package com.chuang.bootplus.base.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @description: TODO 发请求
 * @author nuo
 * @date 2022/7/28 2:04
 * @version 1.0
 */
public class HttpClientUtil {

    /**
     * @description: get 请求
     * @param uri
     * @return java.lang.String
     * @date: 2022/7/28 2:05
     */
    public static String get(String uri) {
        CloseableHttpClient httpClient = null;
        HttpGet get = new HttpGet(uri);
        CloseableHttpResponse response = null;
        String res = null;

        try {
            // 建一个客户端
            httpClient = HttpClients.createDefault();
            // 执行 get 请求, 获取请求的 resp
            response = httpClient.execute(get);
            int statusCode = response.getStatusLine().getStatusCode();
            // 请求成功 -> 返回请求结果
            if (statusCode == 200) {
                HttpEntity entity = response.getEntity();
                res = EntityUtils.toString(entity, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return res;
    }
}
