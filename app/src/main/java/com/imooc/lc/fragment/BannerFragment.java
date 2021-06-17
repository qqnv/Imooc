package com.imooc.lc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imooc.lc.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @类名: BannerFragment
 * @描述:
 * @作者: huangchao
 * @时间: 2018/7/17 下午5:50
 * @版本: 1.0.0
 */
public class BannerFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_banner,container,false);
        return view;
    }
}
