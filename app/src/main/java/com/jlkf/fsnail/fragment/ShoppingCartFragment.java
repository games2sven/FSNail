package com.jlkf.fsnail.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.ShopCartAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ShopCartBean;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.CustomDialog;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.UiUtil;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class ShoppingCartFragment extends BaseFragment {
    private ShopCartAdapter mAdapter;

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }
@Bind(R.id.tv_shop_cart_jifeng)
    TextView tv_shop_cart_jifeng;
@Bind(R.id.total_price)
    TextView total_price;
@Bind(R.id.recylerview)
RecyclerView recylerview;
//@Bind(R.id.checkBox2)
//    CheckBox checkBox2;
//@Bind(R.id.checkBox1)
//    CheckBox checkBox1;

MainActivity mainActivity;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_shopping_cart,null);
        ButterKnife.bind(this,view);
        mainActivity= (MainActivity) getActivity();
        initRecyclerView();
        initNet();
        return view ;
    }


    private void initRecyclerView() {
        recylerview.setLayoutManager(new GridLayoutManager(getActivity(), 1));
        mAdapter = new ShopCartAdapter(mainActivity,mDatas);
        recylerview.setAdapter(mAdapter);
        mAdapter.setItemClickListener(new ShopCartAdapter.OnItemClickListener() {

            @Override
            public void onDelClick(final ShopCartBean.DataBean dataBean) {
                final CustomDialog customDialog =new CustomDialog(mainActivity);
                customDialog.setOnButtonClickListener(new CustomDialog.OnButtonClickListener() {
                    @Override
                    public void onButton1Click(View v) {
                        customDialog.dismiss();
                        delShopCart(dataBean.getId());
                    }

                    @Override
                    public void onButton2Click(View v) {
                      customDialog.dismiss();

                    }
                });
                customDialog.ShowDialog("确定","取消","确认清除购物车中的此订单?",null,null,null, Gravity.CENTER,Gravity.CENTER);

            }

            @Override
            public void onCheckChange() {
                calculatePrice(mDatas);
            }
        });
    }

    private  void calculatePrice(List<ShopCartBean.DataBean> dataBeans){
        if (dataBeans==null||dataBeans.size()==0){

            total_price.setText("€"+0);
        }else{
            int  sum=0;
            for (ShopCartBean.DataBean dataBean : dataBeans){
                if (dataBean.isSelect()) {
                    sum +=dataBean.getPrice();
                }
            }

            total_price.setText("€"+sum);
        }


    }


    private void initNet() {
     getShopCartList();
    }


    void  delShopCart(int  id){
        Map<String ,String > parames = new HashMap<>();
        parames.put("",id+"");
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.DEL_SHOPCART, this, parames, new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {
                 Log.e("respone","delete="+response.toString());
                if (response.getCode()==200){//删除成功
                    UiUtil.showToast(R.string.del_success);
                    getShopCartList();
                }else{//删除失败
                    if (!TextUtils.isEmpty(response.getMsg()))
                    UiUtil.showToast(response.getMsg());

                }
            }

            @Override
            public void onFailure(String errorMsg) {
                if (!TextUtils.isEmpty(errorMsg))
                    UiUtil.showToast(errorMsg);
            }
        });

    }

List<ShopCartBean.DataBean> mDatas = new ArrayList<>();
      int  pageNo=1;
    int  pageSize=7;
    private void getShopCartList() {
Map<String,String>   paramse =new HashMap<>();
      paramse.put("pageNo",pageNo+"");
      paramse.put("pageSize",pageSize+"");

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.SHOP_CART_LIST, this,paramse, new MyHttpCallback<ShopCartBean>() {
            @Override
            public void onSuccess(ShopCartBean response) {
                Log.e("response",response.toString());
                mDatas.clear();
                if (response.getCode()==200){
                    mDatas.addAll(response.getData());
                    if (mDatas.size()==0){
                        mainActivity.popBackFragment(-1);
                        return;
                    }
                    calculatePrice(mDatas);
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

//    private void initCheckBox() {
//        setDrawable(checkBox1);
//        setDrawable(checkBox2);
//    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.shop_cart_ensure)
    public void  ensureShopcart(){
        ((MainActivity)getActivity()).popBackFragment(0);
        mainActivity.shoppingCartFragment=null;
        mainActivity.selectFragment(0);
    }

//    @OnClick({R.id.shop_cart_shangping_del,R.id.shop_cart_service_del})
//    public  void  delete(){
//        CustomDialog customDialog =new CustomDialog(mainActivity);
//        customDialog.ShowDialog("确定","取消","确认清除购物车中的此订单?",null,null,null, Gravity.CENTER,Gravity.CENTER);
//    }

    int  jifen=0;
    @OnClick(R.id.shop_cart_minus)
    public  void  minusJifen(){
        jifen-=10;
        if (jifen>=0){
            tv_shop_cart_jifeng.setText(jifen+"");
        }else{
            jifen=0;
        }

    }
    @OnClick(R.id.shop_cart_add)
    public  void  addJifen(){
        jifen+=10;
        if (jifen>=0){
            tv_shop_cart_jifeng.setText(jifen+"");
        }else{
            jifen=0;
        }

    }
}
