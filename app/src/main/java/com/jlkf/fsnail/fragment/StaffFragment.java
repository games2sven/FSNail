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
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.StaffBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.SingleFunctionPop;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.widget.SpaceItemDecoration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    List<StaffBean.DataBean> mDatas =new ArrayList<>();
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

        initNet();
        return rootview;

    }

    private void initNet() {
    getStaffList();

    }


 String      id;// 编号查员工
    String   name;//姓名查员工（是姓名不是昵称）
   int  pageNo=1;// 页码
    int  pageSize= 10;//页面大小

    boolean  isSelectAll=true;
    private void getStaffList() {
        Map<String,String> parmas = new HashMap<>();
        if (!isSelectAll) {
            addParams(parmas, "today", "1");
        }
        addParams(parmas,"id",id);
        addParams(parmas,"name",name);
        addParams(parmas,"pageNo", String.valueOf(pageNo));
        addParams(parmas,"pageSize", String.valueOf(pageSize));
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.TODAY_WORK, this, parmas, new MyHttpCallback<StaffBean>() {
            @Override
            public void onSuccess(StaffBean response) {
                  if (response.getCode()==200){
                      if (pageNo==1){
                          mDatas.clear();
                      }
                      mDatas.addAll(response.getData());
                      mAdapter.notifyDataSetChanged();
                  }
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });

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
