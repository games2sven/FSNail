package com.jlkf.fsnail.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.StaffTimeBean;
import com.jlkf.fsnail.bean.StaffWorklyBean;

import java.util.List;

public class StaffTimeAdapter extends BaseAdapter {
    private final List<StaffWorklyBean.DataBean> staffTimeBeanList;

    public StaffTimeAdapter(List<StaffWorklyBean.DataBean> staffTimeBeanList) {
        this.staffTimeBeanList=staffTimeBeanList;
    }

    @Override
    public int getCount() {
        return staffTimeBeanList==null?0:staffTimeBeanList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View  view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_staff_time,null);
        ViewHolder holder = (ViewHolder) view.getTag();
        if (holder==null){
            holder =new ViewHolder() ;
            holder.staff_time_end=view.findViewById(R.id.staff_time_end);
            holder.staff_time_star=view.findViewById(R.id.staff_time_star);
            holder.staff_time_week=view.findViewById(R.id.staff_time_week);
            holder.staff_time_select_tv=view.findViewById(R.id.staff_time_select_tv);
            holder.staff_time_select_iv=view.findViewById(R.id.staff_time_select_bt);
           view.setTag(holder);
        }else {
            holder= (ViewHolder) view.getTag();
        }
        final StaffWorklyBean.DataBean timeBean  =staffTimeBeanList.get(position);
        if (timeBean.isOpen){
            holder.staff_time_select_iv.setImageResource(R.mipmap.turn_on);
            holder.staff_time_select_tv.setText("Disponible");
        }else{
            holder.staff_time_select_iv.setImageResource(R.mipmap.turn_off);
            holder.staff_time_select_tv.setText("Riposo");
        }
        holder.staff_time_end.setText(timeBean.getEnd());
        holder.staff_time_star.setText(timeBean.getStart());
        holder.staff_time_week.setText(timeBean.getWeek()+"");
        holder.staff_time_select_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeBean.isOpen= !timeBean.isOpen;
                notifyDataSetChanged();
            }
        });
        return view;
    }

    public  class  ViewHolder{
        public TextView staff_time_week;
        public TextView staff_time_star;
        public TextView staff_time_end;
        public TextView staff_time_select_tv;
        public ImageView staff_time_select_iv;

    }

}
