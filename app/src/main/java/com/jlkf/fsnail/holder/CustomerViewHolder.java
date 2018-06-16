package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class CustomerViewHolder extends   RecyclerView.ViewHolder{
    public View itemView;
    public ImageView editView;
    public  TextView item_customer_is_appuser,item_customer_photo,item_customer_email,item_customer_birthday,item_customer_register_time,item_customer_jifen,item_customer_name;
    public CustomerViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        editView =itemView.findViewById(R.id.item_customer_edit);
        item_customer_is_appuser =itemView.findViewById(R.id.item_customer_is_appuser);
        item_customer_photo =itemView.findViewById(R.id.item_customer_photo);
        item_customer_email =itemView.findViewById(R.id.item_customer_email);
        item_customer_birthday =itemView.findViewById(R.id.item_customer_birthday);
        item_customer_register_time =itemView.findViewById(R.id.item_customer_register_time);
        item_customer_jifen =itemView.findViewById(R.id.item_customer_jifen);
        item_customer_name =itemView.findViewById(R.id.item_customer_name);
    }
}