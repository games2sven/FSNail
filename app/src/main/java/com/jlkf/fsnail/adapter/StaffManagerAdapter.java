package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.StaffManagerBean;
import com.jlkf.fsnail.holder.StaffManagerViewHolder;

import java.util.List;


public class StaffManagerAdapter extends RecyclerView.Adapter<StaffManagerViewHolder> {

    private final List<StaffManagerBean.DataBean > datas;

    public StaffManagerAdapter(List<StaffManagerBean.DataBean > mDatas) {
        this.datas = mDatas;
    }

    @NonNull
    @Override
    public StaffManagerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff_manager_view,null);
        return new StaffManagerViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(StaffManagerViewHolder holder, final int position) {
        StaffManagerBean.DataBean dataBean =datas.get(position);
        holder.tv_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener!=null){
                    clickListener.onClickDetail(datas.get(position));
                }
            }
        });
        holder.item_staff_id.setText(dataBean.getId()+"");
        holder.item_staff_name.setText(dataBean.getU_name());
        holder.item_staff_nike_name.setText(dataBean.getNick_name());
        holder.item_staff_phone.setText(dataBean.getPhone());

        int statusTextId;
        switch (dataBean.getStatus()){
            case 0://进行中
                statusTextId =R.string.staff_status_progress;
                break;
            case 1://休息中
                statusTextId =R.string.staff_status_rest;
                break;
            case 2://预约
                statusTextId=R.string.staff_status_booked;
                break;
            default://请假中
                statusTextId=R.string.staff_status_holiday;
                break;

        }
        holder.item_staff_status.setText(statusTextId);

    }



    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }


    public OnStaffManagerClickListener getClickListener() {
        return clickListener;
    }

    public void setClickListener(OnStaffManagerClickListener clickListener) {
        this.clickListener = clickListener;
    }

    OnStaffManagerClickListener clickListener;
    public  interface  OnStaffManagerClickListener{
        void onClickDetail(StaffManagerBean.DataBean  dataBean);

    }

}



