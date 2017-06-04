package com.github.apiaoa.bilibili.framework;

import android.app.Application;
import android.content.Context;

import com.github.apiaoa.bilibili.framework.api.Api;


/**
 * Created by Sunny on 2016/11/7.
 */
public class AppContext extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        Api.init(this);

    }

}
