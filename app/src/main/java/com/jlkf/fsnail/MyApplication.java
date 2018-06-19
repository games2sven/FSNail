package com.jlkf.fsnail;

import android.app.Application;
import android.content.Context;

import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.tencent.bugly.crashreport.CrashReport;

import me.lxw.dtl.utils.DTLUtils;

/**
 * Created by Administrator on 2018/5/23 0023.
 */

public class MyApplication  extends Application{
    public ServiceMenuBean getMenuBean() {
        return menuBean;
    }

    public void setMenuBean(ServiceMenuBean menuBean) {
        this.menuBean = menuBean;
    }

    ServiceMenuBean menuBean;
    private static MyApplication instance;
    private Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        context = getApplicationContext();
        DTLUtils.init(getApplicationContext());
        //bugly
        CrashReport.initCrashReport(getApplicationContext());
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public Context getContext() {
        return context;
    }
}
