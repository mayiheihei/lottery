package com.example.lottery.utils;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Httpclient工具类（正在使用）
 *
 * @author : 唐明
 * @date : Created in 17:05 2017/9/4
 * @modified By:
 */
public class HttpClientUtil {
    //测试方法 //TODO: 测试方法
    public static void main(String[] args) {
        String host = "a.apiplus.net/newly.do?token=t9bc5205d023876b3k&code=bjpk10&rows=1&format=json";
        System.out.println(HttpGetDate(host));
    }

    public static String HttpGetDate(String host) {
        //创建httpclient实例，采用默认的参数配置
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        HttpEntity ety = null;

        //通过URIBuilder类创建URI
        URI uri = null;
        try {
            uri = new URIBuilder().setScheme("http")
                    .setHost(host)
                    .build();


            HttpGet get = new HttpGet(uri);   //使用Get方法提交

            //请求的参数配置，分别设置连接池获取连接的超时时间，连接上服务器的时间，服务器返回数据的时间
            RequestConfig config = RequestConfig.custom()
                    .setConnectionRequestTimeout(3000)
                    .setConnectTimeout(3000)
                    .setSocketTimeout(3000)
                    .build();
            //配置信息添加到Get请求中
            get.setConfig(config);
            //通过httpclient的execute提交 请求 ，并用CloseableHttpResponse接受返回信息
            response = httpClient.execute(get);
            //服务器返回的状态
            int statusCode = response.getStatusLine().getStatusCode();
            //判断返回的状态码是否是200 ，200 代表服务器响应成功，并成功返回信息
            //获取实体
            ety = response.getEntity();
            if (statusCode == HttpStatus.SC_OK) {

                //EntityUtils 获取返回的信息。官方不建议使用使用此类来处理信息
                return EntityUtils.toString(ety, Consts.UTF_8);

            } else {
                System.out.println("Demo.example -------->" + "获取信息失败");
            }
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert response != null;
                EntityUtils.consume(ety);//释放实体
                response.close();
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

