package com.dzg.project.utils;

import com.alibaba.fastjson.JSONObject;
import lombok.Builder;
import lombok.Data;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author dingzhenggang
 */
@Data
@Builder
public class OkHttpUtil {

  private static final Logger logger = LoggerFactory.getLogger(OkHttpUtil.class);

  private static Integer timeout = 5000000;

  public OkHttpUtil() {
  }

  private static OkHttpClient okHttpClient = (OkHttpClient) SpringContextUtils.getBean("okHttpClient");

  /**
   * OkHttpPost请求
   *
   * @param api
   * @param param
   * @return
   */
  public static String okhttpPost(String api, JSONObject param) {
    String object = null;
    try {
      RequestBody body = RequestBody.create(MediaType.parse("application/json"),
              param.toJSONString());
      Request request = new Request.Builder().post(body)
              .url(api).build();
      Response response = okHttpClient.newCall(request).execute();
      object = response.body().string();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (object == null) {
      throw new IllegalArgumentException(api + " 请求失败");
    }

    return object;
  }

  /**
   * OkHttpGet请求
   *
   * @param api
   * @return
   */
  public static String okhttpGet(String api) {
    String object = null;
    try {
      Request request = new Request.Builder().get()
              .url(api).build();
      Response response = okHttpClient.newCall(request).execute();
      object = response.body().string();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (object == null) {
      throw new IllegalArgumentException(api + " 请求失败");
    }

    return object;
  }

  /**
   * OkHttpPut请求
   *
   * @param api
   * @param param
   * @return
   */
  public static String okhttpPut(String api, JSONObject param) {
    String object = null;
    try {
      RequestBody body = RequestBody.create(MediaType.parse("application/json"),
              param.toJSONString());
      Request request = new Request.Builder().put(body)
              .url(api).build();
      Response response = okHttpClient.newCall(request).execute();
      object = response.body().string();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (object == null) {
      throw new IllegalArgumentException(api + " 请求失败");
    }

    return object;
  }


  /**
   * OkHttpDelete请求
   *
   * @param api
   * @param param
   * @return
   */
  public static String okhttpDelete(String api, JSONObject param) {
    String object = null;
    try {
      RequestBody body = RequestBody.create(MediaType.parse("application/json"),
              param.toJSONString());
      Request request = new Request.Builder().delete(body)
              .url(api).build();
      Response response = okHttpClient.newCall(request).execute();
      object = response.body().string();
    } catch (Exception e) {
      e.printStackTrace();
    }
    if (object == null) {
      throw new IllegalArgumentException(api + " 请求失败");
    }

    return object;
  }


}
