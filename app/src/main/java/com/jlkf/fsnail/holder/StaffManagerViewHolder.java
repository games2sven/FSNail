package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class StaffManagerViewHolder extends   RecyclerView.ViewHolder{
    public  TextView tv_operation;
    public View itemView;
    public TextView item_staff_id,item_staff_nike_name,item_staff_name,item_staff_phone,item_staff_status;
    public StaffManagerViewHolder(View itemView) {
        super(itemView);
        this.itemView=itemView;
         this.tv_operation =itemView.findViewById(R.id.tv_operation);
         this.item_staff_id =itemView.findViewById(R.id.item_staff_id);
         this.item_staff_nike_name =itemView.findViewById(R.id.item_staff_nike_name);
         this.item_staff_name =itemView.findViewById(R.id.item_staff_name);
         this.item_staff_phone =itemView.findViewById(R.id.item_staff_phone);
         this.item_staff_status =itemView.findViewById(R.id.item_staff_status);

    }
}