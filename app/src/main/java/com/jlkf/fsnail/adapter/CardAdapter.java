package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.Card2Bean;
import com.jlkf.fsnail.bean.CardBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.holder.CardViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class CardAdapter extends RecyclerView.Adapter {

    private final List<CardBean.DataBean> datas;

    public CardAdapter(List<CardBean.DataBean> mDatas) {
        this.datas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_recycel_view,null);
        return new CardViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((CardViewHolder)holder).tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventCenter(Constants.CODE_CRAD_DETAIL, datas.get(position)));
            }
        });


        ((CardViewHolder)holder).tv_card_num.setText(datas.get(position).getId()+"");
        ((CardViewHolder)holder).tv_pro_name.setText(datas.get(position).getCardName());
        ((CardViewHolder)holder).tv_pro_type.setText(datas.get(position).getName());
        ((CardViewHolder)holder).tv_price.setText(datas.get(position).getPrice()+"");
        ((CardViewHolder)holder).tv_buy_customer.setText(datas.get(position).getPayUser());
        ((CardViewHolder)holder).tv_bind_customer.setText(datas.get(position).getIsBandUser()+"");
        ((CardViewHolder)holder).tv_bind_phone.setText(datas.get(position).getIsBandUserPhone()+"");
        ((CardViewHolder)holder).tv_card_type.setText(datas.get(position).getType()+"");
        ((CardViewHolder)holder).tv_time.setText(datas.get(position).getPay_optime()+"");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}



