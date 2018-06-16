package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.adapter.MenuAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.MenuBean;
import com.jlkf.fsnail.bean.TypeBean;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.widget.myspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MenuFragment extends BaseFragment{

    @Bind(R.id.menu_recycler)
    RecyclerView mRecyClerView;
    List<MenuBean.DataBean> datas = new ArrayList<MenuBean.DataBean>();

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_menu,null);
        ButterKnife.bind(this,view);

        loadDatas();
        return view;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    public void loadDatas(){
        OKHttpUtils.getIntance().oKHttpGet(UrlConstants.PRICE_LIST, this,  new MyHttpCallback<MenuBean>() {

            @Override
            public void onSuccess(MenuBean response) {
                Log.i("Sven","response "+response.getCode());
                if(response.getCode() == 200){
                    datas = response.getData();
                    initView();
                }

            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }


    public void initView(){
        mRecyClerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        mRecyClerView.setAdapter(new MenuAdapter(datas));
//
//        mySpinner.setItems(datas);
//        mySpinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<MenuBean>() {
//
//            @Override public void onItemSelected(MaterialSpinner view, int position, long id, MenuBean item) {
//                Snackbar.make(view, "Clicked " + item.toString(), Snackbar.LENGTH_LONG).show();
//            }
//        });
    }


}
