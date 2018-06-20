package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.BookBean;
import com.jlkf.fsnail.bean.Card2Bean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.EmployeeSelectDialog;
import com.jlkf.fsnail.dialog.InformationNotCopleteDialog;
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

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.jlkf.fsnail.utils.VerifyUtil.isDate;


public class EditBookFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.tv_title)
    TextView tv_title;
    @Bind(R.id.ll_parent)
    LinearLayout ll_parent;
    @Bind(R.id.tv_appoint_back)
    TextView tv_appoint_back;
    @Bind(R.id.ll_content)
    LinearLayout ll_content;
    @Bind(R.id.tv_add_type)
    TextView tv_add_type;

    @Bind(R.id.tv_appoint_confirm)
    TextView tv_appoint_confirm;
    @Bind(R.id.tv_appoint_cancel)
    TextView tv_appoint_cancel;
    @Bind(R.id.tv_appoint_send)
    TextView tv_appoint_send;
    @Bind(R.id.customer_spinner)
    TextViewSpinner customer_spinner;
    @Bind(R.id.et_appoint_phone)
    TextView et_appoint_phone;
    @Bind(R.id.tv_incorrect)
    TextView tv_incorrect;
    @Bind(R.id.tv_correct)
    TextView tv_correct;


    private BookBean.DataBean mBookBean;

    private int type = 0;

    MainActivity mainActivity;

    String [] tests;
    List<String> lists = new ArrayList<String>();
    List<View> viewList = new ArrayList<View>();

    private int clickCount = 0;

    List <ServiceMenuBean.DataBean.CustomerBean>  customerLists;
    List <ServiceMenuBean.DataBean.BrandBean>  brandLists;
    List <ServiceMenuBean.DataBean.ServiceBean>  serviceLists;
    List <ServiceMenuBean.DataBean.TypeBean>  typeLists;

    ServiceMenuBean serviceBean;
    boolean isCustomerComplete = false;


    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        mainActivity= (MainActivity) getActivity();
        View view  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_edit_book,null);
        ButterKnife.bind(this,view);

        Bundle bundle = getArguments();
        if(bundle != null){//修改预约
            type  = 1;
            mBookBean =(BookBean.DataBean) bundle.getSerializable("data");
        }else{//添加预约
            type = 0;
        }

        tests = getResources().getStringArray(R.array.sports);
        lists = Arrays.asList(tests);
        serviceBean = MyApplication.getInstance().getMenuBean();
        customerLists = MyApplication.getInstance().getMenuBean().getData().getCustomer();
        brandLists = MyApplication.getInstance().getMenuBean().getData().getBrand();
        serviceLists = MyApplication.getInstance().getMenuBean().getData().getService();
        typeLists = MyApplication.getInstance().getMenuBean().getData().getType();

        initView();
        return view;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        switch (eventCenter.getEventCode()){
            case Constants.CODE_TO_REGISTER:
                toRegister();
                break;
        }
    }

    public void initView(){
        Log.i("Sven","type = "+type);
        if(type == 0){
            tv_title.setText(getString(R.string.text_add_book));
            tv_appoint_send.setVisibility(View.GONE);
            tv_appoint_cancel.setVisibility(View.GONE);

            customer_spinner.setItems(customerLists);
            customer_spinner.setSelectedIndex(0);
            customer = customerLists.get(0).getName();
            phone = customerLists.get(0).getPhone();
            et_appoint_phone.setText(phone);
            queryCustomerInfo(customer,phone);


        }else{
            tv_title.setText(getString(R.string.text_edit_book));
            if(mBookBean != null){
                customer_spinner.setItems(customerLists);
                customer = mBookBean.getCustomerName();
                for(ServiceMenuBean.DataBean.CustomerBean customerBean : customerLists){
                    if(customerBean.getId() == (mBookBean.getCustomerId())){
                        customer_spinner.setSelectedIndex(customerLists.indexOf(customerBean));
                        break;
                    }
                }
                phone = mBookBean.getCustomerPhone();
                et_appoint_phone.setText(phone);
                queryCustomerInfo(customer,phone);
            }

            tv_appoint_cancel.setOnClickListener(this);
        }

        customer_spinner.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

            @Override public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                customer = customerLists.get(position).getName();
                phone = customerLists.get(position).getPhone();
                et_appoint_phone.setText(phone);
                queryCustomerInfo(customer,phone);
            }
        });

        tv_appoint_back.setOnClickListener(this);
        tv_appoint_confirm.setOnClickListener(this);
        tv_add_type.setOnClickListener(this);
        tv_add_type.performClick();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_appoint_back:
                ((MainActivity)getActivity()).popBackFragment(3);
                ((MainActivity)getActivity()).addBookFragment = null;
                break;
            case R.id.tv_appoint_confirm:
                if(isCustomerComplete){
                    Log.i("Sven","toregister");
                    toRegister();
                }else{
                    Log.i("Sven","showdialog");
                    InformationNotCopleteDialog dialog1 = new InformationNotCopleteDialog(getActivity());
                    dialog1.showDialog();
                }

                break;
            case R.id.tv_add_type:
                View mView = getTypeView(clickCount);
                ll_content.addView(mView);
                viewList.add(mView);
                clickCount++;
                break;
            case R.id.tv_appoint_cancel://取消预约
                cancelBook();
                break;
        }
    }

    EditText et_book_time;
    private View getTypeView(int postion){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View typeView = inflater.inflate(R.layout.item_add_type,null);
        final TextViewSpinner sp_category = (TextViewSpinner)typeView.findViewById(R.id.sp_category);
        TextViewSpinner sp_brand = (TextViewSpinner)typeView.findViewById(R.id.sp_brand);
        final TextViewSpinner sp_service = (TextViewSpinner)typeView.findViewById(R.id.sp_service);
        TextView add_service = (TextView) typeView.findViewById(R.id.add_service);
        TextView text_select = (TextView) typeView.findViewById(R.id.text_select);
        final TextView tv_price = (TextView) typeView.findViewById(R.id.tv_price);
        et_book_time = (EditText) typeView.findViewById(R.id.et_book_time);
        TextView tv_employee_time = (TextView)typeView.findViewById(R.id.tv_employee_time);

        add_service.setOnClickListener(new On_Click_Listener(postion));
        text_select.setOnClickListener(new On_Click_Listener(postion));

        if(type == 0){//添加预约
            sp_category.setItems(typeLists);
            sp_category.setSelectedIndex(0);
            typeID = String.valueOf(typeLists.get(0).getId());
            tv_price.setText(typeLists.get(0).getServiceList().get(0).getPrice()+"€");

            sp_brand.setItems(brandLists);
            sp_brand.setSelectedIndex(0);
            brandID = String.valueOf(brandLists.get(0).getId());

            sp_service.setItems(typeLists.get(0).getServiceList());
            sp_service.setSelectedIndex(0);
            service= String.valueOf(((ServiceMenuBean.DataBean.TypeBean.ServiceListBean)(sp_service.getItems().get(0))).getId());
        }else{
            sp_category.setItems(typeLists);
            typeID = String.valueOf(mBookBean.getTypeId());
            for(ServiceMenuBean.DataBean.TypeBean typeBean : typeLists){
                if(typeBean.getId() == mBookBean.getTypeId()){
                    sp_category.setSelectedIndex(typeLists.indexOf(typeBean));
                    sp_service.setItems(typeBean.getServiceList());
                    break;
                }
            }

            sp_brand.setItems(brandLists);
            brandID = String.valueOf(mBookBean.getBrandId());
            for(ServiceMenuBean.DataBean.BrandBean brandBean :brandLists){
                if(brandBean.getId() == mBookBean.getBrandId()){
                    sp_brand.setSelectedIndex(brandLists.indexOf(brandBean));
                    break;
                }
            }

            List<ServiceMenuBean.DataBean.TypeBean.ServiceListBean> serviceListBean = sp_service.getItems();
            for(ServiceMenuBean.DataBean.TypeBean.ServiceListBean serviceBean1 :serviceListBean){
                if(serviceBean1.getId() == mBookBean.getServiceId()){
                    sp_service.setSelectedIndex(serviceListBean.indexOf(serviceBean1));
                    break;
                }
            }
            service= String.valueOf(mBookBean.getServiceId());
            tv_price.setText(typeLists.get(sp_category.getSelectedIndex()).getServiceList().get(sp_service.getSelectedIndex()).getPrice()+"€");
            et_book_time.setText(mBookBean.getOptime());
            tv_employee_time.setVisibility(View.VISIBLE);
            String [] strs = mBookBean.getCreateTime().split(" ");
            strs = strs[1].split(":");
            time = strs[0]+":"+strs[1];

            tv_employee_time.setText(mBookBean.getUName()+" "+time);
            text_select.setVisibility(View.GONE);
            tv_employee_time.setOnClickListener(new On_Click_Listener(postion));
        }

        sp_category.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {
                sp_service.setItems(typeLists.get(position).getServiceList());
                sp_service.setSelectedIndex(0);
                tv_price.setText(typeLists.get(position).getServiceList().get(0).getPrice()+"€");
                typeID = String.valueOf(typeLists.get(position).getId());
            }
        });

        sp_brand.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {
                brandID = String.valueOf(brandLists.get(position).getId());
            }
        });

        sp_service.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, Object item) {
                tv_price.setText(((ServiceMenuBean.DataBean.TypeBean.ServiceListBean)(sp_service.getItems().get(position))).getPrice()+"€");
                service= String.valueOf(((ServiceMenuBean.DataBean.TypeBean.ServiceListBean)(sp_service.getItems().get(position))).getId());
            }
        });

        return typeView;
    }

    private View getServiceView(){
        LayoutInflater inflater = LayoutInflater.from(getActivity());
        View view = inflater.inflate(R.layout.item_add_service,null);
        return view;
    }


    public class On_Click_Listener implements View.OnClickListener{

        private int index;

        public On_Click_Listener(int index) {
            super();
            this.index = index;
        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.add_service:
                    LinearLayout linearLayout = (LinearLayout) viewList.get(index).findViewById(R.id.ll_service);
                    linearLayout.addView(getServiceView());
                    break;
                case R.id.text_select:
                    EmployeeSelectDialog dialog = new EmployeeSelectDialog(getContext(), new EmployeeSelectDialog.OkClickListner() {
                        @Override
                        public void clickOk(ServiceMenuBean.DataBean.StaffBean bean, String hour, String minute) {
                            TextView text_select = (TextView) viewList.get(index).findViewById(R.id.text_select);
                            TextView tv_employee_time = (TextView) viewList.get(index).findViewById(R.id.tv_employee_time);
                            text_select.setVisibility(View.GONE);
                            tv_employee_time.setVisibility(View.VISIBLE);
                            tv_employee_time.setText(bean.getU_name()+" "+hour+":"+minute);
                            staffId = bean.getId()+"";
                            time = hour+":"+minute;
                        }
                    });
                    dialog.showDialog();
                    break;
                case R.id.tv_employee_time:
                    EmployeeSelectDialog dialog1 = new EmployeeSelectDialog(getContext(), new EmployeeSelectDialog.OkClickListner() {
                        @Override
                        public void clickOk(ServiceMenuBean.DataBean.StaffBean bean, String hour, String minute) {
                            TextView text_select = (TextView) viewList.get(index).findViewById(R.id.text_select);
                            TextView tv_employee_time = (TextView) viewList.get(index).findViewById(R.id.tv_employee_time);
                            text_select.setVisibility(View.GONE);
                            tv_employee_time.setVisibility(View.VISIBLE);
                            tv_employee_time.setText(bean.getU_name()+" "+hour+":"+minute);
                            staffId = bean.getId()+"";
                            time = hour+":"+minute;
                        }
                    });
                    dialog1.setEmployee(mBookBean.getId(),mBookBean.getCreateTime());
                    dialog1.showDialog();

                    break;
            }
        }
    }

    String    service;
    String    customer;
    String    phone;
    String    typeID;
    String    brandID;
    String    bookTime;
    String    staffId;
    String    time;

    public void toRegister(){
        Map<String,String> params =new HashMap<>();
        phone = et_appoint_phone.getText().toString().trim();
        bookTime = et_book_time.getText().toString().trim();
//        for(View view :viewList){
//            TextViewSpinner sp_category = (TextViewSpinner)view.findViewById(R.id.sp_category);
//            sp_category.get
//        }

        if (type==0) {
            if (TextUtils.isEmpty(customer)) {
                UiUtil.showToast(R.string.please_input_name);
                return;
            }
            if (TextUtils.isEmpty(phone)) {
                UiUtil.showToast(R.string.please_input_phome);
                return;
            }
            if (TextUtils.isEmpty(bookTime)) {
                UiUtil.showToast(R.string.tips_input_book_date);
                return;
            }
            if (TextUtils.isEmpty(time)) {
                UiUtil.showToast(R.string.tips_choose_employee);
                return;
            }
        }else{
            if(TextUtils.isEmpty(staffId)){
                staffId = mBookBean.getStaffId()+"";
            }

            if(TextUtils.isEmpty(time)){//2018-06-14 20:36:14
                String [] strs = mBookBean.getCreateTime().split(" ");
                strs = strs[1].split(":");
                time = strs[0]+":"+strs[1];
            }
        }

        if (!isDate(bookTime)){
            UiUtil.showToast(R.string.please_input_ensure_birthday_format);
            return;
        }



        addParams(params,"customerName",customer);
        addParams(params,"customerPhone",phone);
        addParams(params,"typeId",typeID);
        addParams(params,"serviceId",service);
        addParams(params,"brandId",brandID);
        addParams(params,"optime",bookTime);
        addParams(params,"staffId",staffId);
        addParams(params,"appointTime",time);

        if(type == 1){
            addParams(params,"id",mBookBean.getId()+"");
        }

        Log.e("Sven",OKHttpUtils.getMapParamStr(params));

        OKHttpUtils.getIntance().oKHttpPost(type == 0?UrlConstants.ADD_BOOK :UrlConstants.EDIT_BOOK,this, params, new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {

                if (response.getCode()==200){
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_ADD_BOOK_SUCCESS));
                    mainActivity.popBackFragment(3);
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

    public void cancelBook(){
        Map<String,String> params = new HashMap<>();
        params.put("id",mBookBean.getId()+"");
        params.put("status",5+"");

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.CANCEL_BOOK ,this, params,new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {

                if (response.getCode()==200){
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_CANCEL_BOOK));
                    mainActivity.popBackFragment(3);
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

    public void queryCustomerInfo(String name,String phone){
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
                    isCustomerComplete = true;
                }else{
                    Log.i("Sven"," false ");
                    tv_correct.setVisibility(View.GONE);
                    tv_incorrect.setVisibility(View.VISIBLE);
                    isCustomerComplete = false;
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                UiUtil.showToast(errorMsg);
            }
        });
    }


}
