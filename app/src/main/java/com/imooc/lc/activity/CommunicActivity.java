package com.imooc.lc.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.imooc.lc.R;
import com.imooc.lc.model.MessageEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import androidx.annotation.Nullable;

/**
 * @类名: CommunicActivity
 * @描述:
 * @作者: huangchao
 * @时间: 2018/6/19 下午4:16
 * @版本: 1.0.0
 */
public class CommunicActivity extends Activity {

    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communic);
        initView();
        init();
    }

    private void initView() {
        mButton = findViewById(R.id.btn_button);
        mTextView = findViewById(R.id.tv_text);
        mTextView.setText("今天周一");
        EventBus.getDefault().register(this);
    }

    private void init() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CommunicActivity.this,SendEventActivity.class);
                startActivity(intent);
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent messageEvent){
        mTextView.setText(messageEvent.getMessage());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().unregister(this);
        }
    }
}
