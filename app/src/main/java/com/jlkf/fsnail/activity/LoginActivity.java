package com.jlkf.fsnail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.gson.Gson;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.base.BaseActivity;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.UserBean;
import com.jlkf.fsnail.constants.SPConstants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.SPUtil;
import com.jlkf.fsnail.utils.UiUtil;
import com.jlkf.fsnail.utils.VerifyUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.HashMap;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {

    @Bind(R.id.email)
    EditText et_email;
    @Bind(R.id.password)
    EditText et_pwd;
    @Bind(R.id.login_tip)
    LinearLayout login_tip;
    @Bind(R.id.email_sign_in_button)
    Button email_sign_in_button;

    private String account;
    private String password;

    private MaterialDialog loginlDialog;
    private Gson gson = new Gson();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        login_tip.setVisibility(View.INVISIBLE);
        String  loginInfo=(String) SPUtil.get(this, SPConstants.LOG_INFO,"");
        if (!TextUtils.isEmpty(loginInfo))
        {
          UserBean userBean =   gson.fromJson(loginInfo,UserBean.class);
            if (userBean!=null){
                Intent intent = new Intent();
                intent.setClass(LoginActivity.this,MainActivity.class);
                intent.putExtra("data",userBean.getData());
                startActivity(intent);
                finish();
            }
        }

    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        int  code =eventCenter.getEventCode();
        Object object =eventCenter.getData();

    }

    @OnClick(R.id.email_sign_in_button)
   public  void clickSignIn(){
        email_sign_in_button.setEnabled(false);

        account = et_email.getText().toString();
        password = et_pwd.getText().toString();

        if(TextUtils.isEmpty(account)){
            showToast(getString(R.string.tip_enter_account));
            email_sign_in_button.setEnabled(true);
            return;
        }else {
            if(!VerifyUtil.isValidEmail(account)&&!VerifyUtil.isMobileNO(account)){
                showToast(getString(R.string.enter_correct_account));
                email_sign_in_button.setEnabled(true);
                return;
            }else if(TextUtils.isEmpty(password)){
                showToast(getString(R.string.tip_enter_password));
                email_sign_in_button.setEnabled(true);
                return;
            }

            loginlDialog = UiUtil.getMaterialDialog(this, UiUtil.getString(R.string.laucning));

            Map<String, String> params = new HashMap<>();
            params.put("phone", account);
            params.put("password", password);

            OKHttpUtils.getIntance().oKHttpPost(UrlConstants.LOG_IN, this, params, new MyHttpCallback<UserBean>() {

                @Override
                public void onSuccess(UserBean response) {
                    loginlDialog.dismiss();
                    if (response.getCode()==200){
                        Intent intent = new Intent();
                        intent.setClass(LoginActivity.this,MainActivity.class);
                        intent.putExtra("data",response.getData());

                        startActivity(intent);
                        finish();
                    }else{
                        UiUtil.showToast(response.getMsg());
                        login_tip.setVisibility(View.VISIBLE);
                        email_sign_in_button.setEnabled(true);
                    }

                }

                @Override
                public void onFailure(String errorMsg) {
                    UiUtil.cancelDialog(loginlDialog, errorMsg);
                    email_sign_in_button.setEnabled(true);
                    login_tip.setVisibility(View.VISIBLE);
                }
            });
        }
   }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
       if (keyCode==KeyEvent.KEYCODE_BACK){
           finish();
       }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(loginlDialog!=null&&loginlDialog.isShowing()){
            loginlDialog.dismiss();
            loginlDialog = null;
        }
        ButterKnife.unbind(this);
        OkHttpUtils.getInstance().cancelTag(this);
    }

}

