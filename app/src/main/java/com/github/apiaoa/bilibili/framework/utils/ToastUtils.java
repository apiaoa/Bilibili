package com.github.apiaoa.bilibili.framework.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.github.apiaoa.bilibili.R;
import com.github.apiaoa.bilibili.framework.AppContext;


public class ToastUtils {
    private static Toast toast;
    private static LayoutInflater inflater;

    static {
        if (null == inflater) {
            inflater = (LayoutInflater) AppContext.context.getSystemService(
                    Context.LAYOUT_INFLATER_SERVICE);
        }
    }


    public static void showLoading() {
        View view = inflater.inflate(R.layout.loading_layout, null);

        if (toast != null) {
            toast.cancel();
        }

        toast = new Toast(AppContext.context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }

    public static void completed() {
        if (null != toast) {
            toast.cancel();
            toast = null;
        }
    }

    public static void showShort(final String msg) {
        View view = inflater.inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(msg);

        if (toast != null) {
            toast.cancel();
        }

        toast = new Toast(AppContext.context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showShort(final int resId, final String msg) {
        View view = inflater.inflate(R.layout.toast_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(resId);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(msg);

        if (toast != null) {
            toast.cancel();
        }

        toast = new Toast(AppContext.context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showShort(final int msgId) {
        View view = inflater.inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(msgId);

        if (toast != null) {
            toast.cancel();
        }

        toast = new Toast(AppContext.context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showShort(final int resId, final int msgId) {
        View view = inflater.inflate(R.layout.toast_layout, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.image_view);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(resId);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(msgId);

        if (toast != null) {
            toast.cancel();
        }

        toast = new Toast(AppContext.context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();
    }

    public static void showLong(final String msg) {
        View view = inflater.inflate(R.layout.toast_layout, null);
        TextView textView = (TextView) view.findViewById(R.id.text_view);
        textView.setText(msg);

        if (toast != null) {
            toast.cancel();
        }

        toast = new Toast(AppContext.context);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setView(view);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.show();
    }
}
