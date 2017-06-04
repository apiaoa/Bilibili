package com.github.apiaoa.bilibili.framework.utils;

import android.util.Log;

/**
 * Created by Sunny on 2016/9/19.
 */
public class LogUtils {

    public static final boolean is_release = false;

    private static final String TAG = "yy";

    public static void i(String msg) {
        if (!is_release) {
            Log.i(TAG, msg);
        }
    }

    public static void v(String msg) {
        if (!is_release) {
            Log.v(TAG, msg);
        }
    }

    public static void e(String msg) {
        if (!is_release) {
            Log.e(TAG, msg);
        }
    }
    public static void e(String tag, String msg) {
        if (!is_release) {
            Log.e(tag, msg);
        }
    }

    public static void w(String msg) {
        if (!is_release) {
            Log.w(TAG, msg);
        }
    }

    public static void d(String msg) {
        if (!is_release) {
            Log.d(TAG, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (!is_release) {
            Log.d(tag, msg);
        }
    }
}
