package com.imooc.lc.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.dingmouren.layoutmanagergroup.echelon.EchelonLayoutManager;
import com.imooc.lc.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @类名: EchelonFragment
 * @描述:
 * @作者: huangchao
 * @时间: 2018/7/17 下午5:48
 * @版本: 1.0.0
 */
public class EchelonFragment extends Fragment {

    private RecyclerView mRecyclerView;

    private EchelonLayoutManager1 mEchelonLayoutManager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_echelon,container,false);
        mRecyclerView = rootView.findViewById(R.id.rv_recyclerView);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    private void initView() {
        mEchelonLayoutManager = new EchelonLayoutManager1(getContext());
        mRecyclerView.setLayoutManager(mEchelonLayoutManager);
        mRecyclerView.setAdapter(new MyAdapter());
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
        //头像数组
        private int[] icons = {R.mipmap.header_icon_1,R.mipmap.header_icon_2,R.mipmap.header_icon_3,
                                R.mipmap.header_icon_4};
        //背景图数组
        private int[] bgs = {R.mipmap.bg_1,R.mipmap.bg_2,R.mipmap.bg_3,R.mipmap.bg_4};
        //作者昵称数组
        private String[] names = {"左耳近心","凉雨初夏","稚久九栀","半窗疏影"};
        //内容数组
        private String[] contents = {
                "回不去的地方叫故乡 没有根的迁徙叫流浪...",
                "人生就像迷宫，我们用上半生找寻入口，用下半生找寻出口",
                "原来地久天长，只是误会一场",
                "不是故事的结局不够好，而是我们对故事的要求过多",
                "只想优雅转身，不料华丽撞墙"
        };

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_echelon,
                    parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder,final int position) {
            holder.mIcon.setImageResource(icons[position%4]);
            holder.mName.setText(names[position%4]);
            holder.mContent.setText(contents[position%4]);
            holder.mBg.setImageResource(bgs[position%4]);
        }

        @Override
        public int getItemCount() {
            return 30;
        }

        public class ViewHolder extends RecyclerView.ViewHolder{

            ImageView mIcon;
            TextView mName;
            TextView mContent;
            ImageView mBg;

            public ViewHolder(View itemView) {
                super(itemView);
                mIcon = itemView.findViewById(R.id.iv_icon);
                mName = itemView.findViewById(R.id.tv_name);
                mContent = itemView.findViewById(R.id.tv_content);
                mBg = itemView.findViewById(R.id.iv_bg);
            }
        }

    }
}
