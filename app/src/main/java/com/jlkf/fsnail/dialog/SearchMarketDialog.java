package com.jlkf.fsnail.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.GoodsTypeBean;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.UiUtil;
import com.jlkf.fsnail.widget.myspinner.singletextviewspinner.TextViewSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class SearchMarketDialog implements View.OnClickListener{

    private final Context context;
    private final int width;
    private AlertDialog dialog;
    private View mView;

    private TextViewSpinner spinner_type;
    private TextViewSpinner spinner_brand;
    private EditText et_pro_name;

    List<ServiceMenuBean.DataBean.BrandBean> brandBeanList;
    List<ServiceMenuBean.DataBean.TypeBean> typeList;
    List<String> types = new ArrayList<>();
    List<String> brands = new ArrayList<>();
    Map<String,String> params = new HashMap<>();

    String typeID;
    String brandID;

    public SearchMarketDialog(Context context, int  width ) {
        this.context =context;
        this.width=width;
    }


    public void  showDiaglog(){
        dissmiss();
        dialog = new AlertDialog.Builder(context).create();
        mView = LayoutInflater.from(context).inflate(R.layout.dialog_search_market, null);
        dialog.show();
        dialog.setContentView(mView);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        Window window = dialog.getWindow();
        window.setBackgroundDrawable(new BitmapDrawable());
        window.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.BOTTOM|Gravity.RIGHT);
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);

         brandBeanList = MyApplication.getInstance().getMenuBean().getData().getBrand();
         typeList = MyApplication.getInstance().getMenuBean().getData().getType();

        loadData();
    }

    public void loadData(){
        if(brandBeanList != null){
            for(ServiceMenuBean.DataBean.BrandBean brandBean :brandBeanList){
                brands.add(brandBean.getBrandname());
            }
        }

        if(typeList != null){
            for(ServiceMenuBean.DataBean.TypeBean typeBean :typeList){
                types.add(typeBean.getName());
            }
        }
        initView();
    }

    public void initView(){
        et_pro_name = (EditText) mView.findViewById(R.id.et_pro_name);

        spinner_type = (TextViewSpinner)mView.findViewById(R.id.spinner_type);
        if(types != null){
            spinner_type.setItems(types);
        }
        spinner_type.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {
                typeID = String.valueOf(typeList.get(position).getId());
            }
        });

        spinner_brand = (TextViewSpinner)mView.findViewById(R.id.spinner_brand);
        if(brands != null){
            spinner_brand.setItems(brands);
        }
        spinner_brand.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {
                brandID = String.valueOf(brandBeanList.get(position).getId());
            }
        });

        mView.findViewById(R.id.tv_search).setOnClickListener(this);

    }

    private void dissmiss() {
        if (dialog!=null){
            dialog.dismiss();
        }
    }

    private boolean checkIsOk(){
//        if(TextUtils.isEmpty(typeID)){
//            UiUtil.showToast(R.string.please_input_name);
//            return false;
//        }
//
//        if(TextUtils.isEmpty(brandID)){
//            UiUtil.showToast(R.string.please_input_name);
//            return false;
//        }

        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_search:
                if(checkIsOk()){
                    if(!TextUtils.isEmpty(et_pro_name.getText().toString().trim())){
                        params.put("goodsName",et_pro_name.getText().toString().trim());
                    }
                    if(!TextUtils.isEmpty(typeID)){
                        params.put("gTypeId",typeID);
                    }
                    if(!TextUtils.isEmpty(brandID)){
                        params.put("brandId",brandID);
                    }
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_SEARCH_GOODS,params));
                };
                break;
        }
    }
}
