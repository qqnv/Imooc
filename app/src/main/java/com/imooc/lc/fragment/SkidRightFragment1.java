package com.imooc.lc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.imooc.lc.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

/**
 * @类名: SkidRightFragment1
 * @描述:
 * @作者: huangchao
 * @时间: 2018/7/20 下午2:09
 * @版本: 1.0.0
 */
public class SkidRightFragment1 extends Fragment {

    private ImageView mImageView;
    private TextView mText;

    private int mImg;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skidright1,container,false);
//        initView(view);
        return view;
    }
    private void initView(View view){
        mImageView = view.findViewById(R.id.iv_skid);
        mText = view.findViewById(R.id.tv_skid1);
        if(getArguments()!=null){
            mImg = getArguments().getInt("img",R.mipmap.skid_right_3);
            mText.setText(getArguments().getString("title"));
            Glide.with(getContext()).load(mImg).asGif()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(mImageView);
        }
    }
}
