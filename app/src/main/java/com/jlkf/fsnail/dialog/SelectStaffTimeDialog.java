package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.adapter.StaffTimeAdapter;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.StaffTimeBean;
import com.jlkf.fsnail.bean.StaffWorklyBean;
import com.jlkf.fsnail.constants.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class SelectStaffTimeDialog implements View.OnClickListener{

    private final List<StaffWorklyBean.DataBean> staffTimeBeanList=new ArrayList<>();
    private Context mContex;
    View mView;
    private AlertDialog dialog ;

    public SelectStaffTimeDialog(Context context,List<StaffWorklyBean.DataBean> staffTimeBeanList){
        mContex = context;
        this.staffTimeBeanList.addAll(staffTimeBeanList);
    }

    public void showDialog(){
        dialog = new AlertDialog.Builder(mContex).create();
        mView = LayoutInflater.from(mContex).inflate(R.layout.select_staff_time, null);
         ListView list_choose_time  =mView.findViewById(R.id.list_choose_time);
         TextView text_ok  =mView.findViewById(R.id.text_ok);
        list_choose_time.setAdapter(new StaffTimeAdapter(staffTimeBeanList));
        text_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new EventCenter(Constants.CODE_SELETC_STAFF_TIME,staffTimeBeanList));
                dialog.dismiss();

            }
        });

        mView.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        DisplayMetrics dm = mContex.getResources().getDisplayMetrics();

        window.setBackgroundDrawable(null);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }


}
