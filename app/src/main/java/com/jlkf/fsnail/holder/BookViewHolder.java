package com.jlkf.fsnail.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class BookViewHolder extends RecyclerView.ViewHolder {

    public TextView tv_book_time;
    public TextView tv_book_date;
    public TextView tv_employee_nickName;
    public TextView tv_customer_name;
    public TextView tv_customer_phone;
    public TextView tv_type;
    public TextView tv_service;
    public TextView tv_brand;
    public TextView tv_staus;
    public ImageView img_add;
    public ImageView img_edit;
    public TextView tv_cancel_book;


    public BookViewHolder(View itemView) {
        super(itemView);

        tv_book_time = (TextView) itemView.findViewById(R.id.tv_book_time);
        tv_book_date = (TextView) itemView.findViewById(R.id.tv_book_date);
        tv_employee_nickName = (TextView) itemView.findViewById(R.id.tv_employee_nickname);
        tv_customer_name = (TextView) itemView.findViewById(R.id.tv_customer_name);
        tv_customer_phone = (TextView) itemView.findViewById(R.id.tv_customer_phone);
        tv_type = (TextView) itemView.findViewById(R.id.tv_type);
        tv_service = (TextView) itemView.findViewById(R.id.tv_service);
        tv_brand = (TextView) itemView.findViewById(R.id.tv_brand);
        tv_staus = (TextView) itemView.findViewById(R.id.tv_staus);
        img_add = (ImageView) itemView.findViewById(R.id.img_add);
        img_edit = (ImageView) itemView.findViewById(R.id.img_edit);
        tv_cancel_book = (TextView) itemView.findViewById(R.id.tv_cancel_book);
    }
}