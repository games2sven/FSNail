package com.jlkf.fsnail.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;


import com.jlkf.fsnail.R;
import com.jlkf.fsnail.utils.DateUtils;
import com.jlkf.fsnail.widget.Wheel.OnYbWheelChangedListener;
import com.jlkf.fsnail.widget.Wheel.StrericYbWheelAdapter;
import com.jlkf.fsnail.widget.Wheel.WheelYbView;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * author: Yangbin
 * time  : 2017/1/6 15:09
 * desc  :
 */
public class YbTimeSelectorWheelView extends RelativeLayout implements
        OnYbWheelChangedListener {
    private final String flag = "PfpsDateWheelView";
    private WheelYbView wvHour;
    private WheelYbView wvMinute;
    private String[] hours = new String[24];
    private String[] minutes = new String[60];
    private StrericYbWheelAdapter hoursAdapter;
    private StrericYbWheelAdapter minutesAdapter;

    String mHour = "00";
    String mMinute = "00";

    public YbTimeSelectorWheelView(Context context, AttributeSet attrs,
                                   int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initLayout(context);
    }

    public YbTimeSelectorWheelView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initLayout(context);
    }

    public YbTimeSelectorWheelView(Context context) {
        super(context);
        initLayout(context);
    }

    private void initLayout(Context context) {
        LayoutInflater.from(context).inflate(R.layout.time_layout, this,
                true);
        wvHour = (WheelYbView) findViewById(R.id.wv_date_of_hour);
        wvMinute = (WheelYbView) findViewById(R.id.wv_date_of_minute);
        wvHour.addChangingListener(this);
        wvMinute.addChangingListener(this);
        setData();
    }

    private void setData() {
        for (int i = 0; i < hours.length; i++) {
            if(i<10){
                hours[i] = "0" + i + " 时";
            }else{
                hours[i] = i + " 时";
            }

        }
        for (int i = 0; i < minutes.length; i++) {
            if(i<10){
                minutes[i] = "0" + i + " 分";
            }else{
                minutes[i] = i + " 分";
            }
        }

        hoursAdapter = new StrericYbWheelAdapter(hours);
        minutesAdapter = new StrericYbWheelAdapter(minutes);
        wvHour.setAdapter(hoursAdapter);
        wvHour.setCurrentItem(0);
        wvHour.setCyclic(true);
        wvMinute.setAdapter(minutesAdapter);
        wvMinute.setCurrentItem(0);
        wvMinute.setCyclic(true);
    }

    int currentMinute = 1;

    @Override
    public void onChanged(WheelYbView wheel, int oldValue, int newValue) {
        String trim = null;
        switch (wheel.getId()) {
            case R.id.wv_date_of_hour:
                trim = DateUtils.splitDateString(wvHour.getCurrentItemValue())
                        .trim();
                mHour = trim;
                break;
            case R.id.wv_date_of_minute:
                trim = DateUtils.splitDateString(wvMinute.getCurrentItemValue())
                        .trim();
                currentMinute = Integer.parseInt(trim);
//                wvMinute.setCurrentItem(0);
                mMinute = currentMinute+"";
                break;
        }
    }

    /**
     * 获取选择的日期的值
     *
     * @return
     */
    public String getSelectedTime() {
        return mHour + ":"
                + mMinute ;
    }

}
