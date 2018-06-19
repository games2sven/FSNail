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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.bean.EventCenter;
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

public class SearchCardDialog implements View.OnClickListener {

    String[] tests;
    List<String> lists = new ArrayList<String>();

    private final Context context;
    private final int width;
    private AlertDialog dialog;
    private View mView;
    public TextViewSpinner product_type,card_type;
    private LinearLayout ll_date;
    private TextView tv_date;
    private EditText et_bind_customer;
    private EditText et_card_number;
    private EditText buy_customer;
    private TextView tv_search;
    private EditText et_price_start;
    private EditText et_price_end;

    public SearchCardDialog(Context context, int width) {
        this.context = context;
        this.width = width;
    }


    public void showDiaglog() {
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_search_card, null);
        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM | Gravity.RIGHT);

        product_type = (TextViewSpinner) mView.findViewById(R.id.product_type);
        card_type = (TextViewSpinner) mView.findViewById(R.id.card_type);
        et_bind_customer = (EditText)mView.findViewById(R.id.et_bind_customer);
        et_card_number = (EditText)mView.findViewById(R.id.et_card_number);
        buy_customer = (EditText)mView.findViewById(R.id.buy_customer);
        et_price_start = (EditText)mView.findViewById(R.id.et_price_start);
        et_price_end =(EditText)mView.findViewById(R.id.et_price_end);

        ll_date = (LinearLayout) mView.findViewById(R.id.ll_date);
        tv_date = (TextView)mView.findViewById(R.id.tv_date);
        tv_search = (TextView)mView.findViewById(R.id.tv_search);
        ll_date.setOnClickListener(this);
        tv_search.setOnClickListener(this);

        tests = context.getResources().getStringArray(R.array.sports);
        lists = Arrays.asList(tests);
        initView();
    }

    private void initView() {

        product_type.setItems(lists);
        card_type.setItems(lists);


//        spinner_status.setItems(lists);
//        spinner_status.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {
//
//            @Override
//            public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
//                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
//            }
//        });
//        spinner_status.setOnNothingSelectedListener(new TextViewSpinner.OnNothingSelectedListener() {
//
//            @Override
//            public void onNothingSelected(TextViewSpinner spinner) {
//                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
//            }
//        });

    }

    private void dissmiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_date:
//                EventBus.getDefault().post(new EventCenter(Constants.CODE_CARD_SELECT_DATE));
                DialogChooseDate dialogChooseDate = new DialogChooseDate(context,((MainActivity) context).getRightWidth(), new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        tv_date.setText(date);
                    }
                });
                break;
            case R.id.tv_search:
                Map<String,String> params = getParams();
                EventBus.getDefault().post(new EventCenter(Constants.CODE_SEARCH_CARD,params));
                break;
        }
    }

    private Map<String,String> getParams(){
        Map<String,String> params = new HashMap<>();

        if(!TextUtils.isEmpty(et_bind_customer.getText().toString().trim())){
            params.put("isBandUser",et_bind_customer.getText().toString().trim());
        }

        if(!TextUtils.isEmpty(et_card_number.getText().toString().trim())){
            params.put("cardNumber",et_card_number.getText().toString().trim());
        }

        if(!TextUtils.isEmpty(et_price_start.getText().toString().trim())){
            params.put("startPrice",et_price_start.getText().toString().trim());
        }

        if(!TextUtils.isEmpty(et_price_end.getText().toString().trim())){
            params.put("endPrice",et_price_end.getText().toString().trim());
        }

        if(!TextUtils.isEmpty(buy_customer.getText().toString().trim())){
            params.put("endPrice",et_price_end.getText().toString().trim());
        }

        return params;
    }

}
