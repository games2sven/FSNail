package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.StaffBean;
import com.jlkf.fsnail.holder.StaffViewHolder;
import com.jlkf.fsnail.holder.StaffViewHolder;

import java.util.List;


public class StaffAdapter extends RecyclerView.Adapter<StaffViewHolder> {
    public void setOnStaffClickListener(OnStaffClickListener listener) {
        this.listener = listener;
    }



    OnStaffClickListener listener;
    private final List<StaffBean.DataBean> datas;

    public StaffAdapter(List<StaffBean.DataBean> mDatas) {
        this.datas = mDatas;
    }

    @NonNull
    @Override
    public StaffViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff_recycel_view,null);
        return new StaffViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull StaffViewHolder holder, int position) {
        if (!TextUtils.isEmpty(datas.get(position).getHeaderImg()))
        holder.headImg.displayImage(datas.get(position).getHeaderImg());
        holder.item_staff_nike_name.setText(datas.get(position).getNickName()+"");
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }


    
    public  interface OnStaffClickListener{
        void  onClick();
        
    }

}



