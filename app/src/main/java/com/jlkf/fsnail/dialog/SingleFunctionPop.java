package com.jlkf.fsnail.dialog;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/25 0025.
 */

public class SingleFunctionPop {

    private final int type;
    private final View showView;
    private final Context context;
    private PopupWindow mPopupWindow;
    private OnDismissListener listenter;
    private int popupHeight, popupWidth;
    private OnMoreClickListener moreClickListener;

    public SingleFunctionPop(int type, Context context, View showView) {
        this.type = type;
        this.context = context;
        this.showView = showView;
    }

    View parentView;

    public void showPop() {
        if (showView == null) return;
        dissMiss();
        LayoutInflater LayoutInflater =
                android.view.LayoutInflater.from(context);
        parentView = LayoutInflater.inflate(R.layout.pop_single_function, null);
        mPopupWindow = new PopupWindow(parentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, true);
        setPopConfig();

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (listenter != null) {
                    listenter.onDismiss();
                }
            }
        });
        parentView.findViewById(R.id.iv_search_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moreClickListener != null) {
                    dissMiss();
                    moreClickListener.searchAll();
                }
            }
        });
        showUp2(showView);
    }

    /**
     * 设置显示在v上方(以v的左边距为开始位置)
     *
     * @param v
     */
    public void showUp(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        Log.e("position", location[0] + "     " + location[1]);
        //在控件上方显示
        mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0] - popupWidth / 2, location[1] - popupHeight);
    }

    /**
     * 设置显示在v上方（以v的中心位置为开始位置）
     *
     * @param v
     */
    public void showUp2(View v) {
        //获取需要在其上方显示的控件的位置信息
        int[] location = new int[2];
        v.getLocationOnScreen(location);
        //在控件上方显示
        mPopupWindow.showAtLocation(v, Gravity.NO_GRAVITY, (location[0] + v.getWidth() / 2) - popupWidth / 2, location[1] - popupHeight);
    }

    private void setPopConfig() {
//        this.setContentView(mDataView);//设置要显示的视图
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置弹出窗体可点击
        mPopupWindow.setFocusable(true);
        mPopupWindow.setBackgroundDrawable(new BitmapDrawable());
        mPopupWindow.setOutsideTouchable(true);// 设置外部触摸会关闭窗口

        //获取自身的长宽高
        parentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        popupHeight = parentView.getMeasuredHeight();
        popupWidth = parentView.getMeasuredWidth();
    }

    public void dissMiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }


    public void setOnDismissListener(OnDismissListener listener) {
        this.listenter = listener;
    }

    public void setOnMoreClickListener(OnMoreClickListener moreClickListener) {
        this.moreClickListener = moreClickListener;
    }

    public interface OnMoreClickListener {
        void searchAll();
    }

    public interface OnDismissListener {
        void onDismiss();
    }

}
