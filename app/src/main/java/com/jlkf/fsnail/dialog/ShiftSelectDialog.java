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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.adapter.NamesSelectListAdapter;
import com.jlkf.fsnail.adapter.ShiftSelectListAdapter;

import java.util.List;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class ShiftSelectDialog {

    private final List<String> nikeNames;
    private final Context context;
    private AlertDialog dialog;
    private View mView;
     ShiftSelectListAdapter madapter;

    public ShiftSelectDialog( Context context, List<String> nikeNames) {
    this.nikeNames =nikeNames;
    this.context =context;
    }


    public void  showDiaglog(){
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_shift_select, null);
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
        ListView names_list_view =mView.findViewById(R.id.shift_list_view);
        TextView dialog_ensure =mView.findViewById(R.id.dialog_ensure);
         madapter =new ShiftSelectListAdapter(context,nikeNames);

        names_list_view.setAdapter(madapter);

        names_list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                madapter.setSelctPosition(position);
            }
        });

        dialog_ensure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (madapter!=null) {
                    if (onEnsureClickListenr!=null){
                        onEnsureClickListenr.onEnsureClick(madapter.getSelectPosition());
                    }

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

    public OnEnsureClickListenr getOnEnsureClickListenr() {
        return onEnsureClickListenr;
    }

    public void setOnEnsureClickListenr(OnEnsureClickListenr onEnsureClickListenr) {
        this.onEnsureClickListenr = onEnsureClickListenr;
    }

    OnEnsureClickListenr onEnsureClickListenr;
    public  interface  OnEnsureClickListenr{
        void onEnsureClick(int  position);

    }
}
