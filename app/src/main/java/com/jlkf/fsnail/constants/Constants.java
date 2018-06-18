package com.jlkf.fsnail.constants;

import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;

/**
 * Created by Administrator on 2018/5/26 0026.
 */

public class Constants {

    ////服务状态码代表意思
    public static String getStaus( int code){
        switch (code){
            case 1://未开始
                return MyApplication.getInstance().getContext().getString(R.string.status_no_tart);
            case 2://进行中
                return MyApplication.getInstance().getContext().getString(R.string.status_progress);
            case 3://以结束
                return MyApplication.getInstance().getContext().getString(R.string.status_finish);
            case 4://已预约
                return MyApplication.getInstance().getContext().getString(R.string.status_booked);
            case 5://取消
                return MyApplication.getInstance().getContext().getString(R.string.status_cancel);
        }
        return MyApplication.getInstance().getContext().getString(R.string.status_no_tart);
    }



    /*************************************************************************************
     * 以下是EventBus的事件码*****不可更改，不可重复******** CODE_+code              *
     ************************************************************************************/
    public static final int CODE_CRAD_DETAIL             = 15;// 点击查看卡券详情
    public static final int CODE_CRAD_DETAIL_RETURN             = 16;// 查看卡券详情返回
    public static final int CODE_CHECK_CARD_CONSUME             = 17;// 查看卡券消费记录
    public static final int CODE_CHECK_CARD_CONSUME_RETURN             = 18;// 查看卡券消费记录返回
    public static final int CODE_ADD_BOOK             = 19;// 添加预约
    public static final int CODE_EDIT_BOOK            = 20;// 修改预约
    public static final int CODE_CHECK_BOOK            = 21;// 查看预约
    public static final int CODE_MARKET_ADD_SHOPCAR           = 22;// 商城添加购物车
    public static final int CODE_CARD_SELECT_DATE          = 23;// 卡券列表点击选择日期
    public static final int CODE_SELECT_CUSTOMER_RETURN         = 24;// 选择顾客账号返回
    public static final int CODE_CLEAR_ACCOUNT_SHOPCAR         = 25;// 清空账号购物车
    public static final int CODE_SELETC_STAFF_TIME         = 26;// 清空账号购物车
    public static final int CODE_SELETC_CANCEL_TIME         = 27;// 清空账号购物车
    public static final int CODE_UPDATE_SERVICE =28 ;
    public static final int CODE_SEARCH_ORDERLIST =29 ;//查询订单列表
    public static final int CODE_SEARCH_CUSTOMERLIST =30 ;//查询订单列表

    public static final int CODE_TO_REGISTER =31 ;//去登记
    public static final int CODE_CANCEL_BOOK =32 ;//取消预约
    public static final int CODE_ADD_BOOK_SUCCESS =33 ;//添加预约成功&修改预约成功
    public static final int CODE_SEARCH_GOODS =34 ;//搜索商品

    public static final int CODE_UPDATE_STAFF =35 ;
    public static final int CODE_SEARCH_BOOK =36 ;//查找预约





}
