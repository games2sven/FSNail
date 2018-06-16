package com.jlkf.fsnail.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jlkf.fsnail.utils.Utils;


/**
 * Created by Administrator on 2018/4/16 0016.
 */

public class SpaceItemDecoration extends RecyclerView.ItemDecoration{
    private int mSpace;
    private  int  mTopvalue;
    public SpaceItemDecoration(Context context, int dpValue, int topValue) {
        mSpace = Utils.dp2px(dpValue,context);
        mTopvalue = Utils.dp2px(topValue,context);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//            if(parent.getChildAdapterPosition(view) > 0) {
        //从第二个条目开始，距离左边tem的距离

//            }
        outRect.left = mSpace;
        outRect.top = mTopvalue;
//        if (mSpace>0) {
//            if (parent.getChildAdapterPosition(view) % 4!= 0) {
//                outRect.left = mSpace;
//            }
//        }
    }


}