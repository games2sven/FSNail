package com.jlkf.fsnail.fragment;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.ShiftAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.CustomerBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ShiftBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.UiUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jlkf.fsnail.utils.VerifyUtil.isDate;

/**
 * Created by Administrator on 2018/5/28 0028. 轮班
 */

public class AddEditCustomerFragment extends BaseFragment {
    MainActivity mainActivity;
  int  type=0;
  CustomerBean.DataBean customerBean;
  @Bind(R.id.custom_page_name)
    TextView custom_page_name;
  @Bind(R.id.tv_user_agreement)
    TextView tv_user_agreement;
  @Bind(R.id.tv_totalExpence)
    TextView tv_totalExpence;
  @Bind(R.id.tv_lastExpence)
    TextView tv_lastExpence;
  @Bind(R.id.tv_haved_integral)
    TextView tv_haved_integral;
  @Bind(R.id.tv_integral)
    TextView tv_integral;

  @Bind(R.id.et_add_customer_address)
    EditText et_add_customer_address;
  @Bind(R.id.et_add_customer_name)
    EditText et_add_customer_name;
  @Bind(R.id.et_add_customer_phone)
    EditText et_add_customer_phone;
  @Bind(R.id.et_add_customer_email)
    EditText et_add_customer_email;
  @Bind(R.id.et_add_customer_birthday)
    EditText et_add_customer_birthday;
  @Bind(R.id.et_add_customer_registration)
    EditText et_add_customer_registration;
  @Bind(R.id.et_add_customer_disease)
    EditText et_add_customer_disease;
  @Bind(R.id.et_add_customer_drug)
    EditText et_add_customer_drug;
  @Bind(R.id.et_add_customer_allergy)
    EditText et_add_customer_allergy;
  @Bind(R.id.et_add_customer_join_service)
    EditText et_add_customer_join_service;
  @Bind(R.id.et_remark)
    EditText et_remark;

