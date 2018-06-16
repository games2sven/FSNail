package com.jlkf.fsnail.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.ShopCartBean;
import com.jlkf.fsnail.holder.ShopCartViewHolder;
import com.jlkf.fsnail.holder.StaffManagerViewHolder;
import com.jlkf.fsnail.widget.excelpanel.RecyclerViewAdapter;

import java.util.List;

/**
 * Created by Lenovo on 2018/6/12.
 */

public class ShopCartAdapter  extends RecyclerView.Adapter<ShopCartViewHolder>{
    private final Context context;
    private OnItemClickListener itemClickListener;
    List<ShopCartBean.DataBean> mDatas;
    public ShopCartAdapter(Context context, List<ShopCartBean.DataBean> mDatas) {
        this.mDatas=mDatas;
        this.context =context;
    }

    @Override
    public ShopCartViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
     View view=   LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_shopping_cart_title_info,null);
        return new ShopCartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ShopCartViewHolder holder, int position) {
      final ShopCartBean.DataBean dataBean =mDatas.get(position);
        setDrawable( holder.item_cart_checkbox1);
          holder.item_cart_checkbox1.setChecked(dataBean.isSelect());
        holder.item_cart_checkbox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                dataBean.setSelect(isChecked);
                holder.item_cart_checkbox1.setChecked(isChecked);
                if (itemClickListener!=null){
                    itemClickListener.onCheckChange();
                }
            }
        });
           holder.shop_cart_service_del.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   if (itemClickListener!=null){
                       itemClickListener.onDelClick(dataBean);
                   }
               }
           });

        holder.item_cart_cutomer_phone.setText(dataBean.getCustomerPhone());
        holder.item_cart_customer_name.setText(dataBean.getCustomerName());
        holder.item_cart_service_time.setText(dataBean.getAppointTime());
        holder.item_cart_jicunma.setText(dataBean.getObjectCheck()+"");
        holder.item_cart_price.setText(dataBean.getPrice()+"");
        holder.item_cart_staff_nike_name.setText(dataBean.getUName());
        holder.item_cart_service_name.setText(dataBean.getService());
        String  stutatext=context.getString(R.string.status_no_tart);
        switch (dataBean.getStatus()){
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
        holder.item_cart_service_status.setText(stutatext);
    }
    private void   setDrawable(CheckBox checkBox){
        Drawable drawable = context.getResources().getDrawable(R.drawable.checked_box_selector);
        drawable.setBounds(0,0, (int) context.getResources().getDimension(R.dimen.DIMEN_24PX), (int) context.getResources().getDimension(R.dimen.DIMEN_24PX));
        checkBox.setCompoundDrawables(drawable,null,null,null);
    }


    @Override
    public int getItemCount() {
        return mDatas==null?0:mDatas.size();
    }

    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface OnItemClickListener {
        void onDelClick(ShopCartBean.DataBean dataBean);

        void onCheckChange();
    }


}
