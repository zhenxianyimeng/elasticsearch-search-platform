package com.zx.platform.search.core.config;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description:
 *
 * @author: zhenxianyimeng
 * @date: 2019-08-29
 * @time: 20:09
 */
@Configuration
public class HighLevelClientConfiguration {

    @Value("${elasticsearch.host}")
    private String host;

    @Value("${elasticsearch.port}")
    private Integer port;

    @Value("${elasticsearch.scheme}")
    private String scheme;

    @Value("${elasticsearch.connect.timeout}")
    private Integer connectTimeout;

    @Value("${elasticsearch.max.connect.total}")
    private Integer maxConnectTotal;

    @Value("${elasticsearch.username}")
    private String username;

    @Value("${elasticsearch.password}")
    private String password;

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        HttpHost httpHost = new HttpHost(host, port, scheme);
        RestClientBuilder builder =  RestClient.builder(httpHost);
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY,
                new UsernamePasswordCredentials(username, password));
        builder.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
            @Override
            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder builder) {
                builder.setConnectTimeout(connectTimeout);
                return builder;
            }
        });
        builder.setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
            @Override
            public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpAsyncClientBuilder) {
                httpAsyncClientBuilder.setMaxConnTotal(maxConnectTotal);
                httpAsyncClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                return httpAsyncClientBuilder;
            }
        });
        return new RestHighLevelClient(builder);
    }
}
