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
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ServiceMenuBean;
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

    List<ServiceMenuBean.DataBean.CustomerBean> datas;
    ServiceMenuBean.DataBean.CustomerBean selectedCustomer;
    MyAdapter adapter;

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

        datas = MyApplication.getInstance().getMenuBean().getData().getCustomer();
        selectedCustomer = datas.get(0);

        list_choose = (ListView)mView.findViewById(R.id.list_choose);
        adapter = new MyAdapter();
        list_choose.setAdapter(adapter);
        list_choose.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                adapter.selectItem(i);
                selectedCustomer = datas.get(i);
            }
        });

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
                EventBus.getDefault().post(new EventCenter(Constants.CODE_SELECT_CUSTOMER_RETURN,selectedCustomer));
                break;
            case R.id.dialog_cancel:
                dissmiss();
                break;
        }
    }

    private class MyAdapter extends BaseAdapter{

        int selectedIndex = 0;

        public void selectItem(int position){
            selectedIndex = position;
            notifyDataSetChanged();
        }


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

            holder.tv_customer.setText(datas.get(i).getName());
            holder.tv_phone.setText(datas.get(i).getPhone());

            if(selectedIndex == i){
                holder.img_select.setVisibility(View.VISIBLE);
            }else{
                holder.img_select.setVisibility(View.INVISIBLE);
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
