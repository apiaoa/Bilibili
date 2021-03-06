package com.github.apiaoa.bilibili.framework.api;



import com.github.apiaoa.bilibili.entity.user.UserChaseBangumiInfo;
import com.github.apiaoa.bilibili.entity.user.UserCoinsInfo;
import com.github.apiaoa.bilibili.entity.user.UserContributeInfo;
import com.github.apiaoa.bilibili.entity.user.UserPlayGameInfo;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hcc on 2016/10/12 22:40
 * 100332338@qq.com
 * <p>
 * 用户相关api
 */

public interface UserService {

  /**
   * 用户所玩游戏
   */
  @GET("ajax/game/GetLastPlay")
  Observable<UserPlayGameInfo> getUserPlayGames(@Query("mid") int mid);

  /**
   * 用户投币视频
   */
  @GET("ajax/member/getCoinVideos")
  Observable<UserCoinsInfo> getUserCoinVideos(@Query("mid") int mid);

  /**
   * 用户追番
   */
  @GET("ajax/Bangumi/getList")
  Observable<UserChaseBangumiInfo> getUserChaseBangumis(@Query("mid") int mid);

  /**
   * 用户投稿视频
   */
  @GET("ajax/member/getSubmitVideos")
  Observable<UserContributeInfo> getUserContributeVideos(
          @Query("mid") int mid, @Query("page") int page, @Query("pagesize") int pageSize);
}
