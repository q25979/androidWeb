package com.yisuan.yisuan001;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HttpClient {

    // 基础URL
    public Retrofit builer() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://nihao.gxfc.3132xycp.com/")
                .addConverterFactory(GsonConverterFactory.create(getGson()))
                .build();

        return retrofit;
    }

    // 配置Gson
    private Gson getGson() {
        Gson gson = new GsonBuilder()
                .create();

        return gson;
    }
}
