package com.imooc.lc.fragment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.View.MeasureSpec;

import com.dingmouren.layoutmanagergroup.echelon.ItemViewInfo;

import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import androidx.recyclerview.widget.RecyclerView.LayoutParams;
import androidx.recyclerview.widget.RecyclerView.Recycler;
import androidx.recyclerview.widget.RecyclerView.State;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class EchelonLayoutManager1 extends LayoutManager {

    private static final String TAG = "EchelonLayoutManager";
    private Context mContext;
    private int mItemViewWidth;
    private int mItemViewHeight;
    private int mItemCount;
    private int mScrollOffset = 2147483647;
    private float mScale = 0.9F;

    public EchelonLayoutManager1(Context context) {
        this.mContext = context;
        this.mItemViewWidth = (int)((float)this.getHorizontalSpace() * 0.87F);
        this.mItemViewHeight = (int)((float)this.mItemViewWidth * 1.46F);
    }

    public LayoutParams generateDefaultLayoutParams() {
        return new LayoutParams(-2, -2);
    }

    public void onLayoutChildren(Recycler recycler, State state) {
        if (state.getItemCount() != 0 && !state.isPreLayout()) {
            this.removeAndRecycleAllViews(recycler);
            this.mItemViewWidth = (int)((float)this.getHorizontalSpace() * 0.87F);
            this.mItemViewHeight = (int)((float)this.mItemViewWidth * 1.46F);
            this.mItemCount = this.getItemCount();
            this.mScrollOffset = Math.min(Math.max(this.mItemViewHeight, this.mScrollOffset), this.mItemCount * this.mItemViewHeight);
            this.layoutChild(recycler);
        }
    }

    public int scrollVerticallyBy(int dy, Recycler recycler, State state) {

        Log.e("dy-->",""+dy);
        int pendingScrollOffset = this.mScrollOffset + dy;
        this.mScrollOffset = Math.min(Math.max(this.mItemViewHeight, this.mScrollOffset + dy), this.mItemCount * this.mItemViewHeight);
        this.layoutChild(recycler);
        return this.mScrollOffset - pendingScrollOffset + dy;
    }

    public boolean canScrollVertically() {
        return true;
    }

    private void layoutChild(Recycler recycler) {

//        Log.e("verticalspace--->",""+this.getVerticalSpace());
        if (this.getItemCount() != 0) {
            int bottomItemPosition = (int)Math.floor((double)(this.mScrollOffset / this.mItemViewHeight));
            int remainSpace = this.getVerticalSpace() - this.mItemViewHeight;
            Log.e("remainSpace-->",""+remainSpace);
            int bottomItemVisibleHeight = this.mScrollOffset % this.mItemViewHeight;
            float offsetPercentRelativeToItemView = (float)bottomItemVisibleHeight * 1.0F / (float)this.mItemViewHeight;
            ArrayList<ItemViewInfo> layoutInfos = new ArrayList();
            int layoutCount = bottomItemPosition - 1;

            int startPos;
            int i;
            for(startPos = 1; layoutCount >= 0; ++startPos) {
                double maxOffset = (double)((this.getVerticalSpace() - this.mItemViewHeight) / 2) * Math.pow(0.8D, (double)startPos);
//                Log.e("maxOffset-->",""+maxOffset);
                i = (int)((double)remainSpace - (double)offsetPercentRelativeToItemView * maxOffset);
                float scaleXY = (float)(Math.pow((double)this.mScale, (double)(startPos - 1)) * (double)(1.0F - offsetPercentRelativeToItemView * (1.0F - this.mScale)));
                float layoutPercent = (float)i * 1.0F / (float)this.getVerticalSpace();
                ItemViewInfo info = new ItemViewInfo(i, scaleXY, offsetPercentRelativeToItemView, layoutPercent);
                layoutInfos.add(0, info);
                remainSpace = (int)((double)remainSpace - maxOffset);
                if (remainSpace <= 0) {
                    info.setTop((int)((double)remainSpace + maxOffset));
                    info.setPositionOffset(0.0F);
                    info.setLayoutPercent((float)(info.getTop() / this.getVerticalSpace()));
                    info.setScaleXY((float)Math.pow((double)this.mScale, (double)(startPos - 1)));
                    break;
                }

                --layoutCount;
            }

            if (bottomItemPosition < this.mItemCount) {
                layoutCount = this.getVerticalSpace() - bottomItemVisibleHeight;
//                Log.e("-lc-->",""+layoutCount);
                layoutInfos.add((new ItemViewInfo(layoutCount, 1.0F, (float)bottomItemVisibleHeight * 1.0F / (float)this.mItemViewHeight, (float)layoutCount * 1.0F / (float)this.getVerticalSpace())).setIsBottom());
            } else {
                --bottomItemPosition;
            }

            layoutCount = layoutInfos.size();
            startPos = bottomItemPosition - (layoutCount - 1);
            int endPos = bottomItemPosition;
            int childCount = this.getChildCount();

            View view;
            for(i = childCount - 1; i >= 0; --i) {
                view = this.getChildAt(i);
                int pos = this.getPosition(view);
                if (pos > endPos || pos < startPos) {
                    this.removeAndRecycleView(view, recycler);
                }
            }

            this.detachAndScrapAttachedViews(recycler);

            for(i = 0; i < layoutCount; ++i) {
                view = recycler.getViewForPosition(startPos + i);
                ItemViewInfo layoutInfo = (ItemViewInfo)layoutInfos.get(i);
                this.addView(view);
                this.measureChildWithExactlySize(view);
                int left = (this.getHorizontalSpace() - this.mItemViewWidth) / 2;
                this.layoutDecoratedWithMargins(view, left, layoutInfo.getTop(), left + this.mItemViewWidth, layoutInfo.getTop() + this.mItemViewHeight);
                view.setPivotX((float)(view.getWidth() / 2));
                view.setPivotY(0.0F);
                view.setScaleX(layoutInfo.getScaleXY());
                view.setScaleY(layoutInfo.getScaleXY());
            }

        }
    }

    private void measureChildWithExactlySize(View child) {
        @SuppressLint("WrongConstant") int widthSpec = MeasureSpec.makeMeasureSpec(this.mItemViewWidth, 1073741824);
        @SuppressLint("WrongConstant") int heightSpec = MeasureSpec.makeMeasureSpec(this.mItemViewHeight, 1073741824);
        child.measure(widthSpec, heightSpec);
    }

    public int getVerticalSpace() {
        return this.getHeight() - this.getPaddingTop() - this.getPaddingBottom();
    }

    public int getHorizontalSpace() {
        return this.getWidth() - this.getPaddingLeft() - this.getPaddingRight();
    }
}
