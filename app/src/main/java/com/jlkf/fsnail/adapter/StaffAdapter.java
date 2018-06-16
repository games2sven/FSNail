package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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
    private final List<StaffBean> datas;

    public StaffAdapter(List<StaffBean> mDatas) {
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

    }

    @Override
    public int getItemCount() {
        return 24;
    }


    
    public  interface OnStaffClickListener{
        void  onClick();
        
    }

}



