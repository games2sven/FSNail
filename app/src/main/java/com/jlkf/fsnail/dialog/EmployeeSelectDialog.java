package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.bean.StaffBean;
import com.jlkf.fsnail.widget.Wheel.WheelPicker;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class EmployeeSelectDialog implements View.OnClickListener{

    private Context mContex;
    View mView;
    private WheelPicker wheelListView1;
    private WheelPicker wheelListView2;
    private WheelPicker wheelListView3;

    private AlertDialog dialog ;

    private TextView text_ok;
    private ImageView dialog_cancel;
    private OkClickListner mListner;
    private ServiceMenuBean.DataBean.StaffBean employee;
    private String hour;
    private String minute;

    private int id = -1;

    List<ServiceMenuBean.DataBean.StaffBean> employeeLists;
    List<String> listNames = new ArrayList<>();

    public EmployeeSelectDialog(Context context,OkClickListner listner){
        this.mContex = context;
        this.mListner = listner;
        employeeLists = MyApplication.getInstance().getMenuBean().getData().getStaff();
        initData();
    }

    public void initData(){
        for(ServiceMenuBean.DataBean.StaffBean staffBean :employeeLists){
            listNames.add(staffBean.getU_name());
        }
    }

    public void setEmployee(int id,String time){
        this.id = id;
        String [] strs = time.split(" ");
        String [] times = strs[1].split(":");
        hour = times[0];
        minute =  times[1];
    }

    List<String> listHours ;
    List<String> listMinutes ;

    public void showDialog(){
        dialog = new AlertDialog.Builder(mContex).create();
        mView = LayoutInflater.from(mContex).inflate(R.layout.time_dialog, null);

        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(null);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        wheelListView1 = (WheelPicker) mView.findViewById(R.id.np_date);
        wheelListView1.setData(listNames);
        if(id != -1){
            for(ServiceMenuBean.DataBean.StaffBean staffBean : employeeLists){
                if(id == staffBean.getId()){
                    employee = staffBean;
                    break;
                }
            }
            int index_staff = employeeLists.indexOf(employee);
            wheelListView1.setSelectedItemPosition(index_staff);
        }else{
            employee = employeeLists.get(0);
        }

        wheelListView1.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                employee = employeeLists.get(position);
            }
        });

        listHours =  Arrays.asList(mContex.getResources()
                        .getStringArray( R.array.WheelHourDefault));
        wheelListView2 = (WheelPicker) mView.findViewById(R.id.hour_timePicker);
        wheelListView2.setData(listHours);
        if(TextUtils.isEmpty(hour)){
            hour = listHours.get(0);
        }
        int index_hour = listHours.indexOf(hour);
        wheelListView2.setSelectedItemPosition(index_hour);
        wheelListView2.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                hour = listHours.get(position);
            }
        });


        listMinutes =  Arrays.asList(mContex.getResources()
                .getStringArray( R.array.WheelMinuteDefault));
        wheelListView3 = (WheelPicker) mView.findViewById(R.id.minute_timePicker);
        wheelListView3.setData(listMinutes);
        if(TextUtils.isEmpty(minute)){
            minute = listMinutes.get(0);
        }
        int index_minute = listMinutes.indexOf(minute);
        wheelListView3.setSelectedItemPosition(index_minute);
        wheelListView3.setOnItemSelectedListener(new WheelPicker.OnItemSelectedListener() {
            @Override
            public void onItemSelected(WheelPicker picker, Object data, int position) {
                minute = listMinutes.get(position);
            }
        });

        text_ok = (TextView) mView.findViewById(R.id.text_ok);
        text_ok.setOnClickListener(this);
        dialog_cancel = (ImageView)mView.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(this);
    }

    public  void  dissMiss(){
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_ok:
                if(mListner != null){
                    mListner.clickOk(employee,hour,minute);
                }
                dissMiss();
                break;
            case R.id.dialog_cancel:
                dissMiss();
                break;
        }
    }

    public interface OkClickListner{
        void clickOk(ServiceMenuBean.DataBean.StaffBean bean,String hour,String minute);
    }




}
