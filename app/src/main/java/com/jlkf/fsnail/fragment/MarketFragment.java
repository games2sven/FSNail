package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.MarketAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.AddShopCarBean;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.GoodsBean;
import com.jlkf.fsnail.bean.OrderBean;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.ClearShopCarDialog;
import com.jlkf.fsnail.dialog.SearchMarketDialog;
import com.jlkf.fsnail.dialog.SelectCustomerDialog;
import com.jlkf.fsnail.dialog.SingleFunctionPop;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.UiUtil;
import com.jlkf.fsnail.widget.PageIndexView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MarketFragment extends BaseFragment{

    List<GoodsBean.DataBean> mDatas =new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;
    @Bind(R.id.tv_shop_cart_num)
    TextView tv_shop_cart_num;
    @Bind(R.id.page_index_view)
    PageIndexView page_index_view;

    private int  pageNo=1;
    private int  pageSize=6;


    private  MarketAdapter adapter;
    GoodsBean.DataBean selectedGood;
    private int shopcarNum = 0;

    Map<String,String> mParams;

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_market,null);
        ButterKnife.bind(this,view);

        loadData();
        return view;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        Object object = eventCenter.getData();
        switch (eventCenter.getEventCode()){
            case Constants.CODE_MARKET_ADD_SHOPCAR://添加到购物车
                selectedGood = (GoodsBean.DataBean)object;
                SelectCustomerDialog customerDialog = new SelectCustomerDialog(getActivity());
                customerDialog.showDiaglog();
                break;
            case Constants.CODE_SELECT_CUSTOMER_RETURN://选择顾客返回
                ServiceMenuBean.DataBean.CustomerBean selectedCustomer = (ServiceMenuBean.DataBean.CustomerBean)object;
                add2ShopCar(selectedCustomer.getId()+"",selectedGood.getId()+"");
                break;
            case Constants.CODE_CLEAR_ACCOUNT_SHOPCAR://清空购物车
                String uid = (String)object;
                clearShopCar(uid);
                break;
            case Constants.CODE_SEARCH_GOODS://搜索商品
                mParams = (Map<String,String>)object;
                loadData();
                break;
        }
    }

    public void loadData(){
        if(mParams == null){
            mParams = new HashMap<>();
        }

        mParams.put("pageNo",pageNo+"");
        mParams.put("pageSize",pageSize+"");

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.GOODS_LIST, this, mParams, new MyHttpCallback<GoodsBean>() {

            @Override
            public void onSuccess(GoodsBean response) {
                page_index_view.setTotalPage(response.getTotalPage());
                page_index_view.setCurrentPage(pageNo);
                mDatas = response.getData();
                initRecyclerview();
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });

    }

    public void add2ShopCar(String uid,String gid){
        Map<String,String> params = new HashMap<>();
        params.put("uid",uid);
        params.put("gid",gid);
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.ADD_SHOPCAR, this, params, new MyHttpCallback<AddShopCarBean>() {

            @Override
            public void onSuccess(AddShopCarBean response) {
                if(response.getCode() == 200){
                    Log.i("Sven","code == 200");
                    if(response.getData() != null){//不为空，，先清空购物车
                        Log.i("Sven","不为空");
                        ClearShopCarDialog dialog = new ClearShopCarDialog(getActivity(),response.getData());
                        dialog.showDialog();
                    }else{//为空，，直接添加到购物车了
                        Log.i("Sven","直接添加购物车");
                        tv_shop_cart_num.setVisibility(View.VISIBLE);
                        shopcarNum += 1;
                        tv_shop_cart_num.setText(shopcarNum+"");
                    }
                }
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }

    public void clearShopCar(String uid){
        Map<String,String> params = new HashMap<>();
        params.put("uid",uid);
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.CLEAR_SHOPCAR, this, params, new MyHttpCallback<BaseHttpBean>() {

            @Override
            public void onSuccess(BaseHttpBean response) {
                if(response.getCode() == 200){//清空购物车成功
                    shopcarNum = 0;
                    tv_shop_cart_num.setText(shopcarNum+"");
                    tv_shop_cart_num.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }


    public void initRecyclerview(){
        page_index_view.setOnPageIndexListener(new PageIndexView.OnPageIndexListener() {
            @Override
            public void onLastClick() {
                pageNo--;
                if (pageNo>0){
                    loadData();
                }
            }

            @Override
            public void onNextClick() {
                pageNo++;
                loadData();
            }

            @Override
            public void onIndexClick(int page) {
                pageNo=page;
                page_index_view.setCurrentPage(page);
                loadData();
            }
        });

        recylerview.setLayoutManager(new GridLayoutManager(getActivity(),1));
        adapter = new MarketAdapter(mDatas);
        recylerview.setAdapter(adapter);
    }

    boolean isShowMore;
    @OnClick(R.id.iv_service_more)
    public   void  showMoreFunction(){
        isShowMore=!isShowMore;
        iv_service_more.setImageResource(isShowMore?R.mipmap.btn_more_red2:R.mipmap.btn_more_red1);
        if (isShowMore){
            SingleFunctionPop pop =new SingleFunctionPop(1,getActivity(),iv_service_more);
            pop.showPop();
            pop.setOnDismissListener(new SingleFunctionPop.OnDismissListener() {
                public void onDismiss() {
                    isShowMore=false;
                    iv_service_more.setImageResource(R.mipmap.btn_more_red1);
                }
            });
            pop.setOnMoreClickListener(new SingleFunctionPop.OnMoreClickListener() {
                @Override
                public void searchAll() {//搜索所有
                    showSearchDialog();
                }
            });
        }
    }

    private void showSearchDialog() {
        SearchMarketDialog dialog = new SearchMarketDialog(getActivity(), ((MainActivity) getActivity()).getRightWidth());
        dialog.showDiaglog();
    }

}
