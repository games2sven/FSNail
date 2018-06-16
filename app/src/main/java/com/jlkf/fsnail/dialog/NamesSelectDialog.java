package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.adapter.NamesSelectListAdapter;
import com.jlkf.fsnail.bean.ServiceMenuBean;

import java.util.List;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class NamesSelectDialog {

    private final List<String> nikeNames;
    private final Context context;
    private final int type;//1是员工昵称  2是顾客姓名
    private AlertDialog dialog;
    private View mView;

    public NamesSelectDialog(int type, Context context, List<String> nikeNames) {
        this.type=type;
    this.nikeNames =nikeNames;
    this.context =context;
    }


    public void  showDiaglog(){
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_names_select, null);
        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        mView.findViewById(R.id.dialog_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dissmiss();
            }
        });
        ((TextView)mView.findViewById(R.id.dialog_names_title)).setText(type==1?"员工昵称":"顾客姓名");
        ListView names_list_view =mView.findViewById(R.id.names_list_view);
        names_list_view.setAdapter(new NamesSelectListAdapter(context,nikeNames));

        names_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listener!=null){
                    listener.onItemClick(type,nikeNames.get(position));
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

    public OnItemClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    OnItemClickListener  listener;


    public   interface  OnItemClickListener{

        void  onItemClick(int  type ,String  s);

    }

}
