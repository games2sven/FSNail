package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.CustomerAdapter;
import com.jlkf.fsnail.adapter.ShiftAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.CustomerBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ShiftBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.MoreFunctionsPop;
import com.jlkf.fsnail.dialog.SearchCustomerDialog;
import com.jlkf.fsnail.dialog.SearchServiceDialog;
import com.jlkf.fsnail.dialog.TwoFunctionPop;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.UiUtil;
import com.jlkf.fsnail.widget.PageIndexView;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/28 0028.
 */

public class CustomerFragment  extends BaseFragment {
    MainActivity mainActivity;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;
    @Bind(R.id.recylerview)
     RecyclerView recyclerView;
    @Bind(R.id.page_index_view)
    PageIndexView page_index_view;
    List<CustomerBean.DataBean> mDatas = new ArrayList<>();
    private CustomerAdapter mAdapter;

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        switch (eventCenter.getEventCode()) {
            case Constants.CODE_SEARCH_CUSTOMERLIST:
                refreshCustomerList();
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        mainActivity= (MainActivity) getActivity();
        View view =LayoutInflater.from(mainActivity).inflate(R.layout.fragment_customer,null);
        ButterKnife.bind(this,view);
        initRecyclerView();

        initNet();
        return view;
    }

    private void initNet() {
        getCustomerList();
    }
    String   regStart;  //注册日区间始例：2018/6/1
    String  regEnd  ;//注册日区间终
    String   name;//客户名
    String  phone;//手机
    String startInt;//积分区间
    String endInt;//积分区间
    String birStart;//生日区间始
    String birEnd;//生日区间终
    int    pageNo=1;//页面
    int   pageSize=6;//


    private void clearConditions(){
           regStart=null;  //注册日区间始例：2018/6/1
           regEnd=null  ;//注册日区间终
           name=null;//客户名
          phone=null;//手机
         startInt=null;//积分区间
         endInt=null;//积分区间
         birStart=null;//生日区间始
         birEnd=null;//生日区间终

    }

    private void getCustomerList() {

        Map<String ,String > parmas =new HashMap<>();
        addParams(parmas,"regStart",regStart);
        addParams(parmas,"regEnd",regEnd);
        addParams(parmas,"name",name);
        addParams(parmas,"phone",phone);
        addParams(parmas,"startInt",startInt);
        addParams(parmas,"endInt",endInt);
        addParams(parmas,"birStart",birStart);
        addParams(parmas,"birEnd",birEnd);
        addParams(parmas,"pageNo",pageNo+"");
        addParams(parmas,"pageSize",pageSize+"");


        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.USER_LIST, this, parmas, new MyHttpCallback<CustomerBean>() {
            @Override
            public void onSuccess(CustomerBean response) {
                mDatas.clear();
                Log.e("response",response.toString());
                if (response.getCode()==200){
                    mDatas.addAll(response.getData());

                    page_index_view.setTotalPage(response.getTotalPage());
                    page_index_view.setCurrentPage(pageNo);
                }else{
                    UiUtil.showToast(response.getMsg());
                }

                mAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(String errorMsg) {
                UiUtil.showToast(errorMsg);
            }
        });


    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        mAdapter = new CustomerAdapter(mDatas);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new CustomerAdapter.OnCustomerItemClickListener() {
            @Override
            public void onCustomeEditClick(int position) {
                gotoCustomerEdit(mDatas.get(position));
            }
        });

        page_index_view.setOnPageIndexListener(new PageIndexView.OnPageIndexListener() {
            @Override
            public void onLastClick() {
                pageNo--;
                if (pageNo>0){
                    getCustomerList();
                }
            }

            @Override
            public void onNextClick() {
                pageNo++;
                getCustomerList();
            }

            @Override
            public void onIndexClick(int page) {
                pageNo=page;
                page_index_view.setCurrentPage(page);
                getCustomerList();
            }
        });

    }

    boolean isShowMore;


    @OnClick(R.id.iv_service_more)
    public  void  showMore(){
        isShowMore=!isShowMore;
        iv_service_more.setImageResource(isShowMore ? R.mipmap.btn_more_red2 : R.mipmap.btn_more_red1);

        if (isShowMore) {
            TwoFunctionPop pop  =new  TwoFunctionPop(0,mainActivity,iv_service_more);
            pop.setOnDismissListener(new TwoFunctionPop.OnDismissListener() {
                public void onDismiss() {
                    isShowMore = false;
                    iv_service_more.setImageResource(R.mipmap.btn_more_red1);
                }
            });
            pop.setOnMoreClickListener(new TwoFunctionPop.OnMoreClickListener() {
                @Override
                public void add() {//添加
            gotoCustomerAdd();
                }

                @Override
                public void searchAll() {//搜
                    showSearchDialog();
                }
            });
            pop.showPop();
        }

    }

    private void gotoCustomerAdd() {
        mainActivity.gotoCustomeradd();
    }
    private void gotoCustomerEdit(CustomerBean.DataBean bean) {
        mainActivity.gotoCustomerEdit(bean);
    }

    private void showSearchDialog() {
        SearchCustomerDialog dialog = new SearchCustomerDialog(getActivity(), ((MainActivity) getActivity()).getRightWidth());
        dialog.setSearchClickListener(new SearchCustomerDialog.OnSearchClickListener() {
            @Override
            public void onClick(String regStart, String regEnd, String name, String phone, String startInt, String endInt, String birStart, String birEnd) {
                clearConditions();
                pageNo=1;
                CustomerFragment.this.regStart=regStart;  //注册日区间始例：2018/6/1
                CustomerFragment.this.regEnd=regEnd  ;//注册日区间终
                CustomerFragment.this.name=name;//客户名
                CustomerFragment.this.phone=phone;//手机
                CustomerFragment.this. startInt=startInt;//积分区间
                CustomerFragment.this.endInt=endInt;//积分区间
                CustomerFragment.this.birStart=birStart;//生日区间始
                CustomerFragment.this.birEnd=birEnd;//生日区间终
                getCustomerList();
            }
        });
        dialog.showDiaglog();
    }


public  void  refreshCustomerList(){
        pageNo=1;
        getCustomerList();
}


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        OkHttpUtils.getInstance().cancelTag(this);
    }
}
