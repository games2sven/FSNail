package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.ServiceBean;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.utils.TimeUtil;
import com.jlkf.fsnail.utils.UiUtil;
import com.jlkf.fsnail.widget.myspinner.singletextviewspinner.TextViewSpinner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class SearchServiceDialog {

    private final Context context;
    private final int width;
    private AlertDialog dialog;
    private View mView;
    ServiceMenuBean menuBean;
    List<String> statusStr =new ArrayList<>();
    public SearchServiceDialog(Context context, int width, ServiceMenuBean menuBean) {
    this.context =context;
    this.width=width;
    this.menuBean=menuBean;

    initStatus();
    }

    private void initStatus() {
        statusStr.add(context.getString(R.string.status_no_tart));
        statusStr.add(context.getString(R.string.status_progress));
        statusStr.add(context.getString(R.string.status_finish));
        statusStr.add(context.getString(R.string.status_booked));
        statusStr.add(context.getString(R.string.status_cancel));
    }


    public void  showDiaglog(){
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_search_service, null);

        initView();
        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
    }
    TextViewSpinner brandSpinner;
    TextViewSpinner serviceSpinner;
    TextViewSpinner statusSpinner;
    TextViewSpinner typeSpinner;
    String  brand;
    String  service;
    String status;
    String  type;
    String  startTime;
    String endTime;
    String customeName;
    String  customePhone;
    String  staffName;
   TextView tv_start_date,tv_end_date;
   EditText tv_customer_name,tv_customer_phone,tv_staff_name;

    private void initView() {
         brandSpinner =mView.findViewById(R.id.spinner_brand);
         serviceSpinner =mView.findViewById(R.id.spinner_service);
         statusSpinner =mView.findViewById(R.id.spinner_status);
         typeSpinner =mView.findViewById(R.id.spinner_type);
        tv_start_date =mView.findViewById(R.id.tv_start_date);
        tv_end_date =mView.findViewById(R.id.tv_end_date);
        tv_customer_name =mView.findViewById(R.id.tv_customer_name);
        tv_customer_phone =mView.findViewById(R.id.tv_customer_phone);
        tv_staff_name =mView.findViewById(R.id.tv_staff_name);
        mView.findViewById(R.id.ll_start_date).setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              DialogChooseDate dialogChooseDate = new DialogChooseDate(context, width, new DialogChooseDate.Dialogcallback() {
                  @Override
                  public void pickWeightResult(String date) {
                      startTime=date.replaceAll("-","/");
                      tv_start_date.setText(date);
                  }
              });
          }
      });
     mView.findViewById(R.id.ll_end_date).setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             DialogChooseDate dialogChooseDate = new DialogChooseDate(context, width, new DialogChooseDate.Dialogcallback() {
                 @Override
                 public void pickWeightResult(String date) {
                     endTime=date.replaceAll("-","/");
                     tv_end_date.setText(date);
                 }
             });
         }
     });



         if (menuBean!=null){
             brandSpinner.setItems(menuBean.getData().getBrand());
             brandSpinner.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

                 @Override public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                     brand=menuBean.getData().getBrand().get(position).getId()+"";
                 }
             });



             if (menuBean.getData().getService()!=null) {
                 serviceSpinner.setItems(menuBean.getData().getService());
                 serviceSpinner.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

                     @Override
                     public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                         service = menuBean.getData().getService().get(position).getId() + "";
                     }
                 });
             }

             typeSpinner.setItems(menuBean.getData().getType());
             typeSpinner.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

                 @Override public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                     type=menuBean.getData().getType().get(position).getId()+"";
                 }
             });
         }

        statusSpinner.setItems(statusStr);
        statusSpinner.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                status=position+1+"";
            }
        });

        mView.findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (searchClickListener!=null){
                    if (TextUtils.isEmpty(startTime)&&!TextUtils.isEmpty(endTime)){

                        startTime= TimeUtil.parseDate(0);
                    }
                    if (TextUtils.isEmpty(endTime)&&!TextUtils.isEmpty(startTime)){
                        endTime= TimeUtil.parseDate(System.currentTimeMillis());
                    }




                    if (!TextUtils.isEmpty(endTime)&&!TextUtils.isEmpty(startTime))
                    if (endTime.compareTo(startTime)<1){
                        UiUtil.showToast(R.string.please_select_error_time);
                        return;
                    }


                    customePhone=tv_customer_phone.getText().toString();
                    staffName=tv_staff_name.getText().toString();
                    customeName=tv_customer_name.getText().toString();

                    Log.e("request","status="+status+"   startTime="+startTime+"   endTime="+endTime+"   customeName="+customeName+"   customePhone="+customePhone+"   staffName="+staffName);
                    searchClickListener.onClick( startTime,  endTime,  customeName, customePhone ,staffName , type, service , brand,  status);
                }
                dissmiss();
            }
        });
    }

    private void dissmiss() {
        if (dialog!=null){
            dialog.dismiss();
        }
    }

    public OnSearchClickListener getSearchClickListener() {
        return searchClickListener;
    }

    public void setSearchClickListener(OnSearchClickListener searchClickListener) {
        this.searchClickListener = searchClickListener;
    }

    OnSearchClickListener  searchClickListener;
    public interface  OnSearchClickListener{

        void onClick(String startTime,String  endTime,String  customName,String customPhone ,String nikeName ,String type,String service ,String brand,String  status   );

    }

}
