package com.jlkf.fsnail.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by Lenovo on 2018/6/18.
 */

public class PageIndexView extends LinearLayout {
    public PageIndexView(Context context) {
        super(context);
    }

    public PageIndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PageIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @SuppressLint("NewApi")
    public PageIndexView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
