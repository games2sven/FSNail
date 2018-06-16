package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.ServiceAdapter;
import com.jlkf.fsnail.adapter.ShiftAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ServiceBean;
import com.jlkf.fsnail.bean.ShiftBean;
import com.jlkf.fsnail.dialog.CustomNamePop;
import com.jlkf.fsnail.dialog.ShiftSelectDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/28 0028. 轮班
 */

public class ShiftFragment extends BaseFragment {
    @Bind(R.id.recylerview)
    RecyclerView recyclerView;
    MainActivity mainActivity;
    private ShiftAdapter mAdapter;
    List<ShiftBean> mDatas = new ArrayList<>();
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
        View rootview  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_shift,null);
        mainActivity = (MainActivity) getActivity();
        ButterKnife.bind(this,rootview);
        initRecyclerView();
        return rootview ;
    }

    @OnClick(R.id.shop_cart)
    public void gotoShoppingCart() {
        mainActivity.gotoShoppingCart();
    }
List<String> shiftNames =new ArrayList<>();
    private void initRecyclerView() {
        shiftNames.add("优选一");
        shiftNames.add("优选二");
        shiftNames.add("优选三");
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        mAdapter = new ShiftAdapter(mDatas);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new ShiftAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                ShiftSelectDialog  shiftSelectDialog =new ShiftSelectDialog(mainActivity,shiftNames);
                shiftSelectDialog.showDiaglog();
                shiftSelectDialog.setOnEnsureClickListenr(new ShiftSelectDialog.OnEnsureClickListenr() {
                    @Override
                    public void onEnsureClick(int position) {
                        Toast.makeText(mainActivity,shiftNames.get(position),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }
}
