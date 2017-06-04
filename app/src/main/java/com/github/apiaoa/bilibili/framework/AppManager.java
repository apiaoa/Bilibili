package com.github.apiaoa.bilibili.framework;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Stack;

/**
 * Created by a176 on 2017/4/24.
 */

public class AppManager {
    private static Stack<Activity> activityStack;

    private static class LazyHolder {
        private static AppManager INSTANCE = new AppManager();
    }

    private AppManager() {
    }

    /**
     * 单一实例
     */
    public static AppManager getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        return activityStack.lastElement();
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
  /*  public void removeActivity() {
        removeActivity(activityStack.lastElement());
    }*/

    /**
     * 移除指定的Activity
     */
    public void removeActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
        }
    }

    /**
     * 移除指定类名的Activity
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void removeActivity(Class<?> cls) {
        activityStack.stream().filter(activity -> activity.getClass().equals(cls)).forEach(this::removeActivity);
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    public static void startActivity(Class clazz) {
        Intent intent = new Intent(getInstance().currentActivity(), clazz);
        getInstance().currentActivity().startActivity(intent);
    }

}
