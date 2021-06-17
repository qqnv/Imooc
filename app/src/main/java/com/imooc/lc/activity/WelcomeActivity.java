package com.imooc.lc.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.Window;

import com.imooc.lc.MainActivity;
import com.imooc.lc.R;

import androidx.annotation.Nullable;

/**
 * @类名: WelcomeActivity
 * @描述:
 * @作者: huangchao
 * @时间: 2018/7/17 上午9:37
 * @版本: 1.0.0
 */
public class WelcomeActivity extends Activity {

    private static final long DELAY_TIME = 2000L;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //判断是否是第一次进入应用
        SharedPreferences sharedPreferences=this.getSharedPreferences("share",MODE_PRIVATE);
        boolean isFirstRun=sharedPreferences.getBoolean("isFirstRun", true);
        SharedPreferences.Editor editor=sharedPreferences.edit();
//        isFirstRun = true;
        //若第一次进入应用则跳转到有引导页的界面
        if (isFirstRun){
            startActivity(new Intent(WelcomeActivity.this, WelcomeGuideActivity.class));
            finish();
            editor.putBoolean("isFirstRun", false);
            editor.commit();
            return;
        }
        //若不是第一次，进入普通页面
        setContentView(R.layout.activity_welcome);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
                finish();
            }
        },DELAY_TIME);
    }

    /**
     * 屏蔽物理返回按钮
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
