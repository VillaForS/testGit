/**
 * Copyright (C), 2012-2016, 江苏中地集团有限公司
 * Author:   LG
 * Date:     2016年9月28日 上午9:23:08
 * Description: //模块目的、功能描述      
 */
package com.maiyajf.base.utils.remote.http;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.http.Header;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

/**
 * 〈一句话功能简述〉<br>
 * 〈功能详细描述〉
 *
 * @author LG
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Component
@Lazy(false)
public class LongTimeRestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LongTimeRestClient.class);

    private static RestTemplate longTimeRestTemplate;

    static {
        // 创建连接池
        PoolingHttpClientConnectionManager pollingConnectionManager = new PoolingHttpClientConnectionManager();
        // 总连接数
        pollingConnectionManager.setMaxTotal(1000);
        // 同路由的并发数
        pollingConnectionManager.setDefaultMaxPerRoute(200);

        HttpClientBuilder httpClientBuilder = HttpClients.custom();
        httpClientBuilder.setConnectionManager(pollingConnectionManager);
        // 重试次数，默认是3次，没有开启
        httpClientBuilder.setRetryHandler(new DefaultHttpRequestRetryHandler(0, false));
        // 保持长连接配置，需要在头添加Keep-Alive
        //httpClientBuilder.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());

        List<Header> headers = new ArrayList<>();
        headers.add(new BasicHeader("User-Agent",
                "Mozilla/5.0 (Windows NT 6.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.16 Safari/537.36"));
        headers.add(new BasicHeader("Accept-Encoding", "gzip,deflate"));
        headers.add(new BasicHeader("Accept-Language", "zh-CN"));
        headers.add(new BasicHeader("Connection", "Keep-Alive"));

        httpClientBuilder.setDefaultHeaders(headers);

        HttpClient httpClient = httpClientBuilder.build();

        // httpClient连接配置，底层是配置RequestConfig
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory(
                httpClient);
        // 连接超时
        clientHttpRequestFactory.setConnectTimeout(5000);
        // 数据读取超时时间，即SocketTimeout
        clientHttpRequestFactory.setReadTimeout(100000);
        // 连接不够用的等待时间，不宜过长，必须设置，比如连接不够用时，时间过长将是灾难性的
        clientHttpRequestFactory.setConnectionRequestTimeout(200);
        // 缓冲请求数据，默认值是true。通过POST或者PUT大量发送数据时，建议将此属性更改为false，以免耗尽内存。
        clientHttpRequestFactory.setBufferRequestBody(false);

        // 添加内容转换器
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        messageConverters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
        messageConverters.add(new FormHttpMessageConverter());
        // messageConverters.add(new MappingJackson2XmlHttpMessageConverter());
        // messageConverters.add(new MappingJackson2HttpMessageConverter());

        longTimeRestTemplate = new RestTemplate(messageConverters);
        longTimeRestTemplate.setRequestFactory(clientHttpRequestFactory);
        longTimeRestTemplate.setErrorHandler(new DefaultResponseErrorHandler());

        LOGGER.info("LongTimeRestClient初始化完成");
    }

    private LongTimeRestClient() {

    }

    @PostConstruct
    public static RestTemplate getClient() {
        return longTimeRestTemplate;
    }
}
