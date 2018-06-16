package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class ConsumeViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_businessMan;
    public TextView tv_type;
    public TextView tv_service_name;
    public TextView tv_product_name;
    public TextView tv_product_price;
    public TextView tv_use_money;
    public TextView tv_consume_time;


    public ConsumeViewHolder(View itemView) {
        super(itemView);

        tv_businessMan = (TextView) itemView.findViewById(R.id.tv_businessMan);
        tv_type = (TextView) itemView.findViewById(R.id.tv_type);
        tv_service_name = (TextView) itemView.findViewById(R.id.tv_service_name);
        tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
        tv_product_price = (TextView) itemView.findViewById(R.id.tv_product_price);
        tv_use_money = (TextView) itemView.findViewById(R.id.tv_use_money);
        tv_consume_time = (TextView) itemView.findViewById(R.id.tv_consume_time);
    }
}