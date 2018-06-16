package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.CustomerBean;
import com.jlkf.fsnail.holder.CustomerViewHolder;
import com.jlkf.fsnail.holder.ShiftViewHolder;

import java.util.List;


public class CustomerAdapter extends RecyclerView.Adapter<CustomerViewHolder> {

    private final List<CustomerBean.DataBean> datas;

    public CustomerAdapter(List<CustomerBean.DataBean> mDatas) {
        this.datas = mDatas;
    }

    @NonNull
    @Override
    public CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_customer_view,null);
        return new CustomerViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(CustomerViewHolder holder, final int position) {
             holder.editView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     if (itemClickListener!=null){
                         itemClickListener.onCustomeEditClick(position);
                     }
                 }
             });
             CustomerBean.DataBean  dataBean  =datas.get(position);
        holder.item_customer_birthday.setText(dataBean.getBirthday());
        holder.item_customer_email.setText(dataBean.getEmail());
        holder.item_customer_jifen.setText(dataBean.getIntegral()+"分");
        holder.item_customer_photo.setText(dataBean.getPhone());//缺少手机号码
        holder.item_customer_name.setText(dataBean.getName());
        holder.item_customer_register_time.setText(dataBean.getRegistration());

    }



    @Override
    public int getItemCount() {
        return datas.size();
    }


    public OnCustomerItemClickListener getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(OnCustomerItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    OnCustomerItemClickListener itemClickListener;

    public interface   OnCustomerItemClickListener{
         void onCustomeEditClick(int  position);

    }

}



