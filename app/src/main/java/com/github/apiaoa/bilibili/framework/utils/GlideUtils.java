package com.github.apiaoa.bilibili.framework.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;


/**
 * Created by sunny on 17-3-9.
 */

public class GlideUtils {
    public static void display(Context context, String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .asBitmap()
                .into(imageView);
    }
}
