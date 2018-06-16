package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.support.design.widget.Snackbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.widget.myspinner.singletextviewspinner.TextViewSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class SearchBookDialog implements View.OnClickListener{

    String [] tests;
    List<String> lists = new ArrayList<String>();

    private final Context context;
    private final int width;
    private AlertDialog dialog;
    private View mView;
    private TextViewSpinner spinner_type,spinner_status;
    private TextView tv_serch;
    private TextView tv_date;
    private ImageView img_date;
    private TextView tv_time;
    private ImageView img_time;

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

        spinner_type = (TextViewSpinner)mView.findViewById(R.id.spinner_type);
        spinner_status = (TextViewSpinner)mView.findViewById(R.id.spinner_status);
        tv_serch = (TextView)mView.findViewById(R.id.tv_serch);
        tv_date = (TextView)mView.findViewById(R.id.tv_date);
        img_date = (ImageView)mView.findViewById(R.id.img_date);
        tv_time = (TextView)mView.findViewById(R.id.tv_time);
        img_time = (ImageView)mView.findViewById(R.id.img_time);

        tests = context.getResources().getStringArray(R.array.sports);
        lists = Arrays.asList(tests);
        initView();
    }

    private void initView(){

        tv_serch.setOnClickListener(this);
        img_date.setOnClickListener(this);
        img_time.setOnClickListener(this);

        spinner_type.setItems(lists);
        spinner_type.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner_type.setOnNothingSelectedListener(new TextViewSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(TextViewSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });

        spinner_status.setItems(lists);
        spinner_status.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                Snackbar.make(view, "Clicked " + item, Snackbar.LENGTH_LONG).show();
            }
        });
        spinner_status.setOnNothingSelectedListener(new TextViewSpinner.OnNothingSelectedListener() {

            @Override public void onNothingSelected(TextViewSpinner spinner) {
                Snackbar.make(spinner, "Nothing selected", Snackbar.LENGTH_LONG).show();
            }
        });

    }

    private void dissmiss() {
        if (dialog!=null){
            dialog.dismiss();
            dialog = null;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_serch:
                dissmiss();
                EventBus.getDefault().post(new EventCenter(Constants.CODE_CHECK_BOOK));
                break;
            case R.id.img_date:
                DialogChooseDate dialogChooseDate = new DialogChooseDate(context,width, new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        tv_date.setText(date);
                    }
                });
                break;
            case R.id.img_time:
                DialogChooseTime dialogChooseTime = new DialogChooseTime(context, width, new DialogChooseTime.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String time) {
                        tv_time.setText(time);
                    }
                });
                break;
        }
    }
}
