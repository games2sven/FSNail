package com.jlkf.fsnail.net;

import android.util.Log;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.Iterator;
import java.util.Map;

/**
 * Created by chengbin on 2016/4/21.
 * OKHttp抽取的工具类，方便调用
 * modify by dingchenghao 服务器升级，仅在注册、登录、找回密码时需要用到sessid，无需请求GET_HEADER，用到该类的地方需要用到HeaderCallback
 */
public class OKHttpUtils {

  public static OKHttpUtils myOKHttpUtils;

  public static OKHttpUtils getIntance() {
    if (myOKHttpUtils == null) {
      myOKHttpUtils = new OKHttpUtils();
    }
    return myOKHttpUtils;
  }

  /**
   * 无参数的get请求
   */
  public void oKHttpGet(final String url, final Object tag, final MyHttpCallback myCallback) {
    excuteOKHttpGet(url, tag, myCallback);
  }

  private void excuteOKHttpGet(String url, Object tag, MyHttpCallback myCallback) {
    try {
      OkHttpUtils.get()
          .url(url)
          .tag(tag)
          .build()
          .execute(myCallback);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 有参数的get请求，需签名
   */
  public void oKHttpGetParam(final String url, final Object tag, final Map<String, String> params,
                            final MyHttpCallback mCallback) {
    excuteOKHttpGetParam(url, tag, params, mCallback);
  }

  private void excuteOKHttpGetParam(String url, Object tag,
                                  Map<String, String> params, final MyHttpCallback mCallback) {

    try {
      String param = getMapParamStr(params);
      String  urls=url+param;
      Log.e("url",urls);
      OkHttpUtils.get()
          .url(url + param)
          .tag(tag)
          .build()
          .execute(mCallback);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * post请求
   */
  public void oKHttpPost(final String url, final Object tag, final Map<String, String> params,
                         final MyHttpCallback mCallback) {
    exOKHttpPost(url, tag, params, mCallback);
  }

  private  void exOKHttpPost(String url, Object tag, Map<String, String> params,
                           MyHttpCallback mMyCallback) {
    try {
      OkHttpUtils.post()
          .url(url)
          .tag(tag)
          .params(params)
          .build()
          .execute(mMyCallback);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static String getMapParamStr(Map<String, String> map) {
    StringBuilder builder = new StringBuilder();
    Iterator<Map.Entry<String, String>> iter = map.entrySet().iterator();
    Map.Entry<String, String> entry;
    while (iter.hasNext()) {
      entry = iter.next();
      try {
        builder.append('&');
        builder.append(entry.getKey());
        builder.append('=');
        builder.append(entry.getValue());

      } catch (Exception e) {
                e.printStackTrace();
      }
    }
    return builder.substring(1, builder.length());
  }



  public void cancelTag(Object tag) {
    OkHttpUtils.getInstance().cancelTag(tag);
  }
}
