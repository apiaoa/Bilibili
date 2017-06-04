package com.github.apiaoa.bilibili.framework.api;

import com.github.apiaoa.bilibili.framework.base.BaseActivity;
import com.github.apiaoa.bilibili.framework.base.BaseFragment;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.android.FragmentEvent;


import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by a176 on 2016/12/29.
 */

public class RxHelper {
    /**
     * 统一线程处理
     */
    public static <T> ObservableTransformer<T, T> io2main() {    //compose简化线程
        return rObservable -> rObservable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public static <T> ObservableTransformer<Wrapper<T>, T> handleResult() {
        return rObservable -> rObservable.flatMap(result -> {
                    if (result.getStatus() == Wrapper.ON_SUCCESS) {
                        T t = result.getData();
                        if (t == null || (t instanceof Iterable && !((Iterable) t).iterator().hasNext()))
                            return Observable.error(new NullPointerException("数据源返回的数据为空"));
                        return Observable.just(t);
                    } else {
                        return Observable.error(new ServerException(result.getMsg()));
                    }
                }
        );
    }

  /*  public static <T> Observable.Transformer<Wrapper<T>, T> handleMainResult() {
        return rObservable -> rObservable.compose(io2main()).compose(handleResult());

    }*/

    public static <T> ObservableTransformer<Wrapper<T>, T> handleMainResult(final BaseActivity activity) {
        return rObservable -> rObservable.compose(activity.bindUntilEvent(ActivityEvent.DESTROY)).compose(io2main()).compose(handleResult());

    }

    public static <T> ObservableTransformer<Wrapper<T>, T> handleMainResult(final BaseFragment fragment) {
        return rObservable -> rObservable.compose(fragment.bindUntilEvent(FragmentEvent.DESTROY_VIEW)).compose(io2main()).compose(handleResult());

    }
}
