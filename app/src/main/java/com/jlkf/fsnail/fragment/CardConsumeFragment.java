package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.ConsumeAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.ConsumeBean;
import com.jlkf.fsnail.bean.EventCenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CardConsumeFragment extends BaseFragment implements View.OnClickListener{

    List<ConsumeBean> mDatas =new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    @Bind(R.id.text_total_money)
    TextView text_total_money;
    @Bind(R.id.tv_return)
    TextView tv_return;

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.card_consume,null);
        ButterKnife.bind(this,view);

        Bundle bundle = getArguments();

        for(int i = 0;i<7;i++){
            ConsumeBean bean = new ConsumeBean();
            mDatas.add(bean);
        }

        initView();
        initRecyclerview();
        return view;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    public void initView(){
        tv_return.setOnClickListener(this);
    }

    public void initRecyclerview(){
        recylerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylerview.setAdapter(new ConsumeAdapter(mDatas));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_return:
                ((MainActivity)getActivity()).popBackFragment(-1);
                ((MainActivity)getActivity()).cardConsumeFragment=null;
                break;
        }
    }
}
