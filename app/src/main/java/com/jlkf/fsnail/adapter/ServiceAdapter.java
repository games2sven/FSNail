package com.jlkf.fsnail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.ServiceBean;
import com.jlkf.fsnail.holder.ServiceViewHolder;
import com.jlkf.fsnail.utils.TimeUtil;

import java.util.List;

import me.lxw.dtl.utils.DTLUtils;


public class ServiceAdapter extends RecyclerView.Adapter<ServiceViewHolder> {

    private final  List<ServiceBean.DataBean >  datas;
    private final Context context;
    private final int type;//0 服务 1结果
    private OnLongClickListener listener;

    public ServiceAdapter(Context context, List<ServiceBean.DataBean > dataBeans,int type) {
        this.type=type;
        this.datas = dataBeans;
        this.context =context;
    }

    @NonNull
    @Override
    public ServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_service_recycel_view,null);
        return new ServiceViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull  ServiceViewHolder holder, final int position) {
         final  ServiceBean.DataBean  serviceBean= datas.get(position);

        holder.nameView.setText(serviceBean.getCustomerName());
        holder.nikeNameView.setText(serviceBean.getUName());
        holder.timeView.setText(serviceBean.getStartTime());
        holder.brandView.setText(serviceBean.getBrand());
        holder.categoryView.setText(serviceBean.getType());
        holder.phoneView.setText(serviceBean.getCustomerPhone());
        holder.dateView.setText(serviceBean.getOptime());
        if (type==1){
       holder.finalPrice.setVisibility(View.VISIBLE);
          holder.originPrice.setVisibility(View.VISIBLE);
        }else{
            holder.finalPrice.setVisibility(View.GONE);
            holder.originPrice.setVisibility(View.GONE);

        }

          String  stutatext=context.getString(R.string.status_no_tart);
         switch (serviceBean.getStatus()){
             case 1:
                 stutatext=context.getString(R.string.status_no_tart);
                 holder.statusView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                 holder.statusView.setTextColor(Color.parseColor("#29d7f1"));
                 break;
             case 2:
                 holder.statusView.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
                 holder.statusView.setTextColor(Color.parseColor("#94c42e"));
                 stutatext=context.getString(R.string.status_progress);
                 break;
             case 3:
                 stutatext=context.getString(R.string.status_finish);
                 holder.statusView.setTextColor(Color.parseColor("#7d7d7d"));
                 break;
             case 4:
                 stutatext=context.getString(R.string.status_booked);
                 holder.statusView.setTextColor(Color.parseColor("#7d7d7d"));
                 break;
             case 5:
                 stutatext=context.getString(R.string.status_cancel);
                 holder.statusView.setTextColor(Color.parseColor("#7d7d7d"));
                 break;


         }

        holder.statusView.setText(stutatext);

            holder.statusView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener!=null){
                        listener.statusClick(serviceBean);
                    }
                }
            });

        holder.nameView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener!=null){

                    listener.onCustomNameLongClick(v,serviceBean.getCustomerName());
                }
                return false;
            }
        });


        holder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.editClick(v,serviceBean);

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }


    public  void  setOnLongClickListener(OnLongClickListener listener){
        this.listener =listener;

    }

    public   interface  OnLongClickListener{

        void  onCustomNameLongClick(View view,String msg);

        void  editClick(View view,ServiceBean.DataBean serviceBean);

        void  statusClick(ServiceBean.DataBean serviceBean);
    }
}



