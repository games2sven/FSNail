package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ServiceBean;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.UiUtil;

import java.util.HashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/31 0031.
 */

public class ModifyServiceFragment extends BaseFragment {
    MainActivity mainActivity;
    ServiceBean.DataBean dataBean;


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
        mainActivity= (MainActivity) getActivity();
                 View rootView  =LayoutInflater.from(mainActivity).inflate(R.layout.fragment_modify_service,null);
        ButterKnife.bind(this,rootView);

         dataBean = (ServiceBean.DataBean) getArguments().getSerializable("data");
                 return  rootView;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.service_edit_back,R.id.service_edit_ensure,R.id.service_edit_star,R.id.service_edit_stop})
    public   void finishFragment(){
        ((MainActivity)getActivity()).popBackFragment(-1);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }


    String   staffId;
    String   id;//true
    String   brand;
    String    service;
    String  customerName;
    String customerPhone;
    String  objectCheck;//寄存号码
    String  optime;//日期
    String startPrice;

    private void  modifService(){

        if (TextUtils.isEmpty(id)){
            UiUtil.showToast(R.id.no_id);
            return;
        }

        Map<String ,String> params = new HashMap<>();
        addParams(params,"staffId",staffId);
        addParams(params,"id",id);
        addParams(params,"brand",brand);
        addParams(params,"service",service);
        addParams(params,"customerName",customerName);
        addParams(params,"customerPhone",customerPhone);
        addParams(params,"objectCheck",objectCheck);
        addParams(params,"optime",optime);
        addParams(params,"startPrice",startPrice);

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.UPDATE_SERVICE, this, params, new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {
            if (response.getCode()==200){
                UiUtil.showToast(R.string.modify_success);
            }else{
                UiUtil.showToast(response.getMsg());

            }


            }

            @Override
            public void onFailure(String errorMsg) {
                UiUtil.showToast(errorMsg);
            }
        });



    }

}
