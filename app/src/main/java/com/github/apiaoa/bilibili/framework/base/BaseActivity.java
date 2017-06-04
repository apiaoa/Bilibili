package com.github.apiaoa.bilibili.framework.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.github.apiaoa.bilibili.MainActivity;
import com.github.apiaoa.bilibili.R;
import com.github.apiaoa.bilibili.framework.AppManager;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.RxLifecycleAndroid;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;


/**
 * Created by Sunny on 2016/11/7.
 * 对于大部分界面,都可以通过全局搜索标题栏名字找到对应的界面!
 */
public abstract class BaseActivity extends AppCompatActivity implements LifecycleProvider<ActivityEvent> {
    protected Context context;
    protected Activity activity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
     if (!(this instanceof MainActivity))  overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
        super.onCreate(savedInstanceState);
        context = this;
        activity = this;
        setStatusBarColor(Color.BLACK);
        AppManager.getInstance().addActivity(this);
        lifecycleSubject.onNext(ActivityEvent.CREATE);

    }

    @Override
    public void finish() {
        super.finish();
        if (!(this instanceof MainActivity)) overridePendingTransition(R.anim.slide_out_left,R.anim.slide_in_right);
    }

    @Override
    public Resources getResources() {
        Resources res = super.getResources();
        Configuration config = new Configuration();
        config.setToDefaults();
        res.updateConfiguration(config, res.getDisplayMetrics());
        return res;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getInstance().removeActivity(this);
        lifecycleSubject.onNext(ActivityEvent.DESTROY);
    }

    private final BehaviorSubject<ActivityEvent> lifecycleSubject = BehaviorSubject.create();

    @Override
    @NonNull
    @CheckResult
    public final Observable<ActivityEvent> lifecycle() {
        return lifecycleSubject.hide();
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindUntilEvent(@NonNull ActivityEvent event) {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event);
    }

    @Override
    @NonNull
    @CheckResult
    public final <T> LifecycleTransformer<T> bindToLifecycle() {
        return RxLifecycleAndroid.bindActivity(lifecycleSubject);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void setStatusBarColor(@ColorInt int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            final Window window = getWindow();
            if (window != null) {
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(color);
            }
        }
    }

    private Disposable disposable;

    public void setWindowAlpha(float toAlpha) {
        if (disposable != null) disposable.dispose();
        WindowManager.LayoutParams params = getWindow().getAttributes();
        float fromAlpha = params.alpha;
        disposable = Observable.interval(10, TimeUnit.MILLISECONDS)
                .take(50)
                .observeOn(AndroidSchedulers.mainThread())
                .compose(bindUntilEvent(ActivityEvent.DESTROY))
                .subscribe(aLong -> {
                    params.alpha = fromAlpha + (toAlpha - fromAlpha) * ((aLong + 1) / 50f);
                    getWindow().setAttributes(params);
                });

    }
}

