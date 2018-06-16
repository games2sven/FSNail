package com.jlkf.fsnail.net;

import android.text.TextUtils;
import android.util.Log;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.bean.UserBean;
import com.jlkf.fsnail.constants.SPConstants;
import com.jlkf.fsnail.utils.GsonUtil;
import com.jlkf.fsnail.utils.SPUtil;
import com.jlkf.fsnail.utils.UiUtil;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Call;

/**
 * 描 述: 请求回调
 * 作 者: sven
 * 创 建： 2016/8/19
 * 版 本：
 */
public abstract class MyHttpCallback<T extends BaseHttpBean> extends StringCallback {
    private Class<T> entityClass;

    public MyHttpCallback() {
        Type genType = this.getClass().getGenericSuperclass();
        entityClass = (Class<T>) ((ParameterizedType) genType).getActualTypeArguments()[0];
    }

    @Override
    public void onResponse(String response, int id) {
        if(response != null){
//            LogUtil.e("", "*返*回*参*数*：response = " + response);
        }
        T bean = null;
        String tips = "";
        int status = -1;
        //解析数据
        try {
            Log.i("Sven","response = "+response);
            bean = GsonUtil.JsonToEntity(response, entityClass);
        } catch (Exception e) {
            e.printStackTrace();
            bean = null;
            try {
                JSONObject jsonObject = new JSONObject(response == null ? "" : response); //fixed by yangchao 2016/10/8 :防止空指针崩溃问题
                tips = jsonObject.getString("msg");
            } catch (JSONException e1) {
                e.printStackTrace();
                bean = null;
            }
        }
        //回调
        if (bean != null) {
                onSuccess(bean); //成功回调

            if (bean instanceof  UserBean){

                Log.e("wangsheng"," saveloginfo");
                SPUtil.put(UiUtil.getContext(), SPConstants.LOG_INFO,response) ;
            }else if (bean instanceof ServiceMenuBean){
                Log.e("wangsheng"," savemenu");
                SPUtil.put(UiUtil.getContext(), SPConstants.DROP_DOWN_MENU,response) ;
            }
        } else {
                onFailure(tips); //失败回调
        }
    }

    /**
     * 特殊code码处理
     */
    private void handleRequestStatus() {
    }

    @Override
    public void onError(Call call, Exception e, int id) { //无网络、网络超时或者主动call掉网络请求情况下回调
        if (!call.isCanceled()) { //注意：主动调用call.cancel仍然会执行onError回调
            onFailure(UiUtil.getString(R.string.str_no_internet));
        }
    }

    public abstract void onSuccess(T response);

    public abstract void onFailure(String errorMsg);

}
