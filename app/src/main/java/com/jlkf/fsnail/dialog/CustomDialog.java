package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.jlkf.fsnail.R;

public class CustomDialog {


    Context mContext;

    AlertDialog mDia;
    private OnButtonClickListener listenter;

    public CustomDialog(Context c) {
        this.mContext = c;
    }



   public void ShowDialog(String  btn1,String btn2,String title){
       ShowDialog(btn1,btn2,title,null,null,null, Gravity.CENTER,Gravity.CENTER);

   }
    /**
     *
     * @param btn1 按钮一内容
     * @param btn2  按钮二内容
     * @param title  标题
     * @param content  副标题
     * @param btn1Color  按钮一颜色
     * @param btn2Color   按钮二颜色
     * @param titleGravity  按钮一的gravity
     * @param contentGravity  按钮二的gravity
     */
    public void ShowDialog(String btn1, String btn2, String title, String content, String btn1Color, String btn2Color,int titleGravity,int contentGravity) {
        mDia = new AlertDialog.Builder(mContext).create();
        View mView = LayoutInflater.from(mContext).inflate(R.layout.dialgot_custom, null);
        mDia.show();

        mDia.setContentView(mView);
        mDia.setCancelable(true);
        mDia.setCanceledOnTouchOutside(true);
        mDia.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

        Window win = mDia.getWindow();
        DisplayMetrics dm = mContext.getResources().getDisplayMetrics();
        win.setBackgroundDrawableResource(R.color.transparent);
        win.setLayout((int) mContext.getResources().getDimension(R.dimen.DIMEN_600PX), WindowManager.LayoutParams.WRAP_CONTENT);

        TextView titleView = (TextView) mView.findViewById(R.id.dialog_title);
        TextView contentView = (TextView) mView.findViewById(R.id.dialog_content);
        TextView btn1View = (TextView) mView.findViewById(R.id.dialog_btn1);
        TextView btn2View = (TextView) mView.findViewById(R.id.dialog_btn2);
        View line =mView.findViewById(R.id.dialog_line);


        if (TextUtils.isEmpty(btn1)||TextUtils.isEmpty(btn2)){
            line.setVisibility(View.GONE);
        }else{
            line.setVisibility(View.VISIBLE);
        }

        setTextView(titleView, title, null);
        setTextView(contentView, content, null);
        setTextView(btn1View, btn1, btn1Color);
        setTextView(btn2View, btn2, btn2Color);
            titleView.setGravity(titleGravity);
            contentView.setGravity(contentGravity);

        btn1View.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenter!=null){
                    listenter.onButton1Click(view);

                }
                dismiss();
            }
        });

        btn2View.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listenter!=null){
                    listenter.onButton2Click(view);
                }
                dismiss();
            }
        });

    }


    private void setTextView(TextView tv, String text, String textcolor) {

        if (!TextUtils.isEmpty(text)) {
            tv.setText(text);
            tv.setVisibility(View.VISIBLE);
            if (!TextUtils.isEmpty(textcolor)){
            tv.setTextColor(Color.parseColor(textcolor));
            }
        } else {
            tv.setVisibility(View.GONE);
        }

    }

    public void setOnButtonClickListener(OnButtonClickListener listener){

        this.listenter =listener;
    }

    public interface OnButtonClickListener{

        void onButton1Click(View v);
        void onButton2Click(View v);


    }

    public void dismiss(){
        if (mDia!=null){

        mDia.dismiss();}
    }

    public boolean isShow() {
            return mDia.isShowing();
    }

}
