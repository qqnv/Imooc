package com.imooc.lc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imooc.lc.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @类名: ThirdTabFragment
 * @描述:
 * @作者: huangchao
 * @时间: 2018/6/21 上午11:43
 * @版本: 1.0.0
 */
public class ThirdTabFragment extends Fragment {

    public static ThirdTabFragment newInstance(){
        ThirdTabFragment thirdTabFragment = new ThirdTabFragment();
        return thirdTabFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_second_tab,container,false);
        return view;
    }
}
