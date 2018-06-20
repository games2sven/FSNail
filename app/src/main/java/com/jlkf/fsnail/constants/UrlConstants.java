package com.jlkf.fsnail.constants;

import com.jlkf.fsnail.BuildConfig;

/**
 * Created by Administrator on 2018/6/4 0004.
 */

public class UrlConstants {
    //接口域名
    public static final String SERVER_API = "http://120.77.52.85:8899//fs_api/";
    public static final String SERVICE_LIST = SERVER_API + "service/serviceList";
    public static final String LOG_IN = SERVER_API + "login/staffLogin"; //登录
    public static final String BOOKED_SERVICE_LIST = SERVER_API + "service/appointList";
    public static final String USER_AGREEMENT = SERVER_API + "sys/userAgreementAd";
    public static final String SHOP_CART_LIST = SERVER_API + "service/shopcartList";
    public static final String DEL_SHOPCART = SERVER_API + "service/delShopCart";
    public static final String MENU_LIST =SERVER_API+"sys/sMenu" ;

    public static final String ADD_NEW_SERVICE = SERVER_API + "service/newService";
    public static final String UPDATE_SERVICE = SERVER_API + "service/updateService";
    public static final String ADD_NEW_USER = SERVER_API + "service/newUser";
    public static final String UPDATE_USER = SERVER_API + "service/updateUser";
    public static final String UPDATE_SERVICE_STATUS =SERVER_API + "service/updateServiceStatus";
    public static final String STAFF_LIST =SERVER_API+ "staff/staffList";
    public static final String STAFF_INFO =SERVER_API+ "staff/staffInfo";
    public static final String UPDATE_STAFF =SERVER_API+ "staff/updateStaff";
    public static final String LOCKED_AD =SERVER_API+"sys/lockedAd" ;
    public static final String STAFF_WEEKLY =SERVER_API+"staff/staffWeekly" ;
    public static final String TODAY_WORK = SERVER_API+"staff/todayWork";
    public static String ADD_STAFF=SERVER_API+ "staff/newStaff";
    public static String USER_LIST=SERVER_API + "service/userList";

    public static String GOODS_LIST = SERVER_API + "goods/goodsList";//商品列表
    public static String PRICE_LIST = SERVER_API + "price/allPriceList";//价格单列表
    public static String ORDER_LIST = SERVER_API + "order/orderList";//订单列表
    public static String CARD_LIST = SERVER_API + "card/cardList";//卡券列表
    public static String CARD_DETAIL = SERVER_API + "card/cardDetailInfo";//卡券详情
    public static String BOOK_LIST = SERVER_API + "service/appointList";//预约列表
    public static String ADD_BOOK = SERVER_API + "service/newAppointService";//添加预约
    public static String EDIT_BOOK = SERVER_API + "service/updateAppointService";//修改预约
    public static String CANCEL_BOOK = SERVER_API + "service/updateServiceStatus";//取消预约
    public static String CHECK_CUSTOMER= SERVER_API + "sys/infoVerify";//验证客户信息是否完善
    public static String GOODS_TYPE_LIST= SERVER_API + "goods/goodsTypeList";//商品类型下拉列表
    public static String CHECK_BOOK = SERVER_API + "staff/appointment";//预约查看
    public static String CARD_CONSUME = SERVER_API + "card/cardRecordList";//卡券消费记录列表

    public static String ADD_SHOPCAR = SERVER_API + "shopCart/addShopCart";//添加购物车
    public static String CLEAR_SHOPCAR = SERVER_API + "shopCart/deleteShopCart";//清空购物车
    public static String ADD_BOOK_TO_SERVICE=SERVER_API +"service/updateAppoint";//预约加入服务列表
}
