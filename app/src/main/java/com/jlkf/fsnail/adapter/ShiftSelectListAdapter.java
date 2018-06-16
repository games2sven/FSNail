package com.jlkf.fsnail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class ShiftSelectListAdapter extends BaseAdapter {
    private final Context mContext;
    List<String> nikeNames ;

    public int getSelectPosition() {
        return selectPosition;
    }

    int  selectPosition;
    public ShiftSelectListAdapter(Context context, List<String> nikeNames) {
        this.mContext=context;
        this.nikeNames =nikeNames;
    }

    @Override
    public int getCount() {
        return nikeNames==null?0:nikeNames.size();
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
        View rootview = LayoutInflater.from(mContext).inflate(R.layout.item_shift_select_list,null);
        TextView tv_name = rootview.findViewById(R.id.tv_name);
        tv_name.setText(nikeNames.get(position));
        ImageView imageView =rootview.findViewById(R.id.item_gou);
        View line =rootview.findViewById(R.id.item_line);
        if (position==selectPosition){
            imageView.setVisibility(View.VISIBLE);
        }else{
            imageView.setVisibility(View.GONE);
        }
        if (position==nikeNames.size()-1){
            line.setVisibility(View.INVISIBLE);

        }else{
            line.setVisibility(View.VISIBLE);
        }

        return rootview;
    }

    public  void  setSelctPosition(int position){
        this.selectPosition =position;
        notifyDataSetChanged();
    }
}
