package com.jlkf.fsnail.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ServiceBean;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.CustomDialog;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.TimeUtil;
import com.jlkf.fsnail.utils.UiUtil;
import com.jlkf.fsnail.utils.VerifyUtil;
import com.jlkf.fsnail.widget.myspinner.singletextviewspinner.TextViewSpinner;

import org.greenrobot.eventbus.EventBus;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/31 0031.
 */

public class AddEditServiceFragment extends BaseFragment {
    MainActivity mainActivity;
    @Bind(R.id.et_appoint_name)
    EditText et_appoint_name;
    @Bind(R.id.et_objectCheck)
    EditText et_objectCheck;
    @Bind(R.id.et_date)
    EditText et_date;
//    @Bind(R.id.et_time)
//    EditText et_time;
    @Bind(R.id.et_appoint_phone)
    TextView et_appoint_phone;
    @Bind(R.id.sp_category)
    TextViewSpinner sp_category;
    @Bind(R.id.sp_brand)
    TextViewSpinner sp_brand;
    @Bind(R.id.sp_service)
    TextViewSpinner sp_service;
    @Bind(R.id.sp_book_time)
    TextViewSpinner sp_staff;
    @Bind(R.id.service_edit_star)
    TextView service_edit_star;
    @Bind(R.id.service_edit_stop)
    TextView service_edit_stop;
    @Bind(R.id.ll_service_price)
    View ll_service_price;
    @Bind(R.id.ll_date)
    View ll_date;
    @Bind(R.id.et_price)
    TextView et_price;
    @Bind(R.id.tv_service_title)
    TextView tv_service_title;
    private MaterialDialog loginlDialog;
    @SuppressLint("HandlerLeak")
    private Handler mHandler =new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 100:
                    customerName=et_appoint_name.getText().toString().trim();
                    customerPhone=et_appoint_phone.getText().toString().trim();
                    queryCustomerInfo(customerName,customerPhone);
                    break;
            }
        }
    };

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    int  type;//0 添加服务   1 修改 服务
    ServiceBean.DataBean dataBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mainActivity= (MainActivity) getActivity();
                 View rootView  =LayoutInflater.from(mainActivity).inflate(R.layout.fragment_add_service,null);

        ButterKnife.bind(this,rootView);
        type=getArguments().getInt("type");
        dataBean= (ServiceBean.DataBean) getArguments().getSerializable("data");
        initView();
        initNet();
                 return  rootView;
    }

    private void initNet() {
        queryCustomerInfo(customerName,customerPhone);
    }

    ServiceMenuBean serviceBean;

    private void initView() {
      if (type==1){
          service_edit_star.setVisibility(View.VISIBLE);
          service_edit_stop.setVisibility(View.VISIBLE);
          ll_service_price.setVisibility(View.VISIBLE);
          ll_date.setVisibility(View.VISIBLE);
          tv_service_title.setText(R.string.tv_change_service);

          if (dataBean!=null) {
              if (dataBean.getStatus() == 1) {
                  service_edit_star.setEnabled(true);
                  service_edit_stop.setEnabled(false);
                  service_edit_stop.setBackgroundResource(R.drawable.appoint_opera_red_transparent);
              } else if (dataBean.getStatus() == 2) {
                  service_edit_star.setEnabled(false);
                  service_edit_stop.setEnabled(true);
                  service_edit_star.setBackgroundResource(R.drawable.appoint_opera_green_transparent);
              } else {
                  service_edit_star.setVisibility(View.GONE);
                  service_edit_stop.setVisibility(View.GONE);
              }
          }
      }else{
          service_edit_star.setVisibility(View.GONE);
          service_edit_stop.setVisibility(View.GONE);
          ll_service_price.setVisibility(View.GONE);
          ll_date.setVisibility(View.GONE);
          tv_service_title.setText(R.string.tv_add_service);
      }


      serviceBean   =  MyApplication.getInstance().getMenuBean();
      if (serviceBean!=null){
       sp_brand.setItems( serviceBean.getData().getBrand());

       sp_category.setItems( serviceBean.getData().getType());

       sp_staff.setItems( serviceBean.getData().getStaff());


      }
        sp_brand.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {

                brand =serviceBean.getData().getBrand().get(position).getId()+"";
            }
        });
        sp_category.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {
                sp_service.setItems(serviceBean.getData().getType().get(position).getServiceList());
                sp_service.setSelectedIndex(0);
                typeId=serviceBean.getData().getType().get(position).getId()+"";
                et_price.setText(serviceBean.getData().getType().get(position).getServiceList().get(0).getPrice()+"€");
            }
        });
        sp_staff.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {
                staffId =serviceBean.getData().getStaff().get(position).getId()+"";
            }
        });



        if (dataBean!=null){
            et_appoint_name.setText(dataBean.getCustomerName());
           et_appoint_phone.setText(dataBean.getCustomerPhone());
            et_date.setText(dataBean.getOptime());
            customerName =dataBean.getCustomerName();
            customerPhone=dataBean.getCustomerPhone();
//            et_time.setText(dataBean.getStartTime());

            if (serviceBean==null) return;
           for (ServiceMenuBean.DataBean.BrandBean brandBean :serviceBean.getData().getBrand()){
                if (TextUtils.isEmpty(dataBean.getBrand())||dataBean.getBrandId()==dataBean.getBrandId()){
                    brand= String.valueOf(brandBean.getId());
                    sp_brand.setSelectedIndex(serviceBean.getData().getBrand().indexOf(brandBean));
                    break;
                }
           }
           for (ServiceMenuBean.DataBean.StaffBean staffBean :serviceBean.getData().getStaff()){
                if (TextUtils.isEmpty(dataBean.getUName())||dataBean.getStaffId()==staffBean.getId()){
                    staffId= String.valueOf(staffBean.getId());
                    sp_staff.setSelectedIndex(serviceBean.getData().getStaff().indexOf(staffBean));
                    break;
                }
           }
           for (ServiceMenuBean.DataBean.TypeBean typeBean :serviceBean.getData().getType()){
                if (TextUtils.isEmpty(dataBean.getType())||dataBean.getTypeId()==typeBean.getId()){
                    typeId=typeBean.getId()+"";
                    sp_category.setSelectedIndex(serviceBean.getData().getType().indexOf(typeBean));
                    sp_service.setItems(typeBean.getServiceList());
                    break;
                }
           }
            for (ServiceMenuBean.DataBean.TypeBean.ServiceListBean serviceListBean :serviceBean.getData().getType().get(sp_category.getSelectedIndex()).getServiceList()){
                if (TextUtils.isEmpty(dataBean.getService())||dataBean.getServiceId()==serviceListBean.getId()){
                    sp_service.setSelectedIndex(serviceBean.getData().getType().get(sp_category.getSelectedIndex()).getServiceList().indexOf(serviceListBean));
                    service= String.valueOf(serviceListBean.getId());
                               et_price.setText(serviceListBean.getPrice()+"€");
                    break;
                }
            }

        }else{

            if (serviceBean==null) return;
            sp_brand.setSelectedIndex(0);
            brand= String.valueOf(serviceBean.getData().getBrand().get(0).getId());
            sp_category.setSelectedIndex(0);
            typeId= String.valueOf(serviceBean.getData().getType().get(0).getId());
            sp_staff.setSelectedIndex(0);
            staffId= String.valueOf(serviceBean.getData().getStaff().get(0).getId());
            sp_service.setItems(serviceBean.getData().getType().get(0).getServiceList());
            sp_service.setSelectedIndex(0);
            service= String.valueOf(serviceBean.getData().getType().get(0).getServiceList().get(0).getId());
            et_price.setText(serviceBean.getData().getType().get(0).getServiceList().get(0).getPrice()+"€");
        }
        sp_service.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {
                et_price.setText(((ServiceMenuBean.DataBean.TypeBean.ServiceListBean)(sp_service.getItems().get(position))).getPrice()+"€");
                service= String.valueOf(((ServiceMenuBean.DataBean.TypeBean.ServiceListBean)(sp_service.getItems().get(position))).getId());
            }
        });

        et_appoint_name.addTextChangedListener(new MyTextWatch());
        et_appoint_phone.addTextChangedListener(new MyTextWatch());
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.service_add_back,R.id.service_add_ensure})
    public   void finishFragment(View view){


            if (view.getId()== R.id.service_add_ensure) {
                if (type==0) {
                    addNewService();
                }else{
                    modifService();
                }
            }else{

                mainActivity.popBackFragment(0);
                mainActivity.addServiceFragment=null;

            }



    }
    String   staffId;
    String   id;//true
    String   brand;
    String    service;
    String  typeId;
    String  customerName;
    String customerPhone;
    String  objectCheck;//寄存号码
    String  optime;//日期
    String startPrice;

    private void   addNewService(){

        customerName=et_appoint_name.getText().toString().trim();
        customerPhone=et_appoint_phone.getText().toString().trim();

        if (TextUtils.isEmpty(customerName)){
            UiUtil.showToast(R.string.enter_custom_tip);
            return;
        }
        if (TextUtils.isEmpty(customerPhone)){
            UiUtil.showToast(R.string.enter_custom_photo_tip);
            return;
        }
        if (TextUtils.isEmpty(brand)){
            UiUtil.showToast(R.string.enter_brand_tip);
            return;
        }
        if (TextUtils.isEmpty(service)){
            UiUtil.showToast(R.string.enter_service_tip);
            return;
        }
        if (TextUtils.isEmpty(staffId)){
            UiUtil.showToast(R.string.enter_staff_tip);
            return;
        }
        Map<String,String> params = new HashMap<>();
        addParams(params,"customerName",customerName);
        addParams(params,"customerPhone",customerPhone);
        addParams(params,"brand",brand);
        addParams(params,"service",service);
        addParams(params,"staffId",staffId);
        addParams(params,"type",typeId);
        Log.e("reponseparams",OKHttpUtils.getMapParamStr(params));
        loginlDialog = UiUtil.getMaterialDialog(mainActivity, UiUtil.getString(R.string.loading));
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.ADD_NEW_SERVICE, this, params, new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {
                if (loginlDialog!=null){
                    loginlDialog.dismiss();
                }
                if (response.getCode()==200){
                    mainActivity.popBackFragment(0);
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_UPDATE_SERVICE));
                }else{
                    UiUtil.showToast(response.getMsg());

                }
            }

            @Override
            public void onFailure(String errorMsg) {
                UiUtil.showToast(errorMsg);
                if (loginlDialog!=null){
                    loginlDialog.dismiss();
                }
            }
        });


    }




    private void  modifService(){
        id= String.valueOf(dataBean.getId());
        customerName=et_appoint_name.getText().toString().trim();
        customerPhone=et_appoint_phone.getText().toString().trim();

        if (TextUtils.isEmpty(id)){
            UiUtil.showToast(R.id.no_id);
            return;
        }

        if (TextUtils.isEmpty(customerName)){
            UiUtil.showToast(R.string.enter_custom_tip);
            return;
        }
        if (TextUtils.isEmpty(customerPhone)){
            UiUtil.showToast(R.string.enter_custom_photo_tip);
            return;
        }
        if (TextUtils.isEmpty(brand)){
            UiUtil.showToast(R.string.enter_brand_tip);
            return;
        }
        if (TextUtils.isEmpty(service)){
            UiUtil.showToast(R.string.enter_service_tip);
            return;
        }
        if (TextUtils.isEmpty(staffId)){
            UiUtil.showToast(R.string.enter_staff_tip);
            return;
        }
        optime= et_date.getText().toString().trim().replace("-","/");// objectCheck startPrice
        if (!VerifyUtil.isDate(optime)){
            UiUtil.showToast(R.string.please_input_date_format);
            return;
        }

        objectCheck =et_objectCheck.getText().toString().trim();
        Map<String ,String> params = new HashMap<>();
        addParams(params,"staffId",staffId);
        addParams(params,"id",id);
        addParams(params,"brand",brand);
        addParams(params,"service",service);
        addParams(params,"customerName",customerName);
        addParams(params,"customerPhone",customerPhone);
        addParams(params,"objectCheck",objectCheck);
        addParams(params,"optime",optime);
        addParams(params,"startPrice",startPrice);
        params.put("type",typeId);

        Log.e("reponseparams",OKHttpUtils.getMapParamStr(params));
        loginlDialog = UiUtil.getMaterialDialog(mainActivity, UiUtil.getString(R.string.loading));
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.UPDATE_SERVICE, this, params, new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {
                if (loginlDialog!=null){
                    loginlDialog.dismiss();
                }
                if (response.getCode()==200){
                    mainActivity.popBackFragment(0);
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_UPDATE_SERVICE));
                }else{
                    UiUtil.showToast(response.getMsg());

                }


            }

            @Override
            public void onFailure(String errorMsg) {

                UiUtil.showToast(errorMsg);
                if (loginlDialog!=null){
                    loginlDialog.dismiss();
                }
            }
        });



    }


    @OnClick({R.id.service_edit_star,R.id.service_edit_stop})
    public   void  changeStatus(View view ){
        switch (view.getId()){
            case R.id.service_edit_star :
            case R.id.service_edit_stop :
                final CustomDialog dialog  = new CustomDialog(mainActivity);
                dialog.ShowDialog(getString(R.string.btn_ensure),getString(R.string.tv_cancel),dataBean.getStatus()==1?getString(R.string.tv_start_service_msg):getString(R.string.tv_stop_service_msg));
                dialog.setOnButtonClickListener(new CustomDialog.OnButtonClickListener() {
                    @Override
                    public void onButton1Click(View v) {
                        dialog.dismiss();
                        changeServiceStatus(dataBean.getId(),dataBean.getStatus()==1?2:3);
                    }

                    @Override
                    public void onButton2Click(View v) {
                        dialog.dismiss();
                    }
                });
                break;



        }

    }

    public  void  changeServiceStatus(int  id  ,int  status) {
        Map<String, String> params = new HashMap<>();
        params.put("id", id + "");
        params.put("status", status + "");

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.UPDATE_SERVICE_STATUS, this, params, new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {
                if (response.getCode() == 200) {
                    mainActivity.popBackFragment(0);
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_UPDATE_SERVICE));
                } else {
                    UiUtil.showToast(response.getMsg());
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                UiUtil.showToast(errorMsg);
            }
        });
    }
    @Bind(R.id.tv_correct)
    TextView tv_correct;
    @Bind(R.id.tv_incorrect)
    TextView tv_incorrect;

    public void queryCustomerInfo(String name,String phone){
        if (TextUtils.isEmpty(name)||TextUtils.isEmpty(phone)) return;
        Map<String,String> params = new HashMap<>();
        params.put("name",name);
        params.put("phone",phone);

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.CHECK_CUSTOMER ,this, params,new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {

                if (response.getCode()==200){
                    tv_correct.setVisibility(View.VISIBLE);
                    tv_incorrect.setVisibility(View.GONE);
                    Log.i("Sven"," true ");
                }else{
                    Log.i("Sven"," false ");
                    tv_correct.setVisibility(View.GONE);
                    tv_incorrect.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                UiUtil.showToast(errorMsg);
            }
        });
    }


    public  class MyTextWatch implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
           mHandler.removeMessages(100);
           mHandler.sendEmptyMessageDelayed(100,1500);
        }
    }

}
