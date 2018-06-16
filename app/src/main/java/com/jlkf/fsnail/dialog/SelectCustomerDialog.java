package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.CustomerBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class SelectCustomerDialog implements View.OnClickListener{

    private final Context context;
    private AlertDialog dialog;
    private View mView;
    private ListView list_choose;
    private TextView text_ok;
    private ImageView dialog_cancel;

    private List<CustomerBean> datas = new ArrayList<CustomerBean>();

    public SelectCustomerDialog(Context context) {
        this.context =context;
    }


    public void  showDiaglog(){
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.select_phone, null);
        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        for(int i = 0;i<15;i++){
            CustomerBean bean = new CustomerBean();
            datas.add(bean);
        }

        list_choose = (ListView)mView.findViewById(R.id.list_choose);
        list_choose.setAdapter(new MyAdapter());
        text_ok = (TextView) mView.findViewById(R.id.text_ok);
        text_ok.setOnClickListener(this);
        dialog_cancel = (ImageView)mView.findViewById(R.id.dialog_cancel);
        dialog_cancel.setOnClickListener(this);

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
            case R.id.text_ok:
                dissmiss();
                EventBus.getDefault().post(new EventCenter(Constants.CODE_SELECT_CUSTOMER_RETURN));
                break;
            case R.id.dialog_cancel:
                dissmiss();
                break;
        }
    }

    private class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return datas.size();
        }

        @Override
        public Object getItem(int i) {
            return datas.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            ViewHolder holder = null;
            if(view == null){
                view = View.inflate(context,R.layout.item_select_phone,null);
                holder = new ViewHolder();
                holder.tv_customer = (TextView) view.findViewById(R.id.tv_customer);
                holder.tv_phone = (TextView) view.findViewById(R.id.tv_phone);
                holder.img_select = (ImageView) view.findViewById(R.id.img_select);
                view.setTag(holder);
            }else{
                holder = (ViewHolder) view.getTag();
            }

            return view;
        }

        public class ViewHolder{
            TextView tv_customer;
            TextView tv_phone;
            ImageView img_select;
        }

    }


}
