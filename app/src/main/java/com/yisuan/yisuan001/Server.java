package com.yisuan.yisuan001;

import com.yisuan.yisuan001.bean.Real;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Server {
    // 获取真实url
    @GET("lottery/back/api.php")
    Call<Real> getRealUrl(@Query("type") String type, @Query("show_url") int show_url, @Query("appid") int appid);
}
