package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.GoodsBean;
import com.jlkf.fsnail.bean.OrderBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.holder.MarketViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class MarketAdapter extends RecyclerView.Adapter {

    private List<GoodsBean.DataBean> datas;

    public MarketAdapter(List<GoodsBean.DataBean> mDatas) {
        this.datas = mDatas;
    }

    public void setDatas(List<GoodsBean.DataBean> mDatas){
        this.datas = mDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_market_recycel_view,null);
        return new MarketViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MarketViewHolder)holder).img_shop_car.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventCenter(Constants.CODE_MARKET_ADD_SHOPCAR,datas.get(position)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


}