  int []choosesRes={R.mipmap.content_icon_choose2,R.mipmap.content_icon_unchoose1};

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View rootview  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_customer_add,null);
        ButterKnife.bind(this,rootview);
        mainActivity = (MainActivity) getActivity();
        Bundle bundle =  getArguments();
        if (bundle!=null){
           type =  bundle.getInt("type");
            customerBean = (CustomerBean.DataBean) bundle.getSerializable("data");
        }

        initView();

        return rootview ;
    }


    private void initView() {

        tv_user_agreement.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
         if (type==0){
             custom_page_name.setText("添加顾客");
         }else{
             custom_page_name.setText("修改顾客");
            if (customerBean!=null) {
                et_add_customer_name.setText(customerBean.getName());
                et_add_customer_phone.setText(customerBean.getPhone());
                        et_add_customer_email.setText(customerBean.getEmail());
                et_add_customer_birthday.setText(customerBean.getBirthday());
                        et_add_customer_registration.setText(customerBean.getRegistration());
                et_add_customer_address.setText(customerBean.getAddress());
            }
         }


         if (customerBean==null) return;
         if ("2".equals(customerBean.getIsDisease())){
             iv_add_customer_disease1.setImageResource(choosesRes[1]);
             iv_add_customer_disease2.setImageResource(choosesRes[0]);
             isSelectDisease=false;
         }else{
             isSelectDisease=true;
             iv_add_customer_disease1.setImageResource(choosesRes[0]);
             iv_add_customer_disease2.setImageResource(choosesRes[1]);
             if (!"1".equals(customerBean.getIsDisease())){
                 et_add_customer_disease.setText(customerBean.getIsDisease());
             }
         }


         if ("2".equals(customerBean.getTakeDrugs())){
             iv_add_customer_drug1.setImageResource(choosesRes[1]);
             iv_add_customer_drug2.setImageResource(choosesRes[0]);
             isSelectDrug=false;
         }else{
             isSelectDrug=true;
             iv_add_customer_drug1.setImageResource(choosesRes[0]);
             iv_add_customer_drug2.setImageResource(choosesRes[1]);
             if (!"1".equals(customerBean.getTakeDrugs())){
                 et_add_customer_drug.setText(customerBean.getTakeDrugs());
             }
         }


         if ("2".equals(customerBean.getIsAllergy())){
              allergy=false;
             iv_add_customer_allergy1.setImageResource(choosesRes[1]);
             iv_add_customer_allergy2.setImageResource(choosesRes[0]);
         }else{
             iv_add_customer_allergy1.setImageResource(choosesRes[0]);
             iv_add_customer_allergy2.setImageResource(choosesRes[1]);
             allergy=true;
             if (!"1".equals(customerBean.getIsAllergy())){
                 et_add_customer_allergy.setText(customerBean.getIsAllergy());
             }
         }



         if ("2".equals(customerBean.getIsReceiveService())){
             iv_add_customer_join_service1.setImageResource(choosesRes[1]);
             iv_add_customer_join_service2.setImageResource(choosesRes[0]);
             joinService=false;
         }else{
             iv_add_customer_join_service1.setImageResource(choosesRes[0]);
             iv_add_customer_join_service2.setImageResource(choosesRes[1]);
             joinService=true;
             if (!"1".equals(customerBean.getIsReceiveService())){
                 et_add_customer_join_service.setText(customerBean.getIsReceiveService());
             }
         }



         chooseLoveNail =customerBean.getIsLike()==1;
            iv_add_customer_love_nail1.setImageResource(customerBean.getIsLike()==1?choosesRes[0]:choosesRes[1]);
            iv_add_customer_love_nail2.setImageResource(customerBean.getIsLike()==2?choosesRes[0]:choosesRes[1]);

            chooseServiceLong=customerBean.getHowService();
        iv_add_customer_service_long1.setImageResource(customerBean.getHowService()==1?choosesRes[0]:choosesRes[1]);
        iv_add_customer_service_long2.setImageResource(customerBean.getHowService()==2?choosesRes[0]:choosesRes[1]);
        iv_add_customer_service_long3.setImageResource(customerBean.getHowService()==3?choosesRes[0]:choosesRes[1]);
        iv_add_customer_service_long4.setImageResource(customerBean.getHowService()==4?choosesRes[0]:choosesRes[1]);



        chooseNailType = Integer.parseInt(customerBean.getNailType());
        iv_add_customer_nail_type1.setImageResource("1".equals(customerBean.getNailType())?choosesRes[0]:choosesRes[1]);
        iv_add_customer_nail_type2.setImageResource("2".equals(customerBean.getNailType())?choosesRes[0]:choosesRes[1]);
        iv_add_customer_nail_type3.setImageResource("3".equals(customerBean.getNailType())?choosesRes[0]:choosesRes[1]);
        iv_add_customer_nail_type4.setImageResource("4".equals(customerBean.getNailType())?choosesRes[0]:choosesRes[1]);

        et_remark.setText(customerBean.getRemake());
         //tv_haved_integral.
         tv_integral.setText(customerBean.getIntegral()+"€");
         tv_lastExpence.setText(customerBean.getLastExpence()+"€");
         tv_totalExpence.setText(customerBean.getTotalExpence()+"€");
        setParamsValue();

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_add_customer_ensure,R.id.tv_add_customer_cancel})
   public void  finishFragment(View view){
        switch (view.getId()){
            case R.id.tv_add_customer_ensure:
                addUpdateCustomer();
                break;
            case     R.id.tv_add_customer_cancel:
                mainActivity.popBackFragment(1);
                break;

        }


   }


   @OnClick(R.id.tv_user_agreement)
   public   void  clickUserAgreement(){
       mainActivity.gotoUserAgreement();

   }

    String  name; // 姓名
    String  address ;// 地址
    String  email ;// 邮箱
    String phone;//true 手机
    String isDisease="2";//true  是否患疾病（1.是（存储文本） 2.否）
    String  takeDrugs="2"; // 药物服用（1.是(存储文本） 2.否）
    String isAllergy="2";// 是否过敏（1.是（存储文本） 2.否）
    String     isReceiveService="1";//是否接受服务（1.是（如为是填开始服务时间） 2.否）
    int         howService=1;//  多久服务1次（1.不经常 2.一个星期一次 3.一个月一次 4.从来没有）
    int     isLike=1; //是否喜欢美甲（1.是 2.否）
    String   nailType="1";//  美甲类型（1.光疗 2.Q甲 3.普通 4.其他（输入文本）
    String      remark;// false 备注
    String        birthday;//  false    生日
    String      registration;//登记日期

    private void setParamsValue() {
        name=customerBean.getName();
        address=customerBean.getAddress();
        email=customerBean.getEmail();
        phone=customerBean.getPhone();
        isDisease=customerBean.getIsDisease();
        takeDrugs=customerBean.getTakeDrugs();
        isAllergy=customerBean.getIsAllergy();
        isReceiveService=customerBean.getIsReceiveService();
        howService=customerBean.getHowService();
        takeDrugs=customerBean.getTakeDrugs();
        isLike=customerBean.getIsLike();
        nailType=customerBean.getNailType();
        remark=customerBean.getRemake();
        birthday=customerBean.getBirthday();
        registration=customerBean.getRegistration();
    }


   private void  addUpdateCustomer(){
       Map<String,String> params =new HashMap<>();
       name=et_add_customer_name.getText().toString().trim();
       email=et_add_customer_email.getText().toString().trim();
       phone=et_add_customer_phone.getText().toString().trim();
       address=et_add_customer_address.getText().toString().trim();
       birthday=et_add_customer_birthday.getText().toString().trim().replace("-","/");
       registration=et_add_customer_registration.getText().toString().trim().replace("-","/");
       remark=et_remark.getText().toString().trim();
       if (type==0) {
           if (TextUtils.isEmpty(name)) {
               UiUtil.showToast(R.string.please_input_name);
               return;
           }
           if (TextUtils.isEmpty(email)) {
               UiUtil.showToast(R.string.please_input_email);
               return;
           }
           if (TextUtils.isEmpty(phone)) {
               UiUtil.showToast(R.string.please_input_phome);
               return;
           }
           if (TextUtils.isEmpty(birthday)) {
               UiUtil.showToast(R.string.please_input_birthday);
               return;
           }
           if (TextUtils.isEmpty(registration)) {
               UiUtil.showToast(R.string.please_input_registration);
               return;
           }
       }

       if (!isDate(birthday)){
           UiUtil.showToast(R.string.please_input_ensure_birthday_format);
           return;
       }
       if (!isDate(registration)){
           UiUtil.showToast(R.string.please_input_ensure_registration_format);
           return;
       }

          String  disease =et_add_customer_disease.getText().toString().trim();
           isDisease=isSelectDisease?TextUtils.isEmpty(disease)?"1":disease:"2";

          String  drug =et_add_customer_drug.getText().toString().trim();
           takeDrugs=isSelectDrug?TextUtils.isEmpty(drug)?"1":drug:"2";

          String  allergy =et_add_customer_allergy.getText().toString().trim();
           isAllergy=this.allergy?TextUtils.isEmpty(allergy)?"1":allergy:"2";

          String  join_service =et_add_customer_join_service.getText().toString().trim().replace("-","/");
         isReceiveService=this.joinService?(TextUtils.isEmpty(join_service)?"1":join_service):"2";

         if (!"1".equals(isReceiveService)&&!"2".equals(isReceiveService)){
             if (!isDate(isReceiveService)){
                 UiUtil.showToast(R.string.please_input_how_long_format);
                 return;
             }
         }

         howService=chooseServiceLong;

         isLike =chooseLoveNail?1:2;
         nailType=chooseNailType+"";

       addParams(params,"name",name);
       addParams(params,"address",address);
       addParams(params,"email",email);
       addParams(params,"phone",phone);
       addParams(params,"isDisease",isDisease);
       addParams(params,"takeDrugs",takeDrugs);
       addParams(params,"isAllergy",isAllergy);
       addParams(params,"isReceiveService",isReceiveService);
       addParams(params,"howService",howService+"");
       addParams(params,"isLike",isLike+"");
       addParams(params,"nailType",nailType);
       addParams(params,"remark",remark);
       addParams(params,"birthday",birthday);
       addParams(params,"registration",registration);
       if (type==1){
           addParams(params,"id",customerBean.getId()+"");
       }


       Log.e("params",OKHttpUtils.getMapParamStr(params));

       OKHttpUtils.getIntance().oKHttpPost(type==0?UrlConstants.ADD_NEW_USER:UrlConstants.UPDATE_USER, this, params, new MyHttpCallback<BaseHttpBean>() {
           @Override
           public void onSuccess(BaseHttpBean response) {

               if (response.getCode()==200){
                   EventBus.getDefault().post(new EventCenter(Constants.CODE_SEARCH_CUSTOMERLIST));
                   mainActivity.popBackFragment(1);

               }else{
                   UiUtil.showToast(response.getMsg());
               }

           }

           @Override
           public void onFailure(String errorMsg) {
               UiUtil.showToast(errorMsg);
           }
       });


   }




   @Bind(R.id.iv_add_customer_disease1)
   ImageView iv_add_customer_disease1;
   @Bind(R.id.iv_add_customer_disease2)
   ImageView      iv_add_customer_disease2;

   boolean   isSelectDisease=false;
   @OnClick({R.id.iv_add_customer_disease1,R.id.iv_add_customer_disease2})
   public  void  chooseDisease(View view){
       switch (view.getId()){
           case R.id.iv_add_customer_disease1:
               isSelectDisease=true;
               iv_add_customer_disease1.setImageResource(choosesRes[0]);
               iv_add_customer_disease2.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_disease2:
               isSelectDisease=false;
               iv_add_customer_disease1.setImageResource(choosesRes[1]);
               iv_add_customer_disease2.setImageResource(choosesRes[0]);
               break;


       }


   }


   @Bind(R.id.iv_add_customer_drug1)
   ImageView iv_add_customer_drug1;
   @Bind(R.id.iv_add_customer_drug2)
   ImageView      iv_add_customer_drug2;
   boolean  isSelectDrug=false;
   @OnClick({R.id.iv_add_customer_drug1,R.id.iv_add_customer_drug2})
   public  void  chooseDrug(View view){
       switch (view.getId()){
           case R.id.iv_add_customer_drug1:
               isSelectDrug=true;
               iv_add_customer_drug1.setImageResource(choosesRes[0]);
               iv_add_customer_drug2.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_drug2:
               isSelectDrug=false;
               iv_add_customer_drug1.setImageResource(choosesRes[1]);
               iv_add_customer_drug2.setImageResource(choosesRes[0]);
               break;
       }
   }


   @Bind(R.id.iv_add_customer_allergy1)
   ImageView iv_add_customer_allergy1;
   @Bind(R.id.iv_add_customer_allergy2)
   ImageView      iv_add_customer_allergy2;
   boolean  allergy=false;
   @OnClick({R.id.iv_add_customer_allergy1,R.id.iv_add_customer_allergy2})
   public  void  chooseAllergy(View view){
       switch (view.getId()){
           case R.id.iv_add_customer_allergy1:
               allergy=true;
               iv_add_customer_allergy1.setImageResource(choosesRes[0]);
               iv_add_customer_allergy2.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_allergy2:
               allergy=false;
               iv_add_customer_allergy1.setImageResource(choosesRes[1]);
               iv_add_customer_allergy2.setImageResource(choosesRes[0]);
               break;
       }
   }



   @Bind(R.id.iv_add_customer_join_service1)
   ImageView iv_add_customer_join_service1;
   @Bind(R.id.iv_add_customer_join_service2)
   ImageView      iv_add_customer_join_service2;
   boolean  joinService=true;
   @OnClick({R.id.iv_add_customer_join_service1,R.id.iv_add_customer_join_service2})
   public  void  chooseJoinService(View view){
       switch (view.getId()){
           case R.id.iv_add_customer_join_service1:
               joinService=true;
               iv_add_customer_join_service1.setImageResource(choosesRes[0]);
               iv_add_customer_join_service2.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_join_service2:
               joinService=false;
               iv_add_customer_join_service1.setImageResource(choosesRes[1]);
               iv_add_customer_join_service2.setImageResource(choosesRes[0]);
               break;
       }
   }


   @Bind(R.id.iv_add_customer_love_nail1)
   ImageView iv_add_customer_love_nail1;
   @Bind(R.id.iv_add_customer_love_nail2)
   ImageView      iv_add_customer_love_nail2;
   boolean  chooseLoveNail=true;
   @OnClick({R.id.iv_add_customer_love_nail1,R.id.iv_add_customer_love_nail2})
   public  void  chooseLoveNail(View view){
       switch (view.getId()){
           case R.id.iv_add_customer_love_nail1:
               chooseLoveNail=true;
               iv_add_customer_love_nail1.setImageResource(choosesRes[0]);
               iv_add_customer_love_nail2.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_love_nail2:
               chooseLoveNail=false;
               iv_add_customer_love_nail1.setImageResource(choosesRes[1]);
               iv_add_customer_love_nail2.setImageResource(choosesRes[0]);
               break;
       }
   }



   @Bind(R.id.iv_add_customer_service_long1)
   ImageView iv_add_customer_service_long1;
   @Bind(R.id.iv_add_customer_service_long2)
   ImageView      iv_add_customer_service_long2;
   @Bind(R.id.iv_add_customer_service_long3)
   ImageView      iv_add_customer_service_long3;
   @Bind(R.id.iv_add_customer_service_long4)
   ImageView      iv_add_customer_service_long4;
   int   chooseServiceLong=1;
   @OnClick({R.id.iv_add_customer_service_long1,R.id.iv_add_customer_service_long2,R.id.iv_add_customer_service_long3,R.id.iv_add_customer_service_long4})
   public  void  chooseServiceLong(View view){
       switch (view.getId()){
           case R.id.iv_add_customer_service_long1:
               chooseServiceLong=1;
               iv_add_customer_service_long1.setImageResource(choosesRes[0]);
               iv_add_customer_service_long2.setImageResource(choosesRes[1]);
               iv_add_customer_service_long3.setImageResource(choosesRes[1]);
               iv_add_customer_service_long4.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_service_long2:
               chooseServiceLong=2;
               iv_add_customer_service_long1.setImageResource(choosesRes[1]);
               iv_add_customer_service_long2.setImageResource(choosesRes[0]);
               iv_add_customer_service_long3.setImageResource(choosesRes[1]);
               iv_add_customer_service_long4.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_service_long3:
               chooseServiceLong=3;
               iv_add_customer_service_long1.setImageResource(choosesRes[1]);
               iv_add_customer_service_long2.setImageResource(choosesRes[1]);
               iv_add_customer_service_long3.setImageResource(choosesRes[0]);
               iv_add_customer_service_long4.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_service_long4:
               chooseServiceLong=4;
               iv_add_customer_service_long1.setImageResource(choosesRes[1]);
               iv_add_customer_service_long2.setImageResource(choosesRes[1]);
               iv_add_customer_service_long3.setImageResource(choosesRes[1]);
               iv_add_customer_service_long4.setImageResource(choosesRes[0]);
               break;
       }
   }


   @Bind(R.id.iv_add_customer_nail_type1)
   ImageView iv_add_customer_nail_type1;
   @Bind(R.id.iv_add_customer_nail_type2)
   ImageView      iv_add_customer_nail_type2;
   @Bind(R.id.iv_add_customer_nail_type3)
   ImageView      iv_add_customer_nail_type3;
   @Bind(R.id.iv_add_customer_nail_type4)
   ImageView      iv_add_customer_nail_type4;
   int   chooseNailType=1;
   @OnClick({R.id.iv_add_customer_nail_type1,R.id.iv_add_customer_nail_type2,R.id.iv_add_customer_nail_type3,R.id.iv_add_customer_nail_type4})
   public  void  chooseNailType(View view){
       switch (view.getId()){
           case R.id.iv_add_customer_nail_type1:
               chooseNailType=1;
               iv_add_customer_nail_type1.setImageResource(choosesRes[0]);
               iv_add_customer_nail_type2.setImageResource(choosesRes[1]);
               iv_add_customer_nail_type3.setImageResource(choosesRes[1]);
               iv_add_customer_nail_type4.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_nail_type2:
               chooseNailType=2;
               iv_add_customer_nail_type1.setImageResource(choosesRes[1]);
               iv_add_customer_nail_type2.setImageResource(choosesRes[0]);
               iv_add_customer_nail_type3.setImageResource(choosesRes[1]);
               iv_add_customer_nail_type4.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_nail_type3:
               chooseNailType=3;
               iv_add_customer_nail_type1.setImageResource(choosesRes[1]);
               iv_add_customer_nail_type2.setImageResource(choosesRes[1]);
               iv_add_customer_nail_type3.setImageResource(choosesRes[0]);
               iv_add_customer_nail_type4.setImageResource(choosesRes[1]);
               break;
           case R.id.iv_add_customer_nail_type4:
               chooseNailType=4;
               iv_add_customer_nail_type1.setImageResource(choosesRes[1]);
               iv_add_customer_nail_type2.setImageResource(choosesRes[1]);
               iv_add_customer_nail_type3.setImageResource(choosesRes[1]);
               iv_add_customer_nail_type4.setImageResource(choosesRes[0]);
               break;
       }
   }


}
