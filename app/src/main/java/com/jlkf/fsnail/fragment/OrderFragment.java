package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.OrderAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.OrderBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.SearchOrderDialog;
import com.jlkf.fsnail.dialog.SingleFunctionPop;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class OrderFragment extends BaseFragment{

    List<OrderBean.DataBean> mDatas =new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;

    private OrderAdapter mAdapter;

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_order,null);
        ButterKnife.bind(this,view);

        loadData();
        return view;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        Object object = eventCenter.getData();
        switch (eventCenter.getEventCode()){
            case Constants.CODE_SEARCH_ORDERLIST:
                Map<String, String> params = (Map<String, String>) object;
                requestOrderList(params);
                break;
        }


    }

    public void loadData(){
        Map<String, String> params = new HashMap<>();

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.ORDER_LIST, this, params, new MyHttpCallback<OrderBean>() {

            @Override
            public void onSuccess(OrderBean response) {
                Log.i("Sven","response "+response.getCode());
                if(response.getCode() == 200){
                    mDatas = response.getData();
                    initRecyclerview();
                }

            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }

    public void initRecyclerview(){
        recylerview.setLayoutManager(new GridLayoutManager(getActivity(),1));
        mAdapter =  new OrderAdapter(mDatas);
        recylerview.setAdapter(mAdapter);
    }

    boolean isShowMore;
    @OnClick(R.id.iv_service_more)
    public   void  showMoreFunction(){
        isShowMore=!isShowMore;
        iv_service_more.setImageResource(isShowMore?R.mipmap.btn_more_red2:R.mipmap.btn_more_red1);
        if (isShowMore){
            SingleFunctionPop pop =new SingleFunctionPop(1,getActivity(),iv_service_more);
            pop.showPop();
            pop.setOnDismissListener(new SingleFunctionPop.OnDismissListener() {
                public void onDismiss() {
                    isShowMore=false;
                    iv_service_more.setImageResource(R.mipmap.btn_more_red1);
                }
            });
            pop.setOnMoreClickListener(new SingleFunctionPop.OnMoreClickListener() {
                @Override
                public void searchAll() {//搜索所有
                    showSearchDialog();
                }
            });
        }
    }

    private void showSearchDialog() {
        SearchOrderDialog dialog = new SearchOrderDialog(getActivity(), ((MainActivity) getActivity()).getRightWidth());
        dialog.showDiaglog();
    }


    public void requestOrderList(Map<String, String> params){
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.ORDER_LIST, this, params, new MyHttpCallback<OrderBean>() {

            @Override
            public void onSuccess(OrderBean response) {
                Log.i("Sven","response "+response.getCode());
                if(response.getCode() == 200){
                    mDatas = response.getData();
                    mAdapter.setDatas(mDatas);
                }
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }



}
