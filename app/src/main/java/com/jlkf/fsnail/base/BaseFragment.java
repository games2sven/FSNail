package com.jlkf.fsnail.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;

import com.jlkf.fsnail.bean.EventCenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Map;

import me.lxw.dtl.ui.UIBaseFragment;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public abstract class BaseFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if (isBindEventBusHere()){
            Log.i("Sven","register");
            EventBus.getDefault().register(this);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
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
    public void onDestroy() {
        super.onDestroy();
        if (isBindEventBusHere()){
            EventBus.getDefault().unregister(this);
        }
    }

    public void addParams(Map<String, String> params, String key, String value){
        if (!TextUtils.isEmpty(value)){
            Log.e("params",key+"   "+value);
            params.put(key,value);
        }

    }
}
