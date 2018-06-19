package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.ConsumeBean;
import com.jlkf.fsnail.holder.ConsumeViewHolder;
import com.jlkf.fsnail.utils.TimeUtil;

import java.util.List;


public class ConsumeAdapter extends RecyclerView.Adapter {

    private final List<ConsumeBean.DataBean> datas;

    public ConsumeAdapter(List<ConsumeBean.DataBean> mDatas) {
        this.datas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consume_view,null);
        return new ConsumeViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((ConsumeViewHolder)holder).tv_businessMan.setText(datas.get(position).getSName());
        ((ConsumeViewHolder)holder).tv_type.setText(datas.get(position).getSName());
        ((ConsumeViewHolder)holder).tv_service_name.setText(datas.get(position).getService());
        ((ConsumeViewHolder)holder).tv_product_name.setText(datas.get(position).getGoodName());
        ((ConsumeViewHolder)holder).tv_product_price.setText(datas.get(position).getGoodPrice()+"");
        ((ConsumeViewHolder)holder).tv_use_money.setText(datas.get(position).getUse_card_price()+"");
        ((ConsumeViewHolder)holder).tv_consume_time.setText(TimeUtil.paserTimeToYM(datas.get(position).getCreate_time()));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}



