package com.imooc.lc.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dingmouren.layoutmanagergroup.picker.PickerLayoutManager;
import com.imooc.lc.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @类名: PickerFragment
 * @描述:
 * @作者: huangchao
 * @时间: 2018/7/17 下午5:49
 * @版本: 1.0.0
 */
public class PickerFragment extends Fragment {

    private RecyclerView mRecyclerView1;
    private RecyclerView mRecyclerView2;
    private TextView mTvHour;
    private TextView mTvMinute;
    private PickerLayoutManager mPickerLayoutManager1;
    private PickerLayoutManager mPickerLayoutManager2;
    private static List<String> mHours = new ArrayList<>();
    private static List<String> mMinutes = new ArrayList<>();

    /**
     * 添加假数据
     */
    static {
        for (int i = 0;i < 24;i++){
            if (i < 10){
                mHours.add("0"+i);
            }else{
                mHours.add(i+"");
            }
        }
        for (int i = 0;i < 60;i++){
            if (i < 10){
                mMinutes.add("0"+i);
            }else{
                mMinutes.add(i+"");
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view = inflater.inflate(R.layout.fragment_picker,container,false);
       initView(view);
       initListener();
       return view;

    }

    private void initView(View view) {

        mRecyclerView1 = view.findViewById(R.id.rv_hour);
        mRecyclerView2 = view.findViewById(R.id.rv_minute);
        mTvHour = view.findViewById(R.id.tv_hour);
        mTvMinute = view.findViewById(R.id.tv_minute);

        mPickerLayoutManager1 = new PickerLayoutManager(getContext(),mRecyclerView1,
                PickerLayoutManager.VERTICAL,false,3,0.4f,true);
        mRecyclerView1.setLayoutManager(mPickerLayoutManager1);
        mRecyclerView1.setAdapter(new MyAdapter(mHours));

        mPickerLayoutManager2 = new PickerLayoutManager(getContext(),mRecyclerView2,
                PickerLayoutManager.VERTICAL,false,3,0.4f,true);
        mRecyclerView2.setLayoutManager(mPickerLayoutManager2);
        mRecyclerView2.setAdapter(new MyAdapter(mMinutes));

    }

    private void initListener(){
        mPickerLayoutManager1.setOnSelectedViewListener(new PickerLayoutManager.OnSelectedViewListener() {
            @Override
            public void onSelectedView(View view, int i) {
                TextView textView = (TextView) view;
                if (textView != null) mTvHour.setText(textView.getText());
            }
        });
        mPickerLayoutManager2.setOnSelectedViewListener(new PickerLayoutManager.OnSelectedViewListener() {
            @Override
            public void onSelectedView(View view, int i) {
                TextView textView = (TextView) view;
                if (textView != null) mTvMinute.setText(textView.getText());
            }
        });
    }

    class MyAdapter extends RecyclerView.Adapter<ViewHolder>{

        private List<String> mList;

        public MyAdapter(List<String> list){
            this.mList = list;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item_picker,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tv_item_text.setText(mList.get(position));
        }

        @Override
        public int getItemCount() {
            return mList.size();
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tv_item_text;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_item_text = itemView.findViewById(R.id.tv_item_text);
        }
    }
}
