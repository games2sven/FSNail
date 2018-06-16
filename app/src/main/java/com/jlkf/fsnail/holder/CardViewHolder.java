package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class CardViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_card_num;
    public TextView tv_pro_name;
    public TextView tv_pro_type;
    public TextView tv_price;
    public TextView tv_buy_customer;
    public TextView tv_bind_customer;
    public TextView tv_bind_phone;
    public TextView tv_card_type;
    public TextView tv_time;
    public TextView tv_detail;


    public CardViewHolder(View itemView) {
        super(itemView);

        tv_card_num = (TextView) itemView.findViewById(R.id.tv_card_num);
        tv_pro_name = (TextView) itemView.findViewById(R.id.tv_pro_name);
        tv_pro_type = (TextView) itemView.findViewById(R.id.tv_pro_type);
        tv_price = (TextView) itemView.findViewById(R.id.tv_price);
        tv_buy_customer = (TextView) itemView.findViewById(R.id.tv_buy_customer);
        tv_bind_customer = (TextView) itemView.findViewById(R.id.tv_bind_customer);
        tv_bind_phone = (TextView) itemView.findViewById(R.id.tv_bind_phone);
        tv_card_type = (TextView) itemView.findViewById(R.id.tv_card_type);
        tv_time = (TextView) itemView.findViewById(R.id.tv_time);
        tv_detail = (TextView) itemView.findViewById(R.id.tv_detail);
    }
}