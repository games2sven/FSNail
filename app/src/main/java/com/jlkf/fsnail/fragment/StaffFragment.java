package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.adapter.StaffAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.StaffBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.dialog.SingleFunctionPop;
import com.jlkf.fsnail.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/30 0030.
 */

public class StaffFragment  extends BaseFragment {
    @Bind(R.id.iv_staff_more)
    ImageView iv_staff_more;
    @Bind(R.id.recylerview)
    RecyclerView recyclerView;
    List<StaffBean> mDatas =new ArrayList<>();
    private StaffAdapter mAdapter;

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {


    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootview =LayoutInflater.from(getActivity()).inflate(R.layout.fragment_staff,null);
        ButterKnife.bind(this,rootview);

        initRecyclerView();
        return rootview;

    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),4));
        mAdapter = new StaffAdapter(mDatas);
        recyclerView.setAdapter(mAdapter);
        recyclerView.addItemDecoration(new SpaceItemDecoration(getContext(),20,20));
       mAdapter.setOnStaffClickListener(new StaffAdapter.OnStaffClickListener() {
           @Override
           public void onClick() {

           }
       });

    }
    SingleFunctionPop pop;
    boolean  isShowMore;
    @OnClick(R.id.iv_staff_more)
    public   void  clickStaffMore(){

        isShowMore = !isShowMore;
        iv_staff_more.setImageResource(isShowMore ? R.mipmap.btn_more_red2 : R.mipmap.btn_more_red1);
         pop  =new SingleFunctionPop(0,getActivity(),iv_staff_more);
        pop.setOnDismissListener(new SingleFunctionPop.OnDismissListener() {
            @Override
            public void onDismiss() {
                isShowMore = false;
                iv_staff_more.setImageResource(R.mipmap.btn_more_red1);
            }
        });
        pop.setOnMoreClickListener(new SingleFunctionPop.OnMoreClickListener() {
            @Override
            public void searchAll() {
                pop.dissMiss();

            }
        });

        pop.showPop();
    }
}
