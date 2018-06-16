package com.jlkf.fsnail.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.View;

import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.utils.ToastUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import me.lxw.dtl.ui.UIBaseFragmentActivity;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isBindEventBusHere()){
            EventBus.getDefault().register(this);
        }
    }

    protected abstract  boolean  isBindEventBusHere();


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEventMainThread(EventCenter eventCenter) {
        if (null != eventCenter) {
            onEventComing(eventCenter);
        }
    }

    protected abstract  void  onEventComing(EventCenter eventCenter);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()){
            EventBus.getDefault().unregister(this);
        }
    }


    protected void showToast(String str){
        ToastUtil.showToast(this,str);
    }

}
