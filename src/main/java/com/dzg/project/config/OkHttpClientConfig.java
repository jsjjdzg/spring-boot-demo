package com.dzg.project.config;

import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * OkHttpClient请求Config
 *
 * @author dingzhenggang
 * @since V1.0 2019-05-10
 */
@Configuration
public class OkHttpClientConfig {

  @Bean
  public OkHttpClient okHttpClient() {
    return new OkHttpClient().newBuilder().connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS).writeTimeout(15, TimeUnit.SECONDS)
            .build();
  }
}
