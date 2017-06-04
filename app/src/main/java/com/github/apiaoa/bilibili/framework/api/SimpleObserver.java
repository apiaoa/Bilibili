package com.github.apiaoa.bilibili.framework.api;

import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.github.apiaoa.bilibili.framework.utils.LogUtils;
import com.github.apiaoa.bilibili.framework.utils.NetUtils;
import com.github.apiaoa.bilibili.framework.utils.ToastUtils;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;


public abstract class SimpleObserver<T> implements Observer<T> {


    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        LogUtils.e("err", e.toString());
        String msg;
        if (e instanceof ServerException) {
            // 服务器异常
            msg = e.getMessage();
        } else if (e instanceof NullPointerException) {
            onNullData();
            return;
        } else if (e instanceof UnknownHostException) {
            msg = "没有网络,请检查您的网络设置后再重新尝试...";
        } else if (e instanceof SocketTimeoutException) {
            // 超时
            msg = "请求超时，请稍后重试...";
        } else {
            msg = "请求失败，请稍后重试...";
        }
        if (!NetUtils.isAvailable()) {
            msg = "没有网络,请检查您的网络设置后再重新尝试...";
        }
        onFailure(msg);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {
        LogUtils.d(t.toString());
        onSuccess(t);
    }

    @Override
    public void onComplete() {

    }

    public void onNullData() {
    }

    public void onFailure(String msg) {
        if (!TextUtils.isEmpty(msg)) ToastUtils.showShort(msg);
    }

    public abstract void onSuccess(@NonNull T data);


}