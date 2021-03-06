package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
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

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.utils.TimeUtil;
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

public class SearchOrderDialog implements View.OnClickListener{

    private final Context context;
    private final int width;
    private AlertDialog dialog;
    private View mView;

    private TextView tv_date_start;
    private ImageView img_date_start;
    private TextViewSpinner spinner_status;
    private TextView tv_date_end;
    private TextView tv_search;
    private EditText et_phone;
    private EditText et_number;
    private ImageView img_date_end;

    String [] tests;
    List<String> datas = new ArrayList<String>();
    private int status = -1;

    public SearchOrderDialog(Context context, int width) {
        this.context = context;
        this.width = width;
    }


    public void showDiaglog() {
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_search_order, null);
        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM | Gravity.RIGHT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        tests = context.getResources().getStringArray(R.array.order_status);
        datas = Arrays.asList(tests);

        initView();
    }

    private void initView() {

        tv_date_start = (TextView) mView.findViewById(R.id.tv_date_start);
        img_date_start = (ImageView) mView.findViewById(R.id.img_date_start);
        img_date_end = (ImageView)mView.findViewById(R.id.img_date_end);
        tv_date_end = (TextView) mView.findViewById(R.id.tv_date_end);
        spinner_status = (TextViewSpinner) mView.findViewById(R.id.spinner_status);
        tv_search = (TextView)mView.findViewById(R.id.tv_search);
        et_phone = (EditText)mView.findViewById(R.id.et_phone);
        et_number = (EditText)mView.findViewById(R.id.et_number);

        spinner_status.setItems(datas);
        spinner_status.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {
                status = position+1;
            }
        });

        img_date_start.setOnClickListener(this);
        img_date_end.setOnClickListener(this);
        tv_search.setOnClickListener(this);
    }

    private void dissmiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_date_start:
                DialogChooseDate dialogChooseDate = new DialogChooseDate(context, width, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        tv_date_start.setText(date);
                    }
                });
                break;
            case R.id.img_date_end:
                DialogChooseDate dialogChooseDate2 = new DialogChooseDate(context, width, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        tv_date_end.setText(date);
                    }
                });
                break;
            case R.id.tv_search:
                dissmiss();
                Map<String, String> params = new HashMap<>();

                if(!TextUtils.isEmpty(tv_date_start.getText().toString())){
                    params.put("startTime", tv_date_start.getText().toString().trim());
                }

                if(!TextUtils.isEmpty(tv_date_end.getText().toString())){
                    params.put("endTime",tv_date_end.getText().toString().trim());
                }

                if(!TextUtils.isEmpty(et_phone.getText().toString().trim())){
                    params.put("customerPhone",et_phone.getText().toString().trim());
                }

                if(!TextUtils.isEmpty(et_number.getText().toString().trim())){
                    params.put("orderNumber",et_number.getText().toString());
                }

                if(status != -1){
                    params.put("status",String.valueOf(status));
                }

                EventBus.getDefault().post(new EventCenter(Constants.CODE_SEARCH_ORDERLIST,params));
                break;
        }
    }


}
