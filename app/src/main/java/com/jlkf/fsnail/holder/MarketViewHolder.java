package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class MarketViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_num;
    public TextView tv_product_name;
    public TextView tv_type;
    public TextView tv_brand;
    public TextView tv_descripe;
    public TextView tv_money;
    public ImageView img_shop_car;

    public MarketViewHolder(View itemView) {
        super(itemView);

        tv_num = (TextView) itemView.findViewById(R.id.tv_num);
        tv_product_name = (TextView) itemView.findViewById(R.id.tv_product_name);
        tv_type = (TextView) itemView.findViewById(R.id.tv_type);
        tv_brand = (TextView) itemView.findViewById(R.id.tv_brand);
        tv_descripe = (TextView) itemView.findViewById(R.id.tv_descripe);
        tv_money = (TextView) itemView.findViewById(R.id.tv_money);
        img_shop_car = (ImageView) itemView.findViewById(R.id.img_shop_car);
    }
}