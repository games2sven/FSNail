package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class ServiceViewHolder extends   RecyclerView.ViewHolder{
    public View itemView;
    public  TextView brandView, serviceView ,timeView,dateView,nikeNameView,nameView,categoryView,statusView ,phoneView,originPrice,finalPrice;
    public  ImageView addView ,editView;
    public ServiceViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
         brandView =itemView.findViewById(R.id.item_service_band);
         serviceView =itemView.findViewById(R.id.item_service_service);
         timeView =itemView.findViewById(R.id.item_service_time);
         dateView =itemView.findViewById(R.id.item_service_date);
         nikeNameView =itemView.findViewById(R.id.item_service_nike_name);
         nameView =itemView.findViewById(R.id.item_service_name);
         categoryView =itemView.findViewById(R.id.item_service_category);
         statusView =itemView.findViewById(R.id.item_service_status);
         addView =itemView.findViewById(R.id.item_service_add);
         editView =itemView.findViewById(R.id.item_service_edit);
         phoneView =itemView.findViewById(R.id.item_service_phone);
        originPrice =itemView.findViewById(R.id.item_service_origin_price);
        finalPrice =itemView.findViewById(R.id.item_service_final_price);
    }
}