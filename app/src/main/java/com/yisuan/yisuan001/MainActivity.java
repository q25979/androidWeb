package com.yisuan.yisuan001;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import com.yisuan.yisuan001.bean.Real;

import cn.jpush.android.api.JPushInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         initPush();
         hideAction();
         reqReal();
    }

    // 初始化极光推送
    public void initPush() {
        JPushInterface.setDebugMode(false);
        JPushInterface.init(this);
    }

    // 真实页面
    public void reqReal() {
        HttpClient httpClient = new HttpClient();
        Server server = httpClient.builer().create(Server.class);

        Call<Real> call = server.getRealUrl("android", 1, 20972);
        call.enqueue(new Callback<Real>() {
            @Override
            public void onResponse(Call<Real> call, Response<Real> response) {
                Real real = response.body();

                if (real.getIsWap() == 1) {
                    StringUrl.url = real.getWapUrl();
                }

                // 延时操作
                handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(MainActivity.this, WebActive.class));
                        finish();
                    }
                }, 1000);
            }

            @Override
            public void onFailure(Call<Real> call, Throwable t) {
//                System.out.println("数据接收失败～～～ ");
                t.printStackTrace();
            }
        });
    }

    // 隐藏标题栏
    public void hideAction() {
        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.hide();
        }
        setStatusColor();
    }

    // 设置状态栏颜色
    public void setStatusColor() {
        // 隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
