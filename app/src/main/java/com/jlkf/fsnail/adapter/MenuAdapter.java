package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.MenuBean;
import com.jlkf.fsnail.bean.OrderBean;
import com.jlkf.fsnail.bean.TypeBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.holder.MarketViewHolder;
import com.jlkf.fsnail.widget.myspinner.MaterialSpinner;
import com.jlkf.fsnail.widget.myspinner.singletextviewspinner.TextViewSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.List;


public class MenuAdapter extends RecyclerView.Adapter {

    private final List<MenuBean.DataBean> datas;

    public MenuAdapter(List<MenuBean.DataBean> mDatas) {
        this.datas = mDatas;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu_recycel_view,null);
        return new MenuViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((MenuViewHolder)holder).spinner.setItems(datas.get(position).getPriceList());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    class MenuViewHolder extends RecyclerView.ViewHolder{

        public MaterialSpinner spinner;

        public MenuViewHolder(View itemView) {
            super(itemView);

            spinner = (MaterialSpinner)itemView.findViewById(R.id.spinner);

        }
    }



}



