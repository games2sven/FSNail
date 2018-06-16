package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_doder_num;
    public TextView tv_product_name;
    public TextView tv_type;
    public TextView tv_brand;
    public TextView tv_number;
    public TextView tv_store;
    public TextView tv_channel;
    public TextView tv_first_price;
    public TextView tv_voucher;
    public TextView tv_final_price;
    public TextView tv_order_time;
    public TextView tv_pay_way;
    public TextView tv_customer;
    public TextView tv_customer_phone;
    public TextView tv_order_status;
    public TextView tv_operation;

    public OrderViewHolder(View itemView) {
        super(itemView);

        tv_doder_num = (TextView) itemView.findViewById(R.id.tv_doder_num);
        tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
        tv_type = (TextView) itemView.findViewById(R.id.tv_type);
        tv_brand = (TextView) itemView.findViewById(R.id.tv_brand);
        tv_number = (TextView) itemView.findViewById(R.id.tv_number);
        tv_store = (TextView) itemView.findViewById(R.id.tv_store);
        tv_channel = (TextView) itemView.findViewById(R.id.tv_channel);
        tv_first_price = (TextView) itemView.findViewById(R.id.tv_first_price);
        tv_voucher = (TextView) itemView.findViewById(R.id.tv_voucher);
        tv_final_price = (TextView) itemView.findViewById(R.id.tv_final_price);
        tv_order_time = (TextView) itemView.findViewById(R.id.tv_order_time);
        tv_pay_way = (TextView) itemView.findViewById(R.id.tv_pay_way);
        tv_customer = (TextView) itemView.findViewById(R.id.tv_customer);
        tv_customer_phone = (TextView) itemView.findViewById(R.id.tv_customer_phone);
        tv_order_status = (TextView) itemView.findViewById(R.id.tv_order_status);
        tv_operation = (TextView) itemView.findViewById(R.id.tv_operation);
    }
}