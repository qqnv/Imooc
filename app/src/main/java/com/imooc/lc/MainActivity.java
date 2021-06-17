package com.imooc.lc;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.imooc.lc.fragment.SecondTabFragment;
import com.imooc.lc.fragment.FirstTabFragment;
import com.imooc.lc.fragment.ThirdTabFragment;
import com.imooc.lc.util.PermissionUtils;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

    TabView tabView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检测读写权限和录音权限
        PermissionUtils.verifyStoragePermissions(this);
        initView();

    }

    /**
     * 底部导航栏填充
     */
    private void initView() {
        tabView = findViewById(R.id.tabView);
        List<TabViewChild> tabViewChildList = new ArrayList<>();
        TabViewChild tabViewChild01 = new TabViewChild(R.mipmap.tab01_sel,R.mipmap.tab01_unsel,"首页", FirstTabFragment.newInstance());
        TabViewChild tabViewChild02 = new TabViewChild(R.mipmap.tab02_sel,R.mipmap.tab02_unsel,"分类", SecondTabFragment.newInstance("分类"));
        TabViewChild tabViewChild03 = new TabViewChild(R.mipmap.tab05_sel,R.mipmap.tab05_unsel,"我的", ThirdTabFragment.newInstance());
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        tabView.setTabViewDefaultPosition(0);
        tabView.setTabViewChild(tabViewChildList,getSupportFragmentManager());
        tabView.setOnTabChildClickListener(new TabView.OnTabChildClickListener() {
            @Override
            public void onTabChildClick(int position, ImageView imageView, TextView textView) {

            }
        });
    }
}
