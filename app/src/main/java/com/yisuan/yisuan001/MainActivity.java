package com.yisuan.yisuan001;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;

import com.jaeger.library.StatusBarUtil;

public class MainActivity extends AppCompatActivity {
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideAction();

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
