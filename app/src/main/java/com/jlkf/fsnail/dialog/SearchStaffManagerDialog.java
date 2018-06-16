package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;

import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class SearchStaffManagerDialog {

    private final Context context;
    private final int width;
    private AlertDialog dialog;
    private View mView;

    public SearchStaffManagerDialog(Context context, int  width ) {
    this.context =context;
    this.width=width;
    }


    public void  showDiaglog(){
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_search_staff, null);
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
EditText et_staff_id,et_staff_nikename,et_staff_name,et_staff_phone;

    private void initView() {
        et_staff_id=mView.findViewById(R.id.et_staff_id);
        et_staff_nikename=mView.findViewById(R.id.et_staff_nikename);
        et_staff_name=mView.findViewById(R.id.et_staff_name);
        et_staff_phone=mView.findViewById(R.id.et_staff_phone);
        mView.findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (searchClickListener!=null){
                id=et_staff_nikename.getText().toString().trim();
                uName=et_staff_name.getText().toString().trim();
                nickName=et_staff_nikename.getText().toString().trim();
                phone=et_staff_phone.getText().toString().trim();
                searchClickListener.onClick(id,uName,nickName,phone);

            }
            dissmiss();
        }
    });


    }



    String   id;// 员工编号
    String   uName;//昵称
    String   nickName;//姓名
    String   phone;//手机
    private void dissmiss() {
        if (dialog!=null){
            dialog.dismiss();
        }
    }

    public OnSearchClickListener getSearchClickListener(OnSearchClickListener onSearchClickListener) {
        return searchClickListener;
    }

    public void setSearchClickListener(OnSearchClickListener searchClickListener) {
        this.searchClickListener = searchClickListener;
    }

    OnSearchClickListener  searchClickListener;
    public interface  OnSearchClickListener{

        void onClick(String id,String  uName,String  nickName,String phone);

    }

}
