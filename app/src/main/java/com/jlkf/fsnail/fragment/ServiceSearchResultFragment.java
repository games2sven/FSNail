package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.ServiceAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ServiceBean;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.CustomNamePop;
import com.jlkf.fsnail.dialog.MoreFunctionsPop;
import com.jlkf.fsnail.dialog.SearchServiceDialog;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ServiceSearchResultFragment extends BaseFragment {

    List<ServiceBean.DataBean> mDatas = new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recyclerView;
    @Bind(R.id.tv_service_customer_name)
    TextView tv_service_customer_name;
    @Bind(R.id.tv_service_workers_nickname)
    TextView tv_service_workers_nickname;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;
    @Bind(R.id.shop_cart)
    LinearLayout shop_cart;
    MainActivity mainActivity;
    Gson gson =new Gson();
    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_serive_search_result, null);
        mainActivity = (MainActivity) getActivity();
        ButterKnife.bind(this, view);
        initView();
        initRecyclerView();
        getServiceList(null,null,null,1,6);
        return view;

    }

    private void initView() {
//        tv_service_customer_name.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
//        tv_service_workers_nickname.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG);
//        tv_service_customer_name.setTextColor(Color.parseColor("#94c42e"));
//        tv_service_workers_nickname.setTextColor(Color.parseColor("#94c42e"));
    }

//    List<String> list = new ArrayList<>();

//    @OnClick({R.id.tv_service_workers_nickname, R.id.tv_service_customer_name})
//    public void showNameSelectDialog(View view) {
//        switch (view.getId()) {
//            case R.id.tv_service_workers_nickname:
//                addList();
//                NamesSelectDialog dialog1 = new NamesSelectDialog(1, getActivity(), list);
//                dialog1.showDiaglog();
//                break;
//            case R.id.tv_service_customer_name:
//                addList();
//                NamesSelectDialog dialog2 = new NamesSelectDialog(2, getActivity(), list);
//                dialog2.showDiaglog();
//                break;
//
//
//        }
//
//
//    }

//    private void addList() {
//        list.clear();
//        list.add("Kiki");
//        list.add("Amy");
//        list.add("Susa");
//        list.add("Sara");
//        list.add("Kiki");
//        list.add("Amy");
//        list.add("Susa");
//        list.add("Sara");
//        list.add("Kiki");
//        list.add("Amy");
//        list.add("Susa");
//        list.add("Sara");
//    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    ServiceAdapter mAdapter;

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        mAdapter = new ServiceAdapter(getActivity(),mDatas,1);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLongClickListener(new ServiceAdapter.OnLongClickListener() {
            @Override
            public void onCustomNameLongClick(View view, String customName) {
                CustomNamePop pop = new CustomNamePop(getActivity(), view);
                pop.showPop(customName);
            }

            @Override
            public void editClick(View view, ServiceBean.DataBean serviceBean) {
                if (serviceBean.getStatus()==1){//未开始


                }else if (serviceBean.getStatus()==2){//进行中


                }
            }

            @Override
            public void statusClick(ServiceBean.DataBean serviceBean) {
                mainActivity.gotoServiceEdit(serviceBean);
            }

        });

    }

    boolean isShowMore;

    @OnClick(R.id.iv_service_more)
    public void showMoreFunction() {
        isShowMore = !isShowMore;
        iv_service_more.setImageResource(isShowMore ? R.mipmap.btn_more_red2 : R.mipmap.btn_more_red1);
        if (isShowMore) {
            MoreFunctionsPop pop = new MoreFunctionsPop(1, getActivity(), iv_service_more);
            pop.showPop();
            pop.setOnDismissListener(new MoreFunctionsPop.OnDismissListener() {
                public void onDismiss() {
                    isShowMore = false;
                    iv_service_more.setImageResource(R.mipmap.btn_more_red1);
                }
            });
            pop.setOnMoreClickListener(new MoreFunctionsPop.OnMoreClickListener() {
                @Override
                public void lunbanClick() {//轮班
//                    Toast.makeText(getActivity(), "轮班", Toast.LENGTH_SHORT).show();
                    gotoShift();
                }

                @Override
                public void searchCurren() {//搜索当前
//                    Toast.makeText(getActivity(), "搜索当前", Toast.LENGTH_SHORT).show();
                    showSearchDialog();
                }

                @Override
                public void searchAll() {//搜索所有
//                    Toast.makeText(getActivity(), "搜索所有", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void showSearchDialog() {
        SearchServiceDialog dialog = new SearchServiceDialog(getActivity(), ((MainActivity) getActivity()).getRightWidth(),  MyApplication.getInstance().getMenuBean());
        dialog.showDiaglog();
    }


    @OnClick(R.id.shop_cart)
    public void gotoShoppingCart() {

        mainActivity.gotoShoppingCart();
    }

    @OnClick(R.id.add_new_service)
    public  void  addNewService(){
        mainActivity.gotoServiceAdd();
    }

    //跳转到排班
    public  void  gotoShift(){
        mainActivity.gotoShift();

    }

    @OnClick(R.id.ll_add_booked_service)
    public  void gotoBookedService(){
        mainActivity.gotoBookedService();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    int  totalPage;

    void   getServiceList(String  username ,String  customerName,String  status ,int pageNo , int pageSize){
        Map<String,String> params = new HashMap<>();
        if (!TextUtils.isEmpty(username)){
            params.put("username",username);
        }
        if (!TextUtils.isEmpty(customerName)){
            params.put("customerName",customerName);
        }
        if (!TextUtils.isEmpty(status)){
            params.put("status",status);
        }
        params.put("pageNo",pageNo+"");
        params.put("pageSize",pageSize+"");

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.SERVICE_LIST, this, params, new MyHttpCallback<ServiceBean>() {
            @Override
            public void onSuccess(ServiceBean response) {
                if (response.getCode()==200){
                    mDatas.clear();
                    mDatas.addAll(response.getData());
                    totalPage=response.getTotalPage();
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }
}
