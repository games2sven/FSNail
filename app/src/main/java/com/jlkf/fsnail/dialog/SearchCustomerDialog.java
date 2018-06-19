package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.utils.TimeUtil;
import com.jlkf.fsnail.utils.UiUtil;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class SearchCustomerDialog {

    private final Context context;
    private final int width;
    private AlertDialog dialog;
    private View mView;
    OnSearchClickListener  searchClickListener;
    public SearchCustomerDialog(Context context, int  width ) {
    this.context =context;
    this.width=width;
    }

    String   regStart;  //注册日区间始例：2018/6/1
    String  regEnd  ;//注册日区间终
    String   name;//客户名
    String  phone;//手机
    String startInt;//积分区间
    String endInt;//积分区间
    String birStart;//生日区间始
    String birEnd;//生日区间终
    public void  showDiaglog(){
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_search_customer, null);
        initView();
        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
    }

    TextView tv_regStart,tv_regEnd,tv_birStart,tv_birEnd;
    EditText et_name,et_phone,et_startInt,et_endInt;
    private void initView() {
        tv_regStart =  mView.findViewById(R.id.tv_regStart);
        tv_regEnd =  mView.findViewById(R.id.tv_regEnd);
        tv_birStart =  mView.findViewById(R.id.tv_birStart);
        tv_birEnd =  mView.findViewById(R.id.tv_birEnd);
        et_name =  mView.findViewById(R.id.et_name);
        et_phone =  mView.findViewById(R.id.et_phone);
        et_startInt =  mView.findViewById(R.id.et_startInt);
        et_endInt =  mView.findViewById(R.id.et_endInt);

        mView.findViewById(R.id.ll_regStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChooseDate dialogChooseDate = new DialogChooseDate(context, width, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        regStart=date.replaceAll("-","/");
                        tv_regStart.setText(date);
                    }
                });
            }
        });
        mView.findViewById(R.id.ll_regEnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChooseDate dialogChooseDate = new DialogChooseDate(context, width, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        regEnd=date.replaceAll("-","/");
                        tv_regEnd.setText(date);
                    }
                });
            }
        });
        mView.findViewById(R.id.ll_birStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChooseDate dialogChooseDate = new DialogChooseDate(context, width, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        birStart=date.replaceAll("-","/");
                        tv_birStart.setText(date);
                    }
                });
            }
        });
        mView.findViewById(R.id.ll_birEnd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChooseDate dialogChooseDate = new DialogChooseDate(context, width, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        birEnd=date.replaceAll("-","/");
                        tv_birEnd.setText(date);
                    }
                });
            }
        });
        mView.findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Log.e("search",searchClickListener+"");
                if (searchClickListener!=null){
                    if (TextUtils.isEmpty(regStart)&&!TextUtils.isEmpty(regEnd)){
                        regStart= TimeUtil.parseDate(0);
                    }
                    if (TextUtils.isEmpty(regEnd)&&!TextUtils.isEmpty(regStart)){
                        regEnd= TimeUtil.parseDate(System.currentTimeMillis());
                    }

                    if (!TextUtils.isEmpty(regStart)&&!TextUtils.isEmpty(regEnd))
                        if (regEnd.compareTo(regStart)<1){
                            UiUtil.showToast(R.string.please_select_reg_error_time);
                            return;
                        }




                    if (TextUtils.isEmpty(birStart)&&!TextUtils.isEmpty(birEnd)){
                        birStart= TimeUtil.parseDate(0);
                    }
                    if (TextUtils.isEmpty(birEnd)&&!TextUtils.isEmpty(birStart)){
                        birEnd= TimeUtil.parseDate(System.currentTimeMillis());
                    }

                    if (!TextUtils.isEmpty(birStart)&&!TextUtils.isEmpty(birEnd))
                        if (birEnd.compareTo(birStart)<1){
                            UiUtil.showToast(R.string.please_select_bir_error_time);
                            return;
                        }



                    name =et_name.getText().toString().trim();
                    phone =et_phone.getText().toString().trim();

                    startInt=et_startInt.getText().toString().trim();
                    endInt=et_endInt.getText().toString().trim();

                    if (TextUtils.isEmpty(startInt)){
                        startInt=0+"";
                    }
                    if (TextUtils.isEmpty(endInt)){
                        endInt=Integer.MAX_VALUE+"";
                    }

                    if (Integer.parseInt(endInt)<Integer.parseInt(startInt)){
                        UiUtil.showToast(R.string.please_select_jifen_error);
                          return;
                    }


                    searchClickListener.onClick( regStart,  regEnd,  name, phone , startInt , endInt, birStart , birEnd  );
                    dissmiss();
                }

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


    public interface  OnSearchClickListener{

        void onClick(String regStart,String  regEnd,String  name,String phone ,String startInt ,String endInt,String birStart ,String birEnd  );

    }


}

