package com.github.apiaoa.bilibili.framework.api;


import com.github.apiaoa.bilibili.entity.discover.AllareasRankInfo;
import com.github.apiaoa.bilibili.entity.discover.OriginalRankInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by hcc on 2016/9/22 18:40
 * 100332338@qq.com
 * <p>
 * 排行榜相关api
 */

public interface RankService {

  /**
   * 原创排行榜请求
   */
  @GET("index/rank/{type}")
  Observable<OriginalRankInfo> getOriginalRanks(@Path("type") String type);

  /**
   * 全区排行榜数据请求
   */
  @GET("index/rank/{type}")
  Observable<AllareasRankInfo> getAllareasRanks(@Path("type") String type);
}
