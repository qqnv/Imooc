package com.imooc.lc.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dingmouren.layoutmanagergroup.skidright.SkidRightLayoutManager;
import com.imooc.lc.MainActivity;
import com.imooc.lc.R;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @类名: SkidRightFragment
 * @描述:
 * @作者: huangchao
 * @时间: 2018/7/17 下午5:49
 * @版本: 1.0.0
 */
public class SkidRightFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SkidRightLayoutManager mSkidRightLayoutManager;
    MainActivity mainActivity = (MainActivity) getActivity();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_skidright,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        mRecyclerView = view.findViewById(R.id.rv_skidRight);
        mSkidRightLayoutManager = new SkidRightLayoutManager(1.5f,0.85f);
        mRecyclerView.setLayoutManager(mSkidRightLayoutManager);
        mRecyclerView.setAdapter(new MyAdapter());
    }
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

        private int[] imgs = {
                R.mipmap.skid_right_1,R.mipmap.skid_right_2,R.mipmap.skid_right_3,
                R.mipmap.skid_right_4,R.mipmap.skid_right_5,R.mipmap.skid_right_6,
                R.mipmap.skid_right_7
        };

        String[] titles = {
                "One","Two","Three","Four","Five","Six","Seven"
        };
        int[] contents = {
          R.string.skid1,R.string.skid2,R.string.skid3,R.string.skid4,
                R.string.skid5,R.string.skid6,R.string.skid7
        };

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_skidright,
                    parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            Glide.with(getContext()).load(imgs[position%7]).asGif()
                    .diskCacheStrategy(DiskCacheStrategy.SOURCE).into(holder.iv_skid);
            holder.tv_skidTitle.setText(titles[position%7]);
            holder.tv_skidContent.setText(contents[position%7]);
            holder.iv_skid.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.e("mmp","跳转");

                    SkidRightFragment1 skidRightFragment1 = new SkidRightFragment1();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.add(R.id.fragment_skit,skidRightFragment1);
                    transaction.addToBackStack(null);
                    transaction.commit();

                    Bundle bundle = new Bundle();
                    bundle.putInt("img",imgs[position%7]);
                    bundle.putString("title",titles[position%7]);

                    Pair<View, String> p1 = Pair.create((View)holder.iv_skid,"iv_skid1");
                    Pair<View, String> p2 = Pair.create((View)holder.tv_skidTitle,"tv_skidTitle1");
                    Pair<View, String> p3 = Pair.create((View)holder.tv_skidContent,"tv_skidContent1");
//                    ActivityOptionsCompat optionsCompat = ActivityOptionsCompat
//                            .makeSceneTransitionAnimation(getContext(),p1,p2,p3);

                }
            });
        }

        @Override
        public int getItemCount() {
            return 100;
        }


        public class ViewHolder extends RecyclerView.ViewHolder{

            ImageView iv_skid;
            TextView tv_skidTitle;
            TextView tv_skidContent;

            public ViewHolder(View itemView) {
                super(itemView);
                iv_skid = itemView.findViewById(R.id.iv_skid);
                tv_skidTitle = itemView.findViewById(R.id.tv_skidTitle);
                tv_skidContent = itemView.findViewById(R.id.tv_skidContent);
            }
        }
    }
}
