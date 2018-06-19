package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.AddShopCarBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Administrator on 2017/12/12 0012.
 */

public class ClearShopCarDialog implements View.OnClickListener{

    private Context mContex;
    View mView;
    private AlertDialog dialog ;

    private Button btn_disagree;
    private Button btn_agree;

    List<AddShopCarBean.DataBean> mDatas;

    public ClearShopCarDialog(Context context, List<AddShopCarBean.DataBean> datas){
        mContex = context;
        mDatas = datas;
    }

    public void showDialog(){
        dialog = new AlertDialog.Builder(mContex).create();
        mView = LayoutInflater.from(mContex).inflate(R.layout.information_tips_dialog, null);
        TextView tv_tips = (TextView) mView.findViewById(R.id.tv_tips);
        tv_tips.setText(R.string.clear_shopcar_tips);

        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(null);
        window.setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);

        initView();

    }

    private void initView(){
        btn_disagree = (Button)mView.findViewById(R.id.btn_disagree);
        btn_agree = (Button)mView.findViewById(R.id.btn_agree);

        btn_disagree.setOnClickListener(this);
        btn_agree.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_disagree:
                dissmiss();
                break;
            case R.id.btn_agree:
                dissmiss();
                EventBus.getDefault().post(new EventCenter(Constants.CODE_CLEAR_ACCOUNT_SHOPCAR));
                break;
        }
    }

    private void dissmiss() {
        if (dialog!=null){
            dialog.dismiss();
            dialog = null;
        }
    }


}
