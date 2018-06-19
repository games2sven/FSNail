package com.jlkf.fsnail.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.BookedServiceBean;
import com.jlkf.fsnail.bean.ServiceBean;
import com.jlkf.fsnail.holder.BookedServiceViewHolder;
import com.jlkf.fsnail.holder.ServiceViewHolder;
import com.jlkf.fsnail.utils.TimeUtil;

import java.util.List;


public class BookedServiceAdapter extends RecyclerView.Adapter<BookedServiceViewHolder> {

    private final List<BookedServiceBean.DataBean> datas;
    private final Context context;
    private OnLongClickListener listener;

    public BookedServiceAdapter(Context context, List<BookedServiceBean.DataBean> mDatas) {
        this.datas = mDatas;
        this.context =context;
    }

    @NonNull
    @Override
    public BookedServiceViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_booked_service_recycel_view,null);
        return new BookedServiceViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull  BookedServiceViewHolder holder, final int position) {

        final  BookedServiceBean.DataBean  serviceBean= datas.get(position);

        holder.nameView.setText(serviceBean.getCustomerName());
        holder.nikeNameView.setText(serviceBean.getUName());
        holder.timeView.setText(serviceBean.getAppointTime());
        holder.brandView.setText(serviceBean.getBrand());
        holder.categoryView.setText(serviceBean.getType());
        holder.phoneView.setText(serviceBean.getCustomerPhone());
        holder.dateView.setText(serviceBean.getOptime());

        String  stutatext=context.getString(R.string.status_no_tart);
        switch (serviceBean.getStatus()){
            case 1:
                stutatext=context.getString(R.string.status_no_tart);
                break;
            case 2:
                stutatext=context.getString(R.string.status_progress);
                break;
            case 3:
                stutatext=context.getString(R.string.status_finish);
                break;
            case 4:
                stutatext=context.getString(R.string.status_booked);

                break;
            case 5:
                stutatext=context.getString(R.string.status_cancel);
                break;
        }
        holder.statusView.setTextColor(Color.parseColor("#7d7d7d"));
        holder.statusView.setText(stutatext);


        holder.nameView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (listener!=null){
                    listener.onCustomNameLongClick(v,datas.get(position).getCustomerName());
                }
                return false;
            }
        });


        holder.editView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.editClick(v,datas.get(position));

                }
            }
        });

        holder.addView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener!=null){
                    listener.addServiceClick(datas.get(position));

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

        void  onCustomNameLongClick(View view, String msg);

        void  editClick(View view, BookedServiceBean.DataBean serviceBean);

        void addServiceClick(BookedServiceBean.DataBean serviceBean);
    }
}



