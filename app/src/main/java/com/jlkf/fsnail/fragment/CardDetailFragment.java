package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.Card2Bean;
import com.jlkf.fsnail.bean.CardBean;
import com.jlkf.fsnail.bean.CardDetailBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.TimeUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CardDetailFragment extends BaseFragment implements View.OnClickListener{

    @Bind(R.id.tv_save)
    TextView tv_save;
    @Bind(R.id.tv_return)
    TextView tv_return;
    @Bind(R.id.tv_card_num)
    TextView tv_card_num;
    @Bind(R.id.tv_pro_name)
    TextView tv_pro_name;
    @Bind(R.id.tv_pro_type)
    TextView tv_pro_type;
    @Bind(R.id.tv_price)
    TextView tv_price;
    @Bind(R.id.tv_discount)
    TextView tv_discount;
    @Bind(R.id.tv_voucher)
    TextView tv_voucher;
    @Bind(R.id.tv_last_price)
    TextView tv_last_price;
    @Bind(R.id.tv_buy_time)
    TextView tv_buy_time;
    @Bind(R.id.tv_buy_name)
    TextView tv_buy_name;
    @Bind(R.id.tv_buy_phone)
    TextView tv_buy_phone;
    @Bind(R.id.tv_binding_time)
    TextView tv_binding_time;
    @Bind(R.id.tv_card_type)
    TextView tv_card_type;
    @Bind(R.id.tv_get_time)
    TextView tv_get_time;
    @Bind(R.id.tv_use_price)
    TextView tv_use_price;
    @Bind(R.id.tv_left_price)
    TextView tv_left_price;
    @Bind(R.id.tv_check)
    TextView tv_check;
    @Bind(R.id.et_bind_name)
    EditText et_bind_name;
    @Bind(R.id.et_bind_phone)
    EditText et_bind_phone;


    private CardBean.DataBean mCardBean;

    List<CardDetailBean.DataBean> mDatas =new ArrayList<>();

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  =LayoutInflater.from(container.getContext()).inflate(R.layout.conpous_detail,null);
        ButterKnife.bind(this,view);

        Bundle bundle = getArguments();
        mCardBean =(CardBean.DataBean) bundle.getSerializable("data");
        loadData();

        return view;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    public void initView(){
        tv_save.setOnClickListener(this);
        tv_return.setOnClickListener(this);
        tv_check.setOnClickListener(this);

        if(mDatas != null && mDatas.size() != 0){
            tv_card_num.setText(mDatas.get(0).getId()+"");
            tv_pro_name.setText(mDatas.get(0).getCardName());
            tv_pro_type.setText(mDatas.get(0).getName());
            tv_price.setText(mDatas.get(0).getPrice()+"");
            tv_discount.setText(mDatas.get(0).getDiscount()+"");
            tv_voucher.setText(mDatas.get(0).getUse_card_price()+"");
            tv_last_price.setText(mDatas.get(0).getPrice()-mDatas.get(0).getUse_card_price()+"");
            tv_buy_time.setText(TimeUtil.paserTimeToYM(mDatas.get(0).getPay_optime()));
            tv_buy_name.setText(mDatas.get(0).getPayUser());
            tv_buy_phone.setText(mDatas.get(0).getPayUserPhone());
            et_bind_name.setText(mDatas.get(0).getIsBandUser());
            et_bind_phone.setText(mDatas.get(0).getIsBandUserPhone());
            tv_binding_time.setText(TimeUtil.paserTimeToYM(mDatas.get(0).getBand_optime()));
            tv_card_type.setText(mDatas.get(0).getType()+"");
            tv_use_price.setText(mDatas.get(0).getHava_use_price()+"");
            tv_left_price.setText(mDatas.get(0).getRemain_price()+"");
        }
    }

    public void loadData(){
        Map<String,String> params = new HashMap<String,String>();
        params.put("id",mCardBean.getId()+"");
        Log.i("Sven","carddetail :"+OKHttpUtils.getMapParamStr(params));

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.CARD_DETAIL, this, params, new MyHttpCallback<CardDetailBean>() {

            @Override
            public void onSuccess(CardDetailBean response) {
                if(response.getCode() == 200){
                    mDatas = response.getData();
                    initView();
                }
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_save:
                break;
            case R.id.tv_return:
                ((MainActivity)getActivity()).popBackFragment(6);
                break;
            case R.id.tv_check:
                EventBus.getDefault().post(new EventCenter(Constants.CODE_CHECK_CARD_CONSUME,mCardBean.getId()+""));
                break;
        }
    }
}
