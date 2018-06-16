package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.StaffManagerAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.StaffManagerBean;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.SearchStaffManagerDialog;
import com.jlkf.fsnail.dialog.TwoFunctionPop;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.UiUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/31 0031.
 */

public class StaffManagerFragment  extends BaseFragment{
    MainActivity activity;
    @Bind(R.id.recylerview)
    RecyclerView recyclerView;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;
    private StaffManagerAdapter mAdapter;
    private List<StaffManagerBean.DataBean > mDatas=new ArrayList<>();
    private boolean isShowMore;


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
         activity = (MainActivity) getActivity();

        View view =LayoutInflater.from(activity).inflate(R.layout.fragment_staff_manager,null);
        ButterKnife.bind(this,view);
        initRecyclerView();
        getStaffManagerList();
        return  view ;

    }

    private void initRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),1));
        mAdapter = new StaffManagerAdapter(mDatas);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setClickListener(new StaffManagerAdapter.OnStaffManagerClickListener() {
            @Override
            public void onClickDetail(StaffManagerBean.DataBean  dataBean) {
                   activity.gotoStaffDetail(dataBean);
            }
        });

    }

    @OnClick(R.id.iv_service_more)
    public  void  showMore(){
        isShowMore=!isShowMore;
        iv_service_more.setImageResource(isShowMore ? R.mipmap.btn_more_red2 : R.mipmap.btn_more_red1);

        if (isShowMore) {
            TwoFunctionPop pop  =new  TwoFunctionPop(0,activity,iv_service_more);
            pop.setOnDismissListener(new TwoFunctionPop.OnDismissListener() {
                public void onDismiss() {
                    isShowMore = false;
                    iv_service_more.setImageResource(R.mipmap.btn_more_red1);
                }
            });
            pop.setOnMoreClickListener(new TwoFunctionPop.OnMoreClickListener() {
                @Override
                public void add() {//添加
                    gotoStaffAdd();
                }

                @Override
                public void searchAll() {//搜
                    showSearchDialog();
                }
            });
            pop.showPop();
        }

    }

    private void showSearchDialog() {
        SearchStaffManagerDialog dialog = new SearchStaffManagerDialog(getActivity(), ((MainActivity) getActivity()).getRightWidth());
        dialog.setSearchClickListener(new SearchStaffManagerDialog.OnSearchClickListener() {
            @Override
            public void onClick(String id, String uName, String nickName, String phone) {
                pageNo=1;
                clearSearchCondition();
                StaffManagerFragment.this.id=id;
                StaffManagerFragment.this.uName=uName;
                StaffManagerFragment.this.nickName=nickName;
                StaffManagerFragment.this.phone=phone;
                 getStaffManagerList();
            }
        });
        dialog.showDiaglog();

    }

    private  void  clearSearchCondition(){
        id=null;nickName=null;uName=null;phone=null;
    }


    private void gotoStaffAdd() {
        activity.gotoStaffDetail(null);
    }
    String   id;// 员工编号
    String   uName;//昵称
    String   nickName;//姓名
    String   phone;//手机
    int      pageNo=1;//页码
    int     pageSize=5;// 每页大小

    private void  getStaffManagerList(){
        Map<String,String> params = new HashMap<>();

addParams(params,"id",id);
addParams(params,"uName",uName);
addParams(params,"nickName",nickName);
addParams(params,"phone",phone);
addParams(params,"pageNo",pageNo+"");
addParams(params,"pageSize",pageSize+"");
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.STAFF_LIST, this, params, new MyHttpCallback<StaffManagerBean>() {
            @Override
            public void onSuccess(StaffManagerBean response) {
                mDatas.clear();
                if (response.getCode()==200){
                    mDatas.addAll(response.getData());
                }else{
                    UiUtil.showToast(response.getMsg());
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(String errorMsg) {
                UiUtil.showToast(errorMsg);
            }
        });



    }
}
