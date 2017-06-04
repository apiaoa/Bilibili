package com.github.apiaoa.bilibili.framework.api;

import android.content.Context;

import com.github.apiaoa.bilibili.framework.utils.LogUtils;
import com.github.apiaoa.bilibili.framework.utils.NetUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Sunny on 2016/11/7.
 */
public class Api {

    private static Retrofit retrofit;


    public static <T> T createApi(Class<T> clazz, String baseUrl) {
        return retrofit.newBuilder().baseUrl(baseUrl).build().create(clazz);
    }

    public static LiveService getLiveAPI() {

        return createApi(LiveService.class, ApiConstants.LIVE_BASE_URL);
    }


    public static BiliAppService getBiliAppAPI() {

        return createApi(BiliAppService.class, ApiConstants.APP_BASE_URL);
    }


    public static BiliApiService getBiliAPI() {

        return createApi(BiliApiService.class, ApiConstants.API_BASE_URL);
    }


    public static BiliGoService getBiliGoAPI() {

        return createApi(BiliGoService.class, ApiConstants.BILI_GO_BASE_URL);
    }


    public static RankService getRankAPI() {

        return createApi(RankService.class, ApiConstants.RANK_BASE_URL);
    }


    public static UserService getUserAPI() {

        return createApi(UserService.class, ApiConstants.USER_BASE_URL);
    }


    public static VipService getVipAPI() {

        return createApi(VipService.class, ApiConstants.VIP_BASE_URL);
    }


    public static BangumiService getBangumiAPI() {

        return createApi(BangumiService.class, ApiConstants.BANGUMI_BASE_URL);
    }


    public static SearchService getSearchAPI() {

        return createApi(SearchService.class, ApiConstants.SEARCH_BASE_URL);
    }


    public static AccountService getAccountAPI() {

        return createApi(AccountService.class, ApiConstants.ACCOUNT_BASE_URL);
    }


    public static Im9Service getIm9API() {

        return createApi(Im9Service.class, ApiConstants.IM9_BASE_URL);
    }


    public static void init(Context context) {
        File httpCacheDirectory = new File(context.getCacheDir(), "HttpCache");
        int cacheSize = 10 * 1024 * 1024;//设置缓存文件大小为10M
        Cache cache = new Cache(httpCacheDirectory, cacheSize);
        OkHttpClient.Builder client = new OkHttpClient().newBuilder()
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new NoNetInterceptor())
                .addNetworkInterceptor(new NetInterceptor())
                .cache(cache);


        /*创建Retrofit实例*/
        retrofit = new Retrofit.Builder()
                .baseUrl("www.baidu.com")
                .client(client.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();


    }

    private static class NetInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();
            LogUtils.i(String.format("%s : %s", request.method(), request.url()));
            Request.Builder b = request.newBuilder();
            Response response = chain.proceed(b.build());
            if (NetUtils.isAvailable()) {
                return response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public ,max-age=0")
                        .build();
            }
            return response;
        }
    }

    /**
     * 在没有网络的情况下，读取缓存数据
     */
    public static class NoNetInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {

            Request request = chain.request();

            if (!NetUtils.isAvailable()) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                Response response = chain.proceed(request);
                return response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=2592000")
                        .removeHeader("Pragma")
                        .build();
            }
            return chain.proceed(request);
        }
    }
}
