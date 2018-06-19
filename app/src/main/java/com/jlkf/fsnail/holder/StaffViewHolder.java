package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.widget.NetworkImageView;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class StaffViewHolder extends   RecyclerView.ViewHolder{
    public View itemView;
    public   NetworkImageView headImg;
    public  TextView item_staff_nike_name;
    public StaffViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        headImg=itemView.findViewById(R.id.item_staff_headimg);
        item_staff_nike_name=itemView.findViewById(R.id.item_staff_nike_name);
    }
}