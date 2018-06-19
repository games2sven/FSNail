package com.jlkf.fsnail.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlkf.fsnail.R;

import java.util.ArrayList;
import java.util.List;

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

        setClickListener();
    }

    private void setClickListener() {
        lastPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int   page =currentPage-1;
                if (page>=1){
                    currentPage--;
                    setCurrentPage(currentPage);

                    if (onPageIndexListener!=null){
                        onPageIndexListener.onLastClick();
                    }
                }
                }

        });
        nextPage.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int   page =currentPage+1;
                if (page<=totalPage){
                    currentPage++;
                    setCurrentPage(currentPage);
                    if (onPageIndexListener!=null){
                        onPageIndexListener.onNextClick();
                    }
                }

            }
        });
        page1.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPageIndexListener!=null){
                    onPageIndexListener.onIndexClick(Integer.parseInt(page1.getText().toString()));
                }
            }
        });
        page2.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPageIndexListener!=null){
                    onPageIndexListener.onIndexClick(Integer.parseInt(page2.getText().toString()));
                }
            }
        });
        page3.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPageIndexListener!=null){
                    onPageIndexListener.onIndexClick(Integer.parseInt(page3.getText().toString()));
                }
            }
        });
        page4.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPageIndexListener!=null){
                    onPageIndexListener.onIndexClick(Integer.parseInt(page4.getText().toString()));
                }
            }
        });

    }

    int  currentPage=1;
    int  totalPage;

    public int getCurrentPage() {
        return currentPage;
    }


    private void  setSelectImage(int index){
        page1.setBackgroundResource(index==1?R.drawable.index_number_select_bg:R.drawable.index_number_unselect_bg);
        page2.setBackgroundResource(index==2?R.drawable.index_number_select_bg:R.drawable.index_number_unselect_bg);
        page3.setBackgroundResource(index==3?R.drawable.index_number_select_bg:R.drawable.index_number_unselect_bg);
        page4.setBackgroundResource(index==4?R.drawable.index_number_select_bg:R.drawable.index_number_unselect_bg);

        page1.setTextColor(index==1? Color.WHITE:Color.parseColor("#333333"));
        page2.setTextColor(index==2?Color.WHITE:Color.parseColor("#333333"));
        page3.setTextColor(index==3?Color.WHITE:Color.parseColor("#333333"));
        page4.setTextColor(index==4?Color.WHITE:Color.parseColor("#333333"));

         lastPage.setImageResource(currentPage==1?R.mipmap.page_left_gray:R.mipmap.page_left_black);
          nextPage.setImageResource(currentPage==totalPage?R.mipmap.page_right_gray:R.mipmap.page_right_black);
    }

    public  void  selectText(int  first){
        int dex=first;
        page1.setText(String.valueOf(dex));
        dex++;
        page2.setText(String.valueOf(dex));
        dex++;
        page3.setText(String.valueOf(dex));
        dex++;
        page4.setText(String.valueOf(dex));

    }

    int selectPosition=1;
    List<String> indexList = new ArrayList<>();
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
        indexList.clear();
        if (currentPage==1){
            selectPosition=1;
            selectText(1);
        }else if (currentPage==2||totalPage-currentPage>=2){
            selectPosition=2;
            selectText(currentPage-1);
        }else if(totalPage>=4&&totalPage==currentPage){
            selectPosition=4;
            selectText(currentPage-3);
        }else {
            selectPosition=3;
            selectText(currentPage-2);
        }
        setSelectImage(currentPage);



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

    public OnPageIndexListener getOnPageIndexListener() {
        return onPageIndexListener;
    }

    public void setOnPageIndexListener(OnPageIndexListener onPageIndexListener) {
        this.onPageIndexListener = onPageIndexListener;
    }

    OnPageIndexListener onPageIndexListener;

    public  interface   OnPageIndexListener{
        void onLastClick();
        void  onNextClick();
        void  onIndexClick(int  page);
    }
}
