package com.fz.liangcang.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 作用：缓存工具类
 */
public class CacheUtils {
    /**
     * 保持boolean类型数据
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences("fz",Context.MODE_PRIVATE);
        sp.edit().putBoolean(key,value).commit();

    }

    /**
     * 得到boolean类型数据
     * @param context
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences("fz",Context.MODE_PRIVATE);
        return sp.getBoolean(key,false);
    }



    /**
     * 得到缓存的String类型数据
     * @param mContext
     * @param key
     * @return
     */
    public static String getString(Context mContext, String key) {
        SharedPreferences sp = mContext.getSharedPreferences("fz",Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }


    /**
     * 缓存String类型的数据
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        SharedPreferences sp = context.getSharedPreferences("fz",Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();

    }
}
