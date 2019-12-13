package com.yisuan.yisuan001;

import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.hss01248.dialog.StyledDialog;
import com.hss01248.dialog.interfaces.MyDialogListener;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;

public class WebActive extends AppCompatActivity {
    private AgentWeb mAgentWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_active);
        hideAction();
        StyledDialog.init(this);

        String url = StringUrl.url;
        LinearLayout llView = findViewById(R.id.web_active);
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llView, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator(-1, 0)
                .createAgentWeb()
                .ready()
                .go(url);
        mAgentWeb.getAgentWebSettings().getWebSettings().setUseWideViewPort(true);
        mAgentWeb.getAgentWebSettings().getWebSettings().setLoadWithOverviewMode(true);
    }

    @Override
    public boolean onKeyDown(final int keyCode, final KeyEvent event) {
        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
            return true;
        } else {
            if(keyCode == KeyEvent.KEYCODE_BACK){
                StyledDialog.buildIosAlert("提示", "确定要退出吗？", new MyDialogListener() {
                    @Override
                    public void onFirst() {
                        finish();
                        moveTaskToBack(true);
                    }

                    @Override
                    public void onSecond() {
                    }

                }).setBtnSize(14).setBtnText("确定", "取消").show();

                return true; // 不执行父类点击事件
            }

            return super.onKeyDown(keyCode, event);
        }
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
        StatusBarUtil.setColor(WebActive.this, getResources().getColor(R.color.themeColor), 1);
    }

    @Override
    protected void onPause() {
        mAgentWeb.getWebLifeCycle().onPause();
        super.onPause();

    }

    @Override
    protected void onResume() {
        mAgentWeb.getWebLifeCycle().onResume();
        super.onResume();
    }
}
