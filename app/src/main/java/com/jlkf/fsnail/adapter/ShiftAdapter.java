package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.ShiftBean;
import com.jlkf.fsnail.holder.ShiftViewHolder;

import java.util.List;


public class ShiftAdapter extends RecyclerView.Adapter<ShiftViewHolder> {
    public OnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    OnItemClickListener  onItemClickListener;
    private final List<ShiftBean> datas;

    public ShiftAdapter(List<ShiftBean> mDatas) {
        this.datas = mDatas;
    }

    @NonNull
    @Override
    public ShiftViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shift_recycel_view,null);
        return new ShiftViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull ShiftViewHolder holder, final int position) {
        holder.upimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener!=null){
                    onItemClickListener.onClick(position);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 4;
    }






    public   interface   OnItemClickListener{
        void onClick(int  position);

    }

}



