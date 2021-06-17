package com.imooc.lc.fragment;

import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.imooc.lc.R;
import com.imooc.lc.activity.CommunicActivity;
import com.imooc.lc.activity.LayoutManagerActivity;
import com.imooc.lc.activity.LuckyPanActivity;
import com.imooc.lc.activity.ServiceActivity;
import com.imooc.lc.activity.VoiceActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @类名: FirstTabFragment
 * @描述:
 * @作者: huangchao
 * @时间: 2018/6/21 上午11:42
 * @版本: 1.0.0
 */
public class FirstTabFragment extends Fragment implements View.OnClickListener {

    private View view;
    //通信跳转按钮
    private Button btn_tx;
    //抽奖跳转按钮
    private Button btn_cj;
    //炫酷图片布局按钮
    private Button btn_bj;
    //语音转文字按钮
    private Button btn_voice;
    //服务按钮
    private Button btn_service;

    public static FirstTabFragment newInstance(){
        FirstTabFragment firstTabFragment = new FirstTabFragment();
        return firstTabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_first_tab,container,false);
        initView();
        return view;
    }

    /**
     * 初始化组件
     */
    private void initView() {
        btn_tx = view.findViewById(R.id.btn_tx);
        btn_cj = view.findViewById(R.id.btn_cj);
        btn_bj = view.findViewById(R.id.btn_bj);
        btn_voice = view.findViewById(R.id.btn_voice);
        btn_service = view.findViewById(R.id.btn_service);
        btn_tx.setOnClickListener(this);
        btn_cj.setOnClickListener(this);
        btn_bj.setOnClickListener(this);
        btn_voice.setOnClickListener(this);
        btn_service.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tx:
                startActivity(new Intent(getActivity(), CommunicActivity.class));
                break;
            case R.id.btn_cj:
                startActivity(new Intent(getActivity(), LuckyPanActivity.class));
                break;
            case R.id.btn_bj:
                startActivity(new Intent(getActivity(), LayoutManagerActivity.class));
                break;
            case R.id.btn_voice:
                startActivity(new Intent(getActivity(), VoiceActivity.class));
                break;
            case R.id.btn_service:
                startActivity(new Intent(getActivity(), ServiceActivity.class));
                break;
        }
    }
}
