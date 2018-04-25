package com.tudou.common.util;

import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    private static final int TIME_OUT = 20 * 1000;

    private static final int MAX_TOTAL = 200;
    private static final int MAX_PER_ROUTE = 40;

    private static void config(HttpRequestBase httpRequestBase) {
        // 配置请求的超时设置
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectionRequestTimeout(TIME_OUT)
                .setConnectTimeout(TIME_OUT)
                .setSocketTimeout(TIME_OUT).build();
        httpRequestBase.setConfig(requestConfig);
    }

    private static CloseableHttpClient createHttpClient() {
        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                .register("http", PlainConnectionSocketFactory.getSocketFactory())
                .register("https", SSLConnectionSocketFactory.getSocketFactory()).build();

        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager(registry);
        // 将最大连接数增加
        connectionManager.setMaxTotal(MAX_TOTAL);
        // 将每个路由基础的连接增加
        connectionManager.setDefaultMaxPerRoute(MAX_PER_ROUTE);

        // 请求重试处理
        HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
            @Override
            public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                // 如果已经重试了5次，就放弃
                if (executionCount >= 5) {
                    return false;
                }
                // 如果服务器丢掉了连接，那么就重试
                if (exception instanceof NoHttpResponseException) {
                    return true;
                }
                // 不要重试 SSL 握手异常
                if (exception instanceof SSLHandshakeException) {
                    return false;
                }
                // 超时
                if (exception instanceof InterruptedIOException) {
                    return false;
                }
                // 目标服务器不可达
                if (exception instanceof UnknownHostException) {
                    return false;
                }
                // SSL 握手异常
                if (exception instanceof SSLException) {
                    return false;
                }

                HttpRequest request = HttpClientContext.adapt(context).getRequest();
                // 如果请求是幂等的，就再次尝试
                return !(request instanceof HttpEntityEnclosingRequest);
            }
        };

        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setRetryHandler(httpRequestRetryHandler).build();
    }
    private static String handle(HttpRequestBase request, String params) {
        config(request);

        String method = request.getMethod();
        String url = request.getURI().toString();

        long start = System.currentTimeMillis();
        String result = null;
        try (CloseableHttpResponse response = createHttpClient().execute(request, HttpClientContext.create())) {
            HttpEntity entity = response.getEntity();
            result = EntityUtils.toString(entity, "utf-8");
            if (LogUtil.ROOT_LOG.isInfoEnabled()) {
                long ms = System.currentTimeMillis() - start;
                if (U.isBlank(params)) {
                    LogUtil.ROOT_LOG.info("{} 请求({})耗时({})毫秒, 返回({})", method, url, ms, result);
                } else {
                    LogUtil.ROOT_LOG.info("{} 请求({})(param:{})耗时({})毫秒, 返回({})", method, url, params, ms, result);
                }
            }
            EntityUtils.consume(entity);
        } catch (IOException e) {
            if (LogUtil.ROOT_LOG.isInfoEnabled()) {
                LogUtil.ROOT_LOG.info("请求({})异常", method, url, e);
            }
        }
        return result;
    }


    public static String get(String url) {
        return handle(new HttpGet(url), null);
    }
    public static String get(String url, Map<String, Object> params) {
        if (params != null && params.size() > 0) {
            url = U.appendUrl(url) + U.formatParam(params);
        }
        return get(url);
    }

    public static String post(String url, Map<String, Object> params) {
        HttpPost request = postUrl(url, params);
        return handle(request, U.formatParam(params));
    }
    private static HttpPost postUrl(String url, Map<String, Object> params) {
        HttpPost request = new HttpPost(url);

        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            nameValuePairs.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
        }
        request.setEntity(new UrlEncodedFormEntity(nameValuePairs, StandardCharsets.UTF_8));
        return request;
    }

    public static String post(String url, String jsonStr) {
        HttpPost request = postUrl(url, jsonStr.getBytes(StandardCharsets.UTF_8));
        return handle(request, jsonStr);
    }
    private static HttpPost postUrl(String url, byte[] bytes) {
        HttpPost request = new HttpPost(url);
        request.setEntity(new ByteArrayEntity(bytes));
        return request;
    }

    public static String postImage(String url, Map<String, Object> params, String name, InputStream inputStream) {
        HttpPost request = postUrl(url, params);
        request.setEntity(MultipartEntityBuilder.create().setLaxMode().addBinaryBody(name, inputStream).build());
        return handle(request, U.formatParam(params));
    }
}
