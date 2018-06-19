package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.BookBean;
import com.jlkf.fsnail.bean.Card2Bean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.holder.BookViewHolder;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class BookAdapter extends RecyclerView.Adapter {

    private List<BookBean.DataBean> datas;

    public BookAdapter(List<BookBean.DataBean> mDatas) {
        this.datas = mDatas;
    }

    public void setDatas(List<BookBean.DataBean> mDatas){
        this.datas = mDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_recycel_view,null);
        return new BookViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((BookViewHolder)holder).img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventCenter(Constants.CODE_EDIT_BOOK, datas.get(position)));
            }
        });

        ((BookViewHolder)holder).tv_book_time.setText(datas.get(position).getAppointTime()+"");
        ((BookViewHolder)holder).tv_book_date.setText(datas.get(position).getOptime()+"");
        ((BookViewHolder)holder).tv_employee_nickName.setText(datas.get(position).getUName());
        ((BookViewHolder)holder).tv_customer_name.setText(datas.get(position).getCustomerName());
        ((BookViewHolder)holder).tv_customer_phone.setText(datas.get(position).getCustomerPhone());
        ((BookViewHolder)holder).tv_type.setText(datas.get(position).getType());
        ((BookViewHolder)holder).tv_service.setText(datas.get(position).getService());
        ((BookViewHolder)holder).tv_brand.setText(datas.get(position).getBrand());
        ((BookViewHolder)holder).tv_staus.setText(Constants.getStaus(datas.get(position).getStatus()));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }




}



