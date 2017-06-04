package com.github.apiaoa.bilibili.framework.utils;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.github.apiaoa.bilibili.framework.AppContext;


/**
 * Created by Sunny on 2016/11/7.
 */

public class DisplayUtils {
    public static int width, height;

    static {
        if (0 == width) {
            width = AppContext.context.getResources().getDisplayMetrics().widthPixels;
            height = AppContext.context.getResources().getDisplayMetrics().heightPixels;
        }
    }

    public static int dp2px(float dipValue) {
        final float scale = AppContext.context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    public static int sp2px(float spValue) {
        final float scale = AppContext.context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * scale + 0.5f);
    }

    //设置屏幕背景透明度
    public static void backgroundAlpha(Activity activity, float alpha) {
        WindowManager.LayoutParams params = activity.getWindow().getAttributes();
        params.alpha = alpha;
        activity.getWindow().setAttributes(params);
    }

    public static void clip(Context context, String content) {
        ClipboardManager manager = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clipData = ClipData.newPlainText("Simple", content);
        manager.setPrimaryClip(clipData);
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     */
    public static void hideKeyboard(Context context, View view) {
        InputMethodManager manager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static String replaceToEnter(String source) {
        String string = source.replace(";", "\n");
        string = string.replace("；", "\n");

        return string;
    }

    public static int getInfoItemHeight() {
        return DisplayUtils.width * 13 / 36;
    }
}
