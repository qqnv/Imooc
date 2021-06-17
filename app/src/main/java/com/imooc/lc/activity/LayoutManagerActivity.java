package com.imooc.lc.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.imooc.lc.R;
import com.imooc.lc.fragment.BannerFragment;
import com.imooc.lc.fragment.EchelonFragment;
import com.imooc.lc.fragment.PickerFragment;
import com.imooc.lc.fragment.SkidRightFragment;
import com.imooc.lc.fragment.SlideFragment;
import com.imooc.lc.fragment.ViewPagerFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

/**
 * @类名: LayoutManagerActivity
 * @描述: 炫酷LayoutManager
 * @作者: huangchao
 * @时间: 2018/7/17 下午3:41
 * @版本: 1.0.0
 */
public class LayoutManagerActivity extends AppCompatActivity {

    private TextView tv_title;
    //标题头
    private Toolbar mToolbar;
    //fragment管理器
    private FragmentManager mFragmentManager;
    //所有的fragment集合
    private List<Fragment> mFragments= new ArrayList<>();
    //fragment对应的标题头名称
    private List<String> mFragmentNames = new ArrayList<>();
    private Fragment mCurrentFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout_manager);
        initView();
        initFragment();
    }

    private void initView() {
        mToolbar = findViewById(R.id.tb_toolbar);
        setSupportActionBar(mToolbar);
        tv_title = findViewById(R.id.tv_title);
        mFragmentManager = getSupportFragmentManager();
    }

    private void initFragment(){

        EchelonFragment echelonFragment = new EchelonFragment();
        mFragments.add(echelonFragment);
        mFragmentNames.add("EchelonFragment");

        PickerFragment pickerFragment = new PickerFragment();
        mFragments.add(pickerFragment);
        mFragmentNames.add("PickerFragment");

        SlideFragment slideFragment = new SlideFragment();
        mFragments.add(slideFragment);
        mFragmentNames.add("SlideFragment");

        SkidRightFragment skidRightFragment =  new SkidRightFragment();
        mFragments.add(skidRightFragment);
        mFragmentNames.add("SkidRightFragment");

        BannerFragment bannerFragment = new BannerFragment();
        mFragments.add(bannerFragment);
        mFragmentNames.add("BannerFragment");

        ViewPagerFragment viewPagerFragment = new ViewPagerFragment();
        mFragments.add(viewPagerFragment);
        mFragmentNames.add("ViewPagerFragment");

        mFragmentManager.beginTransaction()
                .add(R.id.container_layout,mFragments.get(0))
                .add(R.id.container_layout,mFragments.get(1))
                .add(R.id.container_layout,mFragments.get(2))
                .add(R.id.container_layout,mFragments.get(3))
                .add(R.id.container_layout,mFragments.get(4))
                .add(                                                                                                                                         R.id.container_layout,mFragments.get(5))
                .hide(mFragments.get(5))
                .hide(mFragments.get(4))
                .hide(mFragments.get(3))
                .hide(mFragments.get(2))
                .hide(mFragments.get(1))
                .show(mFragments.get(0))
                .commit();
        mCurrentFragment = mFragments.get(0);
        tv_title.setText(mFragmentNames.get(0));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.item_0:
                switchFragment(0);
                break;
            case R.id.item_1:
                switchFragment(1);
                break;
            case R.id.item_2:
                switchFragment(2);
                break;
            case R.id.item_3:
                switchFragment(3);
                break;
            case R.id.item_4:
                switchFragment(4);
                break;
            case R.id.item_5:
                switchFragment(5);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void switchFragment(int position) {
        mFragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .hide(mCurrentFragment)
                .show(mFragments.get(position))
                .commit();
        mCurrentFragment = mFragments.get(position);
        tv_title.setText(mFragmentNames.get(position));
    }
}
