package com.fz.liangcang.application;


import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;


/**
 * 作者：尚硅谷-杨光福 on 2016/12/13 09:23
 * 微信：yangguangfu520
 * QQ号：541433511
 * 作用：Application
 */
public class MyApplication extends Application {

    public static Context context;
    public static Handler handler;
    public static Thread mainThread;
    public static int mainThreadId;

    @Override
    public void onCreate() {
        super.onCreate();

        //okhttp
        initOkhttpUtils();

//        //初始化ShareSDK
//        ShareSDK.initSDK(this);

    }

    private void initOkhttpUtils() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .addInterceptor(new LoggerInterceptor("TAG"))
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);
    }
}
