package com.example.lottery.util;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Iterator;
import java.util.Map;

/**
 * Http请求工具类，当springboot启动的时候需要一定时间，所以第一次启动的延迟时间最好长一些。（未使用）
 */
public class HttpConnectionUtil {

    // post请求
    public static final String HTTP_POST = "POST";

    // get请求
    private static final String HTTP_GET = "GET";

    // utf-8字符编码
    private static final String CHARSET_UTF_8 = "utf-8";

    //gbk编码格式
    private static final String CHARSET_GBK = "GBK";

    // HTTP内容类型。如果未指定ContentType，默认为TEXT/HTML
    public static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

    // HTTP内容类型。相当于form表单的形式，提交暑假
    private static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

    // 请求超时时间
    private static final int SEND_REQUEST_TIME_OUT = 50000;

    // 将读超时时间
    private static final int READ_TIME_OUT = 50000;

    /**
     * @param requestType 请求类型
     * @param urlStr      请求地址
     * @param body        请求发送内容
     * @return 返回内容
     */
    public static String requestMethod(String requestType, String urlStr, String body, String charset) {

        // 是否有http正文提交
        boolean isDoInput = false;
        if (body != null && body.length() > 0)
            isDoInput = true;
        OutputStream outputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuilder resultBuilder = new StringBuilder();
        String tempLine = null;
        try {
            // 统一资源
            URL url = new URL(urlStr);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在
            // http正文内，因此需要设为true, 默认情况下是false;
            if (isDoInput) {
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setRequestProperty("Content-Length", String.valueOf(body.length()));
            }
            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);
            // 设置一个指定的超时值（以毫秒为单位）
            httpURLConnection.setConnectTimeout(SEND_REQUEST_TIME_OUT);
            // 将读超时设置为指定的超时，以毫秒为单位。
            httpURLConnection.setReadTimeout(READ_TIME_OUT);
            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);
            // 设置字符编码
            httpURLConnection.setRequestProperty("Accept-Charset", CHARSET_UTF_8);
            // 设置内容类型
            httpURLConnection.setRequestProperty("Content-Type", CONTENT_TYPE_FORM_URL);
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod(requestType);

            // 打开到此 URL 引用的资源的通信链接（如果尚未建立这样的连接）。
            // 如果在已打开连接（此时 connected 字段的值为 true）的情况下调用 connect 方法，则忽略该调用。
            httpURLConnection.connect();

            if (isDoInput) {
                outputStream = httpURLConnection.getOutputStream();
                outputStreamWriter = new OutputStreamWriter(outputStream);
                outputStreamWriter.write(body);
                outputStreamWriter.flush();// 刷新
            }
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                //此处设置返回的数据编码格式。
                inputStreamReader = new InputStreamReader(inputStream, charset);
                reader = new BufferedReader(inputStreamReader);

                while ((tempLine = reader.readLine()) != null) {
                    resultBuilder.append(tempLine);
                    resultBuilder.append("\n");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {// 关闭流

            try {
                if (outputStreamWriter != null) {
                    outputStreamWriter.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStreamReader != null) {
                    inputStreamReader.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return resultBuilder.toString();
    }

    /**
     * 将map集合的键值对转化成：key1=value1&key2=value2 的形式
     *
     * @param parameterMap 需要转化的键值对集合
     * @return 字符串
     */
    public static String convertStringParamter(Map parameterMap) {
        StringBuilder parameterBuilder = new StringBuilder();
        if (parameterMap != null) {
            Iterator iterator = parameterMap.keySet().iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = (String) iterator.next();
                if (parameterMap.get(key) != null) {
                    value = (String) parameterMap.get(key);
                } else {
                    value = "";
                }
                parameterBuilder.append(key).append("=").append(value);
                if (iterator.hasNext()) {
                    parameterBuilder.append("&");
                }
            }
        }
        return parameterBuilder.toString();
    }

    /**
     * 测试main方法
     *
     * @param args
     * @throws MalformedURLException
     */
    public static void main(String[] args) throws MalformedURLException {

        System.out.println(requestMethod(HTTP_GET, "http://a.apiplus.net/newly" +
                ".do?token=t9bc5205d023876b3k&code=bjpk10&format=json", null, "GBK"));
//        System.out.println(requestMethod(HTTP_GET, "http://q.stock.sohu
// .com/hisHq?code=cn_600728&start=20170828&end=20170828",null,"UTF-8"));

    }
}
