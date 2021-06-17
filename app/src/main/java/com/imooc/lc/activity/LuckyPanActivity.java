package com.imooc.lc.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.imooc.lc.R;
import com.imooc.lc.view.LuckyPan;

import androidx.annotation.Nullable;

/**
 * @类名: LuckyPanActivity
 * @描述:
 * @作者: huangchao
 * @时间: 2018/6/20 上午9:38
 * @版本: 1.0.0
 */
public class LuckyPanActivity extends Activity {

    private LuckyPan mLuckyPan;

    private ImageView iv_start;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lucky_pan);
        initView();
        init();
    }

    private void initView() {
        mLuckyPan = findViewById(R.id.luckyPan);
        iv_start = findViewById(R.id.iv_start);
    }

    private void init() {
        iv_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mLuckyPan.isStart()){
                    mLuckyPan.luckyStart(1);
                    iv_start.setImageResource(R.mipmap.btn_end);
                }else {
                    if (!mLuckyPan.isShouldEnd()){
                        mLuckyPan.luckyEnd();
                        iv_start.setImageResource(R.mipmap.btn_start);
                    }
                }
            }
        });
    }
}
