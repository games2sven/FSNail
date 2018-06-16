package com.jlkf.fsnail.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jlkf.fsnail.R;

import java.util.List;

/**
 * Created by Administrator on 2018/5/30 0030.
 */

public class MySpinnerAdapter extends BaseAdapter {

    private Context mContext;
    private List<String> mList;

    public MySpinnerAdapter(Context pContext, List<String> pList) {
        this.mContext = pContext;
        this.mList = pList;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    /**
     * 下面是重要代码
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater _LayoutInflater=LayoutInflater.from(mContext);
        convertView=_LayoutInflater.inflate(R.layout.item_spinner, null);
        if(convertView!=null) {
            TextView _TextView1=(TextView)convertView.findViewById(R.id.custom_text);
            _TextView1.setText(mList.get(position));
        }
        return convertView;
    }

}
