package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Lenovo on 2018/6/12.
 */

public class ShopCartViewHolder extends RecyclerView.ViewHolder {
  public  View itemView;
    public  CheckBox item_cart_checkbox1;
    public TextView item_cart_service_time,item_cart_staff_nike_name,item_cart_customer_name,item_cart_cutomer_phone,item_cart_service_status,item_cart_price,item_cart_jicunma,item_cart_service_name;
    public  View shop_cart_service_del;
    public ShopCartViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        item_cart_checkbox1=itemView.findViewById(R.id.item_cart_checkbox1);
        item_cart_service_time=itemView.findViewById(R.id.item_cart_service_time);
        item_cart_staff_nike_name=itemView.findViewById(R.id.item_cart_staff_nike_name);
        item_cart_customer_name=itemView.findViewById(R.id.item_cart_customer_name);
        item_cart_cutomer_phone=itemView.findViewById(R.id.item_cart_cutomer_phone);
        item_cart_service_status=itemView.findViewById(R.id.item_cart_service_status);
        item_cart_price=itemView.findViewById(R.id.item_cart_price);
        item_cart_jicunma=itemView.findViewById(R.id.item_cart_jicunma);
        item_cart_service_name=itemView.findViewById(R.id.item_cart_service_name);
        shop_cart_service_del=itemView.findViewById(R.id.shop_cart_service_del);
    }
}
