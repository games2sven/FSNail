package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;


import com.jlkf.fsnail.R;
import com.jlkf.fsnail.widget.YbDateSelectorWheelView;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author: Yangbin
 * time  : 2016/12/9 17:55
 * desc  : 选择体重
 */

public class DialogChooseDate {
    Context context;
    Dialogcallback dialogcallback;
    Dialog dialog;


    @Bind(R.id.tv_cancle)
    TextView tvCancle;
    @Bind(R.id.tv_sure)
    TextView tvSure;
    @Bind(R.id.pdwv_date_selector_wheelView)
    YbDateSelectorWheelView pdwvDateSelectorWheelView;



    public DialogChooseDate(Context con,int  width , Dialogcallback callback) {
        this.context = con;
        this.dialogcallback = callback;
        dialog = new AlertDialog.Builder(con).create();
        dialog.show();
        dialog.getWindow().setContentView(R.layout.dialog_pickdate);
        ButterKnife.bind(this, dialog);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
        Window dialogWindow = dialog.getWindow();
        dialogWindow.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialogWindow.setGravity(Gravity.BOTTOM|Gravity.RIGHT);

        Calendar c = Calendar.getInstance();
        pdwvDateSelectorWheelView.setCurrentYear(c.get(Calendar.YEAR) + "");
        pdwvDateSelectorWheelView.setCurrentMonth(c.get(Calendar.MONTH) + "");
        pdwvDateSelectorWheelView.setCurrentDay(c.get(Calendar.DAY_OF_MONTH) + "");
    }

    @OnClick({R.id.tv_cancle, R.id.tv_sure})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_cancle:
                dialog.dismiss();
                break;
            case R.id.tv_sure:
                dialogcallback.pickWeightResult(pdwvDateSelectorWheelView.getSelectedDate());
                dismiss();
                break;
        }
    }

    public interface Dialogcallback {
        void pickWeightResult(String date);
    }

    public void show() {
        dialog.show();
    }

    public void hide() {
        dialog.hide();
    }

    public void dismiss() {
        dialog.dismiss();
    }
}
