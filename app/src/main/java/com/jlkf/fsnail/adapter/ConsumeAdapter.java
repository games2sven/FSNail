package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.ConsumeBean;
import com.jlkf.fsnail.holder.ConsumeViewHolder;

import java.util.List;


public class ConsumeAdapter extends RecyclerView.Adapter {

    private final List<ConsumeBean> datas;

    public ConsumeAdapter(List<ConsumeBean> mDatas) {
        this.datas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_consume_view,null);
        return new ConsumeViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {


    }

    @Override
    public int getItemCount() {
        return datas.size();
    }
}



