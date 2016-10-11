package com.shibo.zzz.swiperefreshlayoutdemo;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by zzz on 2016/9/12.
 */
public class MyItemDecoration extends RecyclerView.ItemDecoration {
    /**
     *
     * 设置item的边距
     * @param view
     * @param parent
     * @param state
     */


    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
    }

    /**
     *
     * 渲染前调用
     * @param parent
     * @param state
     */

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
    }

    /**
     *
     * 渲染后调用
     * @param parent
     * @param state
     */

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }
}
