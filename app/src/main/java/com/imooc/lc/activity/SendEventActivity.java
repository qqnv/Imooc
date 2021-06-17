package com.imooc.lc.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.imooc.lc.model.MessageEvent;
import com.imooc.lc.R;

import org.greenrobot.eventbus.EventBus;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @类名: SendEventActivity
 * @描述:
 * @作者: huangchao
 * @时间: 2018/6/11 下午4:22
 * @版本: 1.0.0
 */
public class SendEventActivity extends AppCompatActivity {

    private Button mButton;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_event);
        initView();
        init();
    }

    private void initView() {
        mButton = findViewById(R.id.btn_button2);
    }

    private void init() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("又是❤️的一周的开始"));
                finish();
            }
        });
    }

}
