package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.OrderBean;
import com.jlkf.fsnail.holder.OrderViewHolder;
import com.jlkf.fsnail.utils.TimeUtil;

import java.util.List;


public class OrderAdapter extends RecyclerView.Adapter {

    private List<OrderBean.DataBean> datas;

    public OrderAdapter(List<OrderBean.DataBean> mDatas) {
        this.datas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order_recycel_view,null);
        return new OrderViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((OrderViewHolder)holder).tv_doder_num.setText(datas.get(position).getOrderNumber());
        ((OrderViewHolder)holder).tv_product_name.setText(datas.get(position).getGoodName());
        ((OrderViewHolder)holder).tv_type.setText(datas.get(position).getGtname());
        ((OrderViewHolder)holder).tv_brand.setText(datas.get(position).getBname());
        ((OrderViewHolder)holder).tv_number.setText(datas.get(position).getQuantity()+"");
        ((OrderViewHolder)holder).tv_store.setText(datas.get(position).getShopName());
        ((OrderViewHolder)holder).tv_channel.setText(datas.get(position).getOrderType()+"");
        ((OrderViewHolder)holder).tv_first_price.setText(datas.get(position).getStartPrice()+"");
        ((OrderViewHolder)holder).tv_voucher.setText(datas.get(position).getUseVoucher()+"");
        ((OrderViewHolder)holder).tv_final_price.setText(datas.get(position).getFinalPrice()+"");
        ((OrderViewHolder)holder).tv_order_time.setText(TimeUtil.paserTimeToYM(datas.get(position).getOrderTime()));
        ((OrderViewHolder)holder).tv_pay_way.setText(datas.get(position).getPayment()+"");
        ((OrderViewHolder)holder).tv_customer.setText(datas.get(position).getName());
        ((OrderViewHolder)holder).tv_customer_phone.setText(datas.get(position).getPhone());
        ((OrderViewHolder)holder).tv_order_status.setText(datas.get(position).getStatus()+"");
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void setDatas(List<OrderBean.DataBean> datas){
        this.datas = datas;
        this.notifyDataSetChanged();
    }


}



