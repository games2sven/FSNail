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

import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.BookedServiceAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BookedServiceBean;
import com.jlkf.fsnail.bean.EventCenter;
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


public class BookedServiceFragment extends BaseFragment {

    List<BookedServiceBean.DataBean> mDatas =new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recyclerView;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;
    MainActivity mainActivity;
    private int totalPage;

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_booked_serive, null);
        mainActivity = (MainActivity) getActivity();
        ButterKnife.bind(this, view);
        initView();
        initRecyclerView();
        initNet();
        return view;

    }

    private void initNet() {
        getBookedServiceList(null,null,null,1,3);
    }


    void  getBookedServiceList(String  username ,String  customerName,String  status ,int pageNo , int pageSize){
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

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.BOOKED_SERVICE_LIST, this, params, new MyHttpCallback<BookedServiceBean>() {
            @Override
            public void onSuccess(BookedServiceBean response) {
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


    private void initView() {
    }

    List<String> list = new ArrayList<>();

    @OnClick(R.id.tv_return)
    public void back() {
        mainActivity.popBackFragment(0);
    }

    private void addList() {
        list.clear();
        list.add("Kiki");
        list.add("Amy");
        list.add("Susa");
        list.add("Sara");
        list.add("Kiki");
        list.add("Amy");
        list.add("Susa");
        list.add("Sara");
        list.add("Kiki");
        list.add("Amy");
        list.add("Susa");
        list.add("Sara");
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
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        mAdapter = new BookedServiceAdapter(mainActivity,mDatas);


        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnLongClickListener(new BookedServiceAdapter.OnLongClickListener() {
            @Override
            public void onCustomNameLongClick(View view, String customName) {
                CustomNamePop pop = new CustomNamePop(getActivity(), view);
                pop.showPop(customName);
            }

            @Override
            public void editClick(View view, BookedServiceBean.DataBean serviceBean) {
            mainActivity.gotoServiceEdit( serviceBean);
            }

            @Override
            public void statusClick(BookedServiceBean.DataBean serviceBean) {

            }
        });

    }

    boolean isShowMore;

//    @OnClick(R.id.iv_service_more)
//    public void showMoreFunction() {
//        isShowMore = !isShowMore;
//        iv_service_more.setImageResource(isShowMore ? R.mipmap.btn_more_red2 : R.mipmap.btn_more_red1);
//        if (isShowMore) {
//            MoreFunctionsPop pop = new MoreFunctionsPop(1, getActivity(), iv_service_more);
//            pop.showPop();
//            pop.setOnDismissListener(new MoreFunctionsPop.OnDismissListener() {
//                public void onDismiss() {
//                    isShowMore = false;
//                    iv_service_more.setImageResource(R.mipmap.btn_more_red1);
//                }
//            });
//            pop.setOnMoreClickListener(new MoreFunctionsPop.OnMoreClickListener() {
//                @Override
//                public void lunbanClick() {//轮班
////                    Toast.makeText(getActivity(), "轮班", Toast.LENGTH_SHORT).show();
//                    gotoShift();
//                }
//
//                @Override
//                public void searchCurren() {//搜索当前
////                    Toast.makeText(getActivity(), "搜索当前", Toast.LENGTH_SHORT).show();
//                    showSearchDialog();
//                }
//
//                @Override
//                public void searchAll() {//搜索所有
////                    Toast.makeText(getActivity(), "搜索所有", Toast.LENGTH_SHORT).show();
//
//                }
//            });
//        }
//    }

    private void showSearchDialog() {
        SearchServiceDialog dialog = new SearchServiceDialog(getActivity(), ((MainActivity) getActivity()).getRightWidth(), MyApplication.getInstance().getMenuBean());
        dialog.showDiaglog();
    }


    //跳转到排班
    public  void  gotoShift(){
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
}
