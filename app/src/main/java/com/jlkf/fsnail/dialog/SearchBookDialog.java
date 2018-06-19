package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.widget.myspinner.singletextviewspinner.TextViewSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class SearchBookDialog implements View.OnClickListener{

    String [] temps;
    List<String> listTypes = new ArrayList<String>();
    List<String> listStatus = new ArrayList<String>();
    List<ServiceMenuBean.DataBean.TypeBean> typeBeans;

    private final Context context;
    private final int width;
    private AlertDialog dialog;
    private View mView;
    private TextViewSpinner spinner_type,spinner_status;
    private TextView tv_serch;

    private EditText et_customer,et_employee;
    private TextView tv_date_start,tv_date_end,tv_time_start,tv_time_end;
    private ImageView img_date_start,img_date_end,img_time_start,img_time_end;

    private String type;
    private int status = -1;


    public SearchBookDialog(Context context, int  width ) {
        this.context =context;
        this.width=width;
    }


    public void  showDiaglog(){
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_search_book, null);
        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        spinner_type = (TextViewSpinner)mView.findViewById(R.id.spinner_type);
        spinner_status = (TextViewSpinner)mView.findViewById(R.id.spinner_status);
        tv_serch = (TextView)mView.findViewById(R.id.tv_serch);
        tv_date_start = (TextView)mView.findViewById(R.id.tv_date_start);
        img_date_start = (ImageView)mView.findViewById(R.id.img_date_start);
        tv_date_end = (TextView)mView.findViewById(R.id.tv_date_end);
        img_date_end = (ImageView)mView.findViewById(R.id.img_date_end);
        tv_time_start = (TextView)mView.findViewById(R.id.tv_time_start);
        img_time_start = (ImageView)mView.findViewById(R.id.img_time_start);
        tv_time_end = (TextView)mView.findViewById(R.id.tv_time_end);
        img_time_end = (ImageView)mView.findViewById(R.id.img_time_end);
        et_customer = (EditText)mView.findViewById(R.id.et_customer);
        et_employee = (EditText)mView.findViewById(R.id.et_employee);

        typeBeans = MyApplication.getInstance().getMenuBean().getData().getType();
        for(ServiceMenuBean.DataBean.TypeBean typeBean:typeBeans){
            listTypes.add(typeBean.getName());
        }

        temps = context.getResources().getStringArray(R.array.status);
        listStatus = Arrays.asList(temps);

        initView();
    }

    private void initView(){

        tv_serch.setOnClickListener(this);
        img_date_start.setOnClickListener(this);
        img_date_end.setOnClickListener(this);
        img_time_start.setOnClickListener(this);
        img_time_end.setOnClickListener(this);

        spinner_type.setItems(listTypes);
        spinner_type.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                type = typeBeans.get(position).getId()+"";
            }
        });
        spinner_type.setOnNothingSelectedListener(new TextViewSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(TextViewSpinner spinner) {
            }
        });

        spinner_status.setItems(listStatus);
        spinner_status.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                status = position+1;
            }
        });
        spinner_status.setOnNothingSelectedListener(new TextViewSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(TextViewSpinner spinner) {
            }
        });

    }

    private void dissmiss() {
        if (dialog!=null){
            dialog.dismiss();
            dialog = null;
        }
    }

    private Map<String,String> getParams(){
        Map<String,String> params = new HashMap<>();

        if(!TextUtils.isEmpty(tv_date_start.getText().toString().trim())){
            params.put("startDate",tv_date_start.getText().toString().trim());
        }
        if(!TextUtils.isEmpty(tv_date_end.getText().toString().trim())){
            params.put("endDate",tv_date_end.getText().toString().trim());
        }
        if(!TextUtils.isEmpty(tv_time_start.getText().toString().trim())){
            params.put("startTime",tv_time_start.getText().toString().trim());
        }
        if(!TextUtils.isEmpty(tv_time_end.getText().toString().trim())){
            params.put("endTime",tv_time_end.getText().toString().trim());
        }

        if(!TextUtils.isEmpty(et_customer.getText().toString().trim())){
            params.put("customerName",et_customer.getText().toString().trim());
        }
        if(!TextUtils.isEmpty(et_employee.getText().toString().trim())){
            params.put("uName",et_employee.getText().toString().trim());
        }

        if(!TextUtils.isEmpty(type)){
            params.put("type",type);
        }
        if(status != -1){
            params.put("status",status+"");
        }
        return params;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_serch:
                dissmiss();
                Map<String,String> params =  getParams();
                EventBus.getDefault().post(new EventCenter(Constants.CODE_SEARCH_BOOK,params));
                break;
            case R.id.img_date_start:
                DialogChooseDate dialogChooseDate = new DialogChooseDate(context,width, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        tv_date_start.setText(date);
                    }
                });
                break;
            case R.id.img_date_end:
                DialogChooseDate dialogChooseDate2 = new DialogChooseDate(context,width, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        tv_date_end.setText(date);
                    }
                });
                break;
            case R.id.img_time_start:
                DialogChooseTime dialogChooseTime = new DialogChooseTime(context, width, new DialogChooseTime.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String time) {
                        tv_time_start.setText(time);
                    }
                });
                break;
            case R.id.img_time_end:
                DialogChooseTime dialogChooseTime2 = new DialogChooseTime(context, width, new DialogChooseTime.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String time) {
                        tv_time_end.setText(time);
                    }
                });
                break;
        }
    }
}
