package com.fz.liangcang.utils;

import com.fz.liangcang.interfaces.OnGetNetListener;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.Map;

import okhttp3.Call;

/**
 * @FileName:com.fz.mybaisi.utils.GetNet.java
 * @author：方志
 * @date: 2016-12-29 17:00
 * @QQ：459119626
 * @微信：15549433151
 * @function <联网请求>
 */

public class GetNet {

    public static void getNetData(String url, Map<String, String> map, final OnGetNetListener onGetNetListener) {

        OkHttpUtils.get().url(url).build()
                .execute(new StringCallback() {

                    @Override
                    public void onResponse(String response, int id) {
                        if (onGetNetListener != null) {
                            onGetNetListener.onSuccess(response);
                        }
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        if (onGetNetListener != null) {
                            onGetNetListener.onError(e);
                        }
                    }
                });

    }

}