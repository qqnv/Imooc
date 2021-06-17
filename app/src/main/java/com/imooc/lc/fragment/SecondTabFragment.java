package com.imooc.lc.fragment;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.imooc.lc.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @类名: SecondTabFragment
 * @描述:
 * @作者: huangchao
 * @时间: 2018/7/16 上午11:58
 * @版本: 1.0.0
 */
public class SecondTabFragment extends Fragment {

    private View view;

    private TextView tv_fragment;

    public static SecondTabFragment newInstance(String str){
        SecondTabFragment secondTabFragment = new SecondTabFragment();
        Bundle bundle = new Bundle();
        bundle.putString("text",str);
        secondTabFragment.setArguments(bundle);
        return secondTabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_third_tab,container,false);
        initView();
        return view;
    }

    private void initView() {
        tv_fragment = view.findViewById(R.id.tv_fragment);
        tv_fragment.setText(getArguments().getString("text"));
    }
}
