package com.jlkf.fsnail.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Lenovo on 2018/6/18.
 */

public class PageIndexView extends LinearLayout {
    public PageIndexView(Context context) {
        super(context);
        initView(context);
    }

    public PageIndexView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PageIndexView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }



    ImageView  lastPage,nextPage;
    TextView  page1,page2,page3,page4;
    public void initView(Context context){

      View view=    LayoutInflater.from(context).inflate(R.layout.layout_index,this);
        lastPage=view.findViewById(R.id.page_last);
        nextPage=view.findViewById(R.id.page_next);
        page1 =view.findViewById(R.id.page_1);
        page2 =view.findViewById(R.id.page_2);
        page3 =view.findViewById(R.id.page_3);
        page4 =view.findViewById(R.id.page_4);
    }

    int  currentPage=1;
    int  totalPage;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        if (totalPage>=4) {

            if (currentPage == totalPage) {
                page1.setText(String.valueOf(totalPage-3));
                page2.setText(String.valueOf(totalPage-2));
                page3.setText(String.valueOf(totalPage-1));
                page4.setText(String.valueOf(totalPage));
            }
        }

    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
        if (totalPage==3){
              lastPage.setVisibility(View.VISIBLE);
              nextPage.setVisibility(View.VISIBLE);
              page1.setVisibility(View.VISIBLE);
              page2.setVisibility(View.VISIBLE);
              page3.setVisibility(View.VISIBLE);
              page4.setVisibility(View.GONE);
        }else if(totalPage==2){
            lastPage.setVisibility(View.VISIBLE);
            nextPage.setVisibility(View.VISIBLE);
            page1.setVisibility(View.VISIBLE);
            page2.setVisibility(View.VISIBLE);
            page3.setVisibility(View.GONE);
            page4.setVisibility(View.GONE);

        }else if (totalPage==1){
            lastPage.setVisibility(View.VISIBLE);
            nextPage.setVisibility(View.VISIBLE);
            page1.setVisibility(View.VISIBLE);
            page2.setVisibility(View.GONE);
            page3.setVisibility(View.GONE);
            page4.setVisibility(View.GONE);

        }else if (totalPage==0){
            lastPage.setVisibility(View.GONE);
            nextPage.setVisibility(View.GONE);
            page1.setVisibility(View.GONE);
            page2.setVisibility(View.GONE);
            page3.setVisibility(View.GONE);
            page4.setVisibility(View.GONE);

        }else {
            lastPage.setVisibility(View.VISIBLE);
            nextPage.setVisibility(View.VISIBLE);
            page1.setVisibility(View.VISIBLE);
            page2.setVisibility(View.VISIBLE);
            page3.setVisibility(View.VISIBLE);
            page4.setVisibility(View.VISIBLE);
        }
    }
}
