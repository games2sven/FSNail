package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class ShiftViewHolder extends   RecyclerView.ViewHolder{
    public View itemView;
    public  ImageView upimage;
    public ShiftViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
        this.upimage =itemView.findViewById(R.id.item_shift_up);
    }
}