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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.BookedServiceAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.BookedServiceBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.CustomNamePop;
import com.jlkf.fsnail.dialog.DialogChooseDate;
import com.jlkf.fsnail.dialog.MoreFunctionsPop;
import com.jlkf.fsnail.dialog.SearchServiceDialog;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.TimeUtil;
import com.jlkf.fsnail.utils.UiUtil;
import com.jlkf.fsnail.widget.myspinner.singletextviewspinner.TextViewSpinner;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BookedServiceFragment extends BaseFragment {

    List<BookedServiceBean.DataBean> mDatas = new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recyclerView;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;
    MainActivity mainActivity;

    TextViewSpinner brandSpinner;
    TextViewSpinner serviceSpinner;
    TextViewSpinner statusSpinner;
    TextViewSpinner typeSpinner;
    private int totalPage;
    View mView;
    TextView tv_start_date, tv_end_date;
    EditText tv_customer_name, tv_customer_phone, tv_staff_name;
    ServiceMenuBean menuBean;

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_booked_serive, null);
        mainActivity = (MainActivity) getActivity();
        menuBean = MyApplication.getInstance().getMenuBean();
        initStatus();
        ButterKnife.bind(this, mView);
        initView();
        initRecyclerView();
        initNet();
        return mView;

    }

    List<String> statusStr = new ArrayList<>();

    private void initStatus() {
        statusStr.add(mainActivity.getString(R.string.status_no_tart));
        statusStr.add(mainActivity.getString(R.string.status_progress));
        statusStr.add(mainActivity.getString(R.string.status_finish));
        statusStr.add(mainActivity.getString(R.string.status_booked));
        statusStr.add(mainActivity.getString(R.string.status_cancel));
    }

    String startTime;//DATE类型例10:00 开始时间
    String endTime;//例 23:00 结束时间
    String startDate;//开始日期
    String endDate;//结束日期
    String service;//服务项ID
    String status;//状态
    String brand;//品牌ID
    int pageSize = 3;//每页条数
    int pageNo = 1;//
    String uName;//员工昵称
    String customerName;//客户名
    String customerPhone;//客户手机
    String type;//类别ID

    private void clearSearchCondition() {
        startTime = null;
        endTime = null;
        uName = null;
        customerName = null;
        customerPhone = null;
        type = null;
        service = null;
        brand = null;
        status = null;
        startDate = null;
        endDate = null;
    }

    private void initNet() {
        getBookedServiceList();
    }


    void getBookedServiceList() {
        Map<String, String> params = new HashMap<>();
        addParams(params, "startTime", startTime);
        addParams(params, "endTime", endTime);
        addParams(params, "startDate", startDate);
        addParams(params, "endDate", endDate);
        addParams(params, "service", service);
        addParams(params, "status", status);
        addParams(params, "brand", brand);
        addParams(params, "uName", uName);
        addParams(params, "customerName", customerName);
        addParams(params, "customerPhone", customerPhone);
        addParams(params, "type", type);
        addParams(params, "pageSize", String.valueOf(pageSize));
        addParams(params, "pageNo", String.valueOf(pageNo));

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.BOOKED_SERVICE_LIST, this, params, new MyHttpCallback<BookedServiceBean>() {
            @Override
            public void onSuccess(BookedServiceBean response) {
                if (response.getCode() == 200) {
                    mDatas.clear();
                    mDatas.addAll(response.getData());
                    totalPage = response.getTotalPage();
                    mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }


    private void initView() {

        brandSpinner = mView.findViewById(R.id.spinner_brand);
        serviceSpinner = mView.findViewById(R.id.spinner_service);
        statusSpinner = mView.findViewById(R.id.spinner_status);
        typeSpinner = mView.findViewById(R.id.spinner_type);
        tv_start_date = mView.findViewById(R.id.tv_start_date);
        tv_end_date = mView.findViewById(R.id.tv_end_date);
        tv_customer_name = mView.findViewById(R.id.tv_customer_name);
        tv_customer_phone = mView.findViewById(R.id.tv_customer_phone);
        tv_staff_name = mView.findViewById(R.id.tv_staff_name);
        mView.findViewById(R.id.ll_start_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChooseDate dialogChooseDate = new DialogChooseDate(mainActivity, mainActivity.getRightWidth(), new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        startTime = date.replaceAll("-", "/");
                        tv_start_date.setText(date);
                    }
                });
            }
        });
        mView.findViewById(R.id.ll_end_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChooseDate dialogChooseDate = new DialogChooseDate(mainActivity, mainActivity.getRightWidth(), new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        endTime = date.replaceAll("-", "/");
                        tv_end_date.setText(date);
                    }
                });
            }
        });


        if (menuBean != null) {
            brandSpinner.setItems(menuBean.getData().getBrand());
            brandSpinner.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

                @Override
                public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                    brand = menuBean.getData().getBrand().get(position).getId() + "";
                }
            });


            if (menuBean.getData().getService() != null) {
                serviceSpinner.setItems(menuBean.getData().getService());
                serviceSpinner.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

                    @Override
                    public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                        service = menuBean.getData().getService().get(position).getId() + "";
                    }
                });
            }

            typeSpinner.setItems(menuBean.getData().getType());
            typeSpinner.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

                @Override
                public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                    type = menuBean.getData().getType().get(position).getId() + "";
                }
            });
        }

        statusSpinner.setItems(statusStr);
        statusSpinner.setOnItemSelectedListener(new TextViewSpinner.OnItemSelectedListener<String>() {

            @Override
            public void onItemSelected(TextViewSpinner view, int position, long id, String item) {
                status = position + 1 + "";
            }
        });

        mView.findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(startTime) && !TextUtils.isEmpty(endTime)) {

                    startTime = TimeUtil.parseDate(0);
                }
                if (TextUtils.isEmpty(endTime) && !TextUtils.isEmpty(startTime)) {
                    endTime = TimeUtil.parseDate(System.currentTimeMillis());
                }


                if (!TextUtils.isEmpty(endTime) && !TextUtils.isEmpty(startTime))
                    if (endTime.compareTo(startTime) < 1) {
                        UiUtil.showToast(R.string.please_select_error_time);
                        return;
                    }


                customerPhone = tv_customer_phone.getText().toString();
                uName = tv_staff_name.getText().toString();
                customerName = tv_customer_name.getText().toString();
                getBookedServiceList();

            }
        });
    }

    List<String> list = new ArrayList<>();

    @OnClick(R.id.tv_return)
    public void back() {
        mainActivity.popBackFragment(0);
    }


    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    BookedServiceAdapter mAdapter;

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mAdapter = new BookedServiceAdapter(mainActivity, mDatas);


        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLongClickListener(new BookedServiceAdapter.OnLongClickListener() {
            @Override
            public void onCustomNameLongClick(View view, String customName) {
                CustomNamePop pop = new CustomNamePop(getActivity(), view);
                pop.showPop(customName);
            }

            @Override
            public void editClick(View view, BookedServiceBean.DataBean serviceBean) {
//            mainActivity.gotoServiceEdit(serviceBean);
            }

            @Override
            public void addServiceClick(BookedServiceBean.DataBean serviceBean) {
                addBooked2Service(serviceBean.getId());
            }

        });

    }

    boolean isShowMore;


    private void showSearchDialog() {
        SearchServiceDialog dialog = new SearchServiceDialog(getActivity(), ((MainActivity) getActivity()).getRightWidth(), MyApplication.getInstance().getMenuBean());
        dialog.showDiaglog();
    }


    //跳转到排班
    public void gotoShift() {
        mainActivity.gotoShift();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


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

                    mainActivity.gotoSearchSearchResultFragment();

                }
            });
        }
    }


    private void addBooked2Service(int id) {
        Map<String, String> pamaras = new HashMap<>();
        addParams(pamaras, "id", String.valueOf(id));
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.ADD_BOOK_TO_SERVICE, this, pamaras, new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {
                if (response.getCode() == 200) {
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_UPDATE_SERVICE));
                    EventBus.getDefault().post(new EventCenter(Constants.ADD_BOOK_TO_SERVICE));
                    UiUtil.showToast(R.string.tv_add_success);
                    mainActivity.popBackFragment(0);
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
}
