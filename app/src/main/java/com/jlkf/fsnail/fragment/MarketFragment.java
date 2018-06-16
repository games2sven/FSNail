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
import android.widget.Toast;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.MarketAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.GoodsBean;
import com.jlkf.fsnail.bean.OrderBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.ClearShopCarDialog;
import com.jlkf.fsnail.dialog.SearchMarketDialog;
import com.jlkf.fsnail.dialog.SelectCustomerDialog;
import com.jlkf.fsnail.dialog.SingleFunctionPop;
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


public class MarketFragment extends BaseFragment{

    List<GoodsBean.DataBean> mDatas =new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;

    private  MarketAdapter adapter;

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_market,null);
        ButterKnife.bind(this,view);

        initData();
        return view;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        Log.i("Sven","onEventComing");
        Object object = eventCenter.getData();
        switch (eventCenter.getEventCode()){
            case Constants.CODE_MARKET_ADD_SHOPCAR:
                SelectCustomerDialog customerDialog = new SelectCustomerDialog(getActivity());
                customerDialog.showDiaglog();
                break;
            case Constants.CODE_SELECT_CUSTOMER_RETURN:
                ClearShopCarDialog dialog = new ClearShopCarDialog(getActivity());
                dialog.showDialog();
                break;
            case Constants.CODE_CLEAR_ACCOUNT_SHOPCAR://清空购物车
                break;
            case Constants.CODE_SEARCH_GOODS://搜索商品
                Map<String,String> params = (Map<String,String>)object;
                loadData(params);
                break;
        }
    }

    public void initData(){

        Map<String, String> params = new HashMap<>();

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.GOODS_LIST, this, params, new MyHttpCallback<GoodsBean>() {

            @Override
            public void onSuccess(GoodsBean response) {
                mDatas = response.getData();
                initRecyclerview();
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });

    }

    public void loadData( Map<String, String> params){
        Log.e("Sven",OKHttpUtils.getMapParamStr(params));
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.GOODS_LIST, this, params, new MyHttpCallback<GoodsBean>() {

            @Override
            public void onSuccess(GoodsBean response) {
                mDatas = response.getData();
                adapter.setDatas(mDatas);
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }

    public void initRecyclerview(){
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
