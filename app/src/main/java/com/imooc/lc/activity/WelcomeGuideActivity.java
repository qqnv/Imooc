package com.imooc.lc.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.imooc.lc.MainActivity;
import com.imooc.lc.R;
import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;

import java.util.ArrayList;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @类名: WelcomeGuideActivity
 * @描述: 首次进入系统时的引导页
 * @作者: huangchao
 * @时间: 2018/7/17 上午9:58
 * @版本: 1.0.0
 */
public class WelcomeGuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //隐藏系统状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.onboarding_main_layout);
        PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(R.id.onboardingRootView),
                getDataForOnboarding(),getApplicationContext());
        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                startActivity(new Intent(WelcomeGuideActivity.this, MainActivity.class));
                finish();
            }
        });
    }

    /**
     * 引导页的具体内容
     * @return
     */
    public ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        PaperOnboardingPage paperOnboardingPage01 = new PaperOnboardingPage("LOVE",
                "最好的年纪，却再也遇不到最好的你", Color.parseColor("#678FB4"),
                R.mipmap.hotels,R.mipmap.key);
        PaperOnboardingPage paperOnboardingPage02 = new PaperOnboardingPage("LIKE",
                "一见你就笑的人，不是傻子就是喜欢你",Color.parseColor("#65B0B4"),
                R.mipmap.banks,R.mipmap.wallet);
        PaperOnboardingPage paperOnboardingPage03 = new PaperOnboardingPage("MISS",
                "有些人错过了就是错过了，一辈子都不会再遇到了",Color.parseColor("#9B90BC"),
                R.mipmap.stores,R.mipmap.shopping_cart);
        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(paperOnboardingPage01);
        elements.add(paperOnboardingPage02);
        elements.add(paperOnboardingPage03);
        return elements;
    }
}
