package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.widget.Wheel.WheelPicker;

import org.greenrobot.eventbus.EventBus;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class InformationNotCopleteDialog implements View.OnClickListener{

    private Context mContex;
    View mView;
    private AlertDialog dialog ;

    private Button btn_agree,btn_disagree;

    public InformationNotCopleteDialog(Context context){
        mContex = context;
    }

    public void showDialog(){
        dialog = new AlertDialog.Builder(mContex).create();
        mView = LayoutInflater.from(mContex).inflate(R.layout.information_tips_dialog, null);

        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(null);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        btn_agree = (Button) mView.findViewById(R.id.btn_agree);
        btn_disagree = (Button) mView.findViewById(R.id.btn_disagree);
        btn_agree.setOnClickListener(this);
        btn_disagree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_disagree:
                disMiss();
                break;
            case R.id.btn_agree:
                disMiss();
                EventBus.getDefault().post(new EventCenter(Constants.CODE_TO_REGISTER));
                break;
        }
    }

    public void disMiss(){
        if(dialog != null){
            dialog.dismiss();
            dialog = null;
        }
    }



}
