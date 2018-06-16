package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.UserAgreementBean;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserAgreementFragment extends BaseFragment {
   MainActivity activity  ;
    @Bind(R.id.tv_agreement_content)
    TextView tv_agreement_content;


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

      View view =LayoutInflater.from(activity).inflate(R.layout.fragment_user_agreement,null);
        ButterKnife.bind(this,view);
         initNet();

        return  view;
    }

    private void initNet() {

   getUserAgreement();

    }

    private void getUserAgreement() {//USER_AGREEMENT

        OKHttpUtils.getIntance().oKHttpGet(UrlConstants.USER_AGREEMENT, this, new MyHttpCallback<UserAgreementBean>() {
            @Override
            public void onSuccess(UserAgreementBean response) {
                if (response.getCode()==200){
                    Log.e("response",response.toString());
                    tv_agreement_content.setText(response.getData().getContent());
                }
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    @OnClick(R.id.tv_return)
    public  void  backFinish(){
        activity.popBackFragment(-1);
    }
}
