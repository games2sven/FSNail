package com.jlkf.fsnail.activity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jlkf.fsnail.MyApplication;
import com.jlkf.fsnail.R;
import com.jlkf.fsnail.base.BaseActivity;
import com.jlkf.fsnail.bean.BookBean;
import com.jlkf.fsnail.bean.Card2Bean;
import com.jlkf.fsnail.bean.CardBean;
import com.jlkf.fsnail.bean.CustomerBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.ServiceBean;
import com.jlkf.fsnail.bean.ServiceMenuBean;
import com.jlkf.fsnail.bean.StaffManagerBean;
import com.jlkf.fsnail.bean.UserBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.SPConstants;
import com.jlkf.fsnail.fragment.AddEditCustomerFragment;
import com.jlkf.fsnail.fragment.AddEditServiceFragment;
import com.jlkf.fsnail.fragment.AddStaffFragment;
import com.jlkf.fsnail.fragment.BookFragment;
import com.jlkf.fsnail.fragment.BookedServiceFragment;
import com.jlkf.fsnail.fragment.CardConsumeFragment;
import com.jlkf.fsnail.fragment.CardDetailFragment;
import com.jlkf.fsnail.fragment.CardFragment;
import com.jlkf.fsnail.fragment.CheckBookFragment;
import com.jlkf.fsnail.fragment.CustomerFragment;
import com.jlkf.fsnail.fragment.EditBookFragment;
import com.jlkf.fsnail.fragment.MarketFragment;
import com.jlkf.fsnail.fragment.MenuFragment;
import com.jlkf.fsnail.fragment.ModifyServiceFragment;
import com.jlkf.fsnail.fragment.OrderFragment;
import com.jlkf.fsnail.fragment.ServiceFragment;
import com.jlkf.fsnail.fragment.ServiceSearchResultFragment;
import com.jlkf.fsnail.fragment.ShiftFragment;
import com.jlkf.fsnail.fragment.ShoppingCartFragment;
import com.jlkf.fsnail.fragment.StaffFragment;
import com.jlkf.fsnail.fragment.StaffManagerFragment;
import com.jlkf.fsnail.fragment.UserAgreementFragment;
import com.jlkf.fsnail.utils.SPUtil;
import com.jlkf.fsnail.widget.NetworkImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/5/24 0024.
 */

public class MainActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.setting)
    ImageView mSetting;
    @Bind(R.id.bg_avator)
    NetworkImageView mAvator;
    @Bind(R.id.tv_name)
    TextView tv_name;
    @Bind(R.id.img_more)
    ImageView img_more;

    @Bind(R.id.ll_service)
    LinearLayout ll_service;
    @Bind(R.id.ll_customer)
    LinearLayout ll_customer;
    @Bind(R.id.ll_employee)
    LinearLayout ll_employee;
    @Bind(R.id.ll_book)
    LinearLayout ll_book;
    @Bind(R.id.ll_menu)
    LinearLayout ll_menu;
    @Bind(R.id.ll_conpous)
    LinearLayout ll_conpous;
    @Bind(R.id.ll_order)
    LinearLayout ll_order;
    @Bind(R.id.ll_chat)
    LinearLayout ll_chat;
    @Bind(R.id.ll_store)
    LinearLayout ll_store;
    @Bind(R.id.img_service)
    ImageView img_service;
    @Bind(R.id.img_customer)
    ImageView img_customer;
    @Bind(R.id.img_employee)
    ImageView img_employee;
    @Bind(R.id.img_book)
    ImageView img_book;
    @Bind(R.id.img_menu)
    ImageView img_menu;
    @Bind(R.id.img_order)
    ImageView img_order;
    @Bind(R.id.img_conpous)
    ImageView img_conpous;
    @Bind(R.id.img_chat)
    ImageView img_chat;
    @Bind(R.id.img_store)
    ImageView img_store;
    @Bind(R.id.tv_service)
    TextView tv_service;
    @Bind(R.id.tv_customer)
    TextView tv_customer;
    @Bind(R.id.tv_employee)
    TextView tv_employee;
    @Bind(R.id.tv_book)
    TextView tv_book;
    @Bind(R.id.tv_menu)
    TextView tv_menu;
    @Bind(R.id.tv_order)
    TextView tv_order;
    @Bind(R.id.tv_conpous)
    TextView tv_conpous;
    @Bind(R.id.tv_chat)
    TextView tv_chat;
    @Bind(R.id.tv_store)
    TextView tv_store;
    @Bind(R.id.main_left)
    View main_left;
    private boolean Unfolded = true;
    FragmentManager magager;
    private StaffManagerFragment staffManagerFragment;
    public AddEditServiceFragment addServiceFragment;
    private AddStaffFragment addStaffFragment;
    private ServiceSearchResultFragment serviceSearchResultFragment;
    private UserAgreementFragment userAgreementFragment;

    public UserBean.Data getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean.Data userBean) {
        this.userBean = userBean;
    }

    UserBean.Data userBean ;
    private StaffFragment staffFragment;
    Gson gson =new Gson();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        userBean= (UserBean.Data) getIntent().getSerializableExtra("data");
        mSetting.setOnClickListener(this);
        ll_service.setOnClickListener(this);
        ll_customer.setOnClickListener(this);
        ll_employee.setOnClickListener(this);
        ll_book.setOnClickListener(this);
        ll_menu.setOnClickListener(this);
        ll_order.setOnClickListener(this);
        ll_conpous.setOnClickListener(this);
        ll_chat.setOnClickListener(this);
        ll_store.setOnClickListener(this);

        magager = getSupportFragmentManager();
        ll_service.performClick();
        selectFragment(0);
        setImgState(true, false, false, false,
                false, false, false, false, false);

        String  menuStr = (String) SPUtil.get(this, SPConstants.DROP_DOWN_MENU,"");
        if (!TextUtils.isEmpty(menuStr)){
            ServiceMenuBean serviceMenuBean =    gson.fromJson(menuStr, ServiceMenuBean.class);
            if (serviceMenuBean!=null){
                MyApplication.getInstance().setMenuBean(serviceMenuBean);
            }
        }


    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

        if (null != eventCenter) {
            Object data = eventCenter.getData();

            switch (eventCenter.getEventCode()) {
                case Constants.CODE_CRAD_DETAIL:
                    selectFragmnetWithParams(0, data);
                    break;
                case Constants.CODE_CRAD_DETAIL_RETURN://卡券详情返回
                    break;
                case Constants.CODE_CHECK_CARD_CONSUME://卡券消费记录
                    selectFragmnetWithParams(1, null);
                    break;
                case Constants.CODE_CHECK_CARD_CONSUME_RETURN://查看卡券消费记录返回
                    break;
                case Constants.CODE_ADD_BOOK://添加预约
                    selectFragmnetWithParams(6, null);
                    break;
                case Constants.CODE_EDIT_BOOK://修改预约
                    selectFragmnetWithParams(5, data);
                    break;
                case Constants.CODE_CHECK_BOOK://查看预约
                    selectFragmnetWithParams(9, data);
                    break;

            }
        }
    }

    private void selectFragment0ButtonState() {
        setBtnState(getBlue(), getTransparent(), getTransparent(), getTransparent(), getTransparent(),
                getTransparent(), getTransparent(), getTransparent(), getTransparent());
        selectFragment(0);
    }

    private void selectFragment1ButtonState() {
        setBtnState(getTransparent(), getBlue(), getTransparent(), getTransparent(), getTransparent(),
                getTransparent(), getTransparent(), getTransparent(), getTransparent());
        selectFragment(1);
    }

    private void selectFragment2ButtonState() {
        setBtnState(getTransparent(), getTransparent(), getBlue(), getTransparent(), getTransparent(),
                getTransparent(), getTransparent(), getTransparent(), getTransparent());
        selectFragment(2);
    }

    private void selectFragment3ButtonState() {
        setBtnState(getTransparent(), getTransparent(), getTransparent(), getBlue(), getTransparent(),
                getTransparent(), getTransparent(), getTransparent(), getTransparent());
        selectFragment(3);
    }

    private void selectFragment4ButtonState() {
        setBtnState(getTransparent(), getTransparent(), getTransparent(), getTransparent(), getBlue(),
                getTransparent(), getTransparent(), getTransparent(), getTransparent());
        selectFragment(4);
    }

    private void selectFragment5ButtonState() {
        setBtnState(getTransparent(), getTransparent(), getTransparent(), getTransparent(), getTransparent(),
                getBlue(), getTransparent(), getTransparent(), getTransparent());
        selectFragment(5);
    }

    private void selectFragment6ButtonState() {
        setBtnState(getTransparent(), getTransparent(), getTransparent(), getTransparent(), getTransparent(),
                getTransparent(), getBlue(), getTransparent(), getTransparent());
        selectFragment(6);
    }

    private void selectFragment7ButtonState() {
        setBtnState(getTransparent(), getTransparent(), getTransparent(), getTransparent(), getTransparent(),
                getTransparent(), getTransparent(), getBlue(), getTransparent());
        selectFragment(7);
    }

    private void selectFragment8ButtonState() {
        setBtnState(getTransparent(), getTransparent(), getTransparent(), getTransparent(), getTransparent(),
                getTransparent(), getTransparent(), getTransparent(), getBlue());
        selectFragment(8);
    }

    private void setBtnState(Drawable service, Drawable customer, Drawable employee, Drawable book,
                             Drawable menu, Drawable order, Drawable conpous, Drawable chat, Drawable store) {
        ll_service.setBackground(service);
        ll_customer.setBackground(customer);
        ll_employee.setBackground(employee);
        ll_book.setBackground(book);
        ll_menu.setBackground(menu);
        ll_order.setBackground(order);
        ll_conpous.setBackground(conpous);
        ll_chat.setBackground(chat);
        ll_store.setBackground(store);
    }

    private Drawable getBlue() {
        return getResources().getDrawable(R.color.white);
    }

    private Drawable getTransparent() {
        return getResources().getDrawable(R.color.green_94c42e);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.setting:
                if (Unfolded) {//收起来
                    Unfolded = false;
                    setPreformClick(View.GONE);
                    mSetting.setImageResource(R.mipmap.left_icon_show);
                } else {//展开
                    Unfolded = true;
                    setPreformClick(View.VISIBLE);
                    mSetting.setImageResource(R.mipmap.left_icon_unshow);
                }
                break;
            case R.id.ll_service:
                selectFragment0ButtonState();
                setImgState(true, false, false, false,
                        false, false, false, false, false);
                break;
            case R.id.ll_customer:
                selectFragment1ButtonState();
                setImgState(false, true, false, false,
                        false, false, false, false, false);
                break;
            case R.id.ll_employee:
                selectFragment2ButtonState();
                setImgState(false, false, true, false,
                        false, false, false, false, false);
                break;
            case R.id.ll_book:
                selectFragment3ButtonState();
                setImgState(false, false, false, true,
                        false, false, false, false, false);
                break;
            case R.id.ll_menu:
                selectFragment4ButtonState();
                setImgState(false, false, false, false,
                        true, false, false, false, false);
                break;
            case R.id.ll_order:
                selectFragment5ButtonState();
                setImgState(false, false, false, false,
                        false, true, false, false, false);
                break;
            case R.id.ll_conpous:
                selectFragment6ButtonState();
                setImgState(false, false, false, false,
                        false, false, true, false, false);
                break;
            case R.id.ll_chat:
                selectFragment7ButtonState();
                setImgState(false, false, false, false,
                        false, false, false, true, false);
                break;
            case R.id.ll_store:
                selectFragment8ButtonState();
                setImgState(false, false, false, false,
                        false, false, false, false, true);
                break;
        }
    }


    public void setImgState(boolean service, boolean customer, boolean employee, boolean book,
                            boolean menu, boolean order, boolean conpous, boolean chat, boolean store) {
        img_service.setSelected(service);
        tv_service.setSelected(service);
        img_customer.setSelected(customer);
        tv_customer.setSelected(customer);
        img_employee.setSelected(employee);
        tv_employee.setSelected(employee);
        img_book.setSelected(book);
        tv_book.setSelected(book);
        img_menu.setSelected(menu);
        tv_menu.setSelected(menu);
        img_order.setSelected(order);
        tv_order.setSelected(order);
        img_conpous.setSelected(conpous);
        tv_conpous.setSelected(conpous);
        img_chat.setSelected(chat);
        tv_chat.setSelected(chat);
        img_store.setSelected(store);
        tv_store.setSelected(store);
    }

    public void setPreformClick(int visible) {
        img_more.setVisibility(visible);
        tv_service.setVisibility(visible);
        tv_customer.setVisibility(visible);
        tv_employee.setVisibility(visible);
        tv_book.setVisibility(visible);
        tv_menu.setVisibility(visible);
        tv_order.setVisibility(visible);
        tv_conpous.setVisibility(visible);
        tv_chat.setVisibility(visible);
        tv_store.setVisibility(visible);
    }

    public Fragment serviceFragment;
    public Fragment bookFragment;
    public Fragment cardFragment;
    public Fragment cardDetailFragment;
    public Fragment cardConsumeFragment;
    public Fragment orderFragment;
    public Fragment menuFragment;
    public Fragment shoppingCartFragment;
    public CustomerFragment customerFragment;
    public Fragment shiftFragment;
    public AddEditCustomerFragment addcustomerFragment;
    public Fragment editBookFragment;
    public Fragment addBookFragment;
    public Fragment checkBookFragment;
    public Fragment marketFragment;

    private void hideFragment(FragmentTransaction transaction) {
        if (serviceFragment != null) {
            transaction.hide(serviceFragment);
        }

        if (bookFragment != null) {
            transaction.hide(bookFragment);
        }

        if (cardFragment != null) {
            transaction.hide(cardFragment);
        }

        if (cardDetailFragment != null) {
            transaction.hide(cardDetailFragment);
        }


        if (cardConsumeFragment != null) {
            transaction.hide(cardConsumeFragment);
        }

        if (menuFragment != null) {
            transaction.hide(menuFragment);
        }
        if (customerFragment != null) {
            transaction.hide(customerFragment);
        }

        if (orderFragment != null) {
            transaction.hide(orderFragment);

        }
        if (shoppingCartFragment != null) {
            transaction.hide(shoppingCartFragment);

        }
        if (shiftFragment != null) {
            transaction.hide(shiftFragment);

        }
        if (addcustomerFragment != null) {
            transaction.hide(addcustomerFragment);

        }
        if (staffManagerFragment != null) {
            transaction.hide(staffManagerFragment);
        }
        if (staffFragment != null) {
            transaction.hide(staffFragment);
        }

        if (editBookFragment != null) {
            transaction.hide(editBookFragment);
        }

        if (addBookFragment != null) {
            transaction.hide(addBookFragment);
        }
        if (addServiceFragment != null) {
            transaction.hide(addServiceFragment);

        }

        if (checkBookFragment != null) {
            transaction.hide(checkBookFragment);
        }
        if (bookedServiceFragment != null) {
            transaction.hide(bookedServiceFragment);
        }

        if(marketFragment != null){
            transaction.hide(marketFragment);
        }

    }

    private void selectFragmnetWithParams(int index, Object object) {
        FragmentTransaction transaction = magager.beginTransaction();
        hideFragment(transaction);
        Bundle bundle = null;

        switch (index) {
            case 0://卡券详情页面
                if (cardDetailFragment == null) {
                    cardDetailFragment = new CardDetailFragment();
                    transaction.addToBackStack(null).add(R.id.container, cardDetailFragment);
                    stackFragments.add(cardDetailFragment);
                } else {
                    transaction.show(cardDetailFragment);
                }
                bundle = new Bundle();
                bundle.putSerializable("data", (CardBean.DataBean) object);
                cardDetailFragment.setArguments(bundle);

                break;
            case 1://消费记录页面
                if (cardConsumeFragment == null) {
                    cardConsumeFragment = new CardConsumeFragment();
                    transaction.addToBackStack(null).add(R.id.container, cardConsumeFragment);
                    stackFragments.add(cardConsumeFragment);
                } else {
                    transaction.show(cardConsumeFragment);
                }

                break;
            case 2:
                if (shoppingCartFragment == null) {
                    shoppingCartFragment = new ShoppingCartFragment();
                    transaction.addToBackStack(null).add(R.id.container, shoppingCartFragment);
                    stackFragments.add(shoppingCartFragment);
                } else {
                    transaction.show(shoppingCartFragment);
                }
                break;
            case 3:
                if (shiftFragment == null) {
                    shiftFragment = new ShiftFragment();
                    transaction.addToBackStack(null).add(R.id.container, shiftFragment);
                    stackFragments.add(shiftFragment);
                } else {
                    transaction.show(shiftFragment);
                }
                break;
            case 4:
                if (addcustomerFragment == null) {
                    addcustomerFragment = new AddEditCustomerFragment();
                    transaction.addToBackStack(null).add(R.id.container, addcustomerFragment);
                    stackFragments.add(addcustomerFragment);
                } else {
                    transaction.show(addcustomerFragment);
                }
                bundle = new Bundle();
                if (object != null) {
                    bundle.putSerializable("data", (CustomerBean.DataBean) object);
                    bundle.putInt("type", 1);
                } else {
                    bundle.putInt("type", 0);

                }
                addcustomerFragment.setArguments(bundle);
                break;
            case 5://修改预约
                if (editBookFragment == null) {
                    editBookFragment = new EditBookFragment();
                    transaction.addToBackStack(null).add(R.id.container, editBookFragment);
                    stackFragments.add(editBookFragment);
                } else {
                    transaction.show(editBookFragment);
                }
                bundle = new Bundle();
                bundle.putSerializable("data", (BookBean.DataBean) object);
                editBookFragment.setArguments(bundle);
                break;
            case 6://添加预约
                if (addBookFragment == null) {
                    addBookFragment = new EditBookFragment();
                    transaction.addToBackStack(null).add(R.id.container, addBookFragment);
                    stackFragments.add(addBookFragment);
                } else {
                    transaction.show(addBookFragment);
                }
                break;
            case 7:

                break;
            case 8:
                if (addServiceFragment == null) {
                    addServiceFragment = new AddEditServiceFragment();
                    transaction.addToBackStack(null).add(R.id.container, addServiceFragment);
                    stackFragments.add(addServiceFragment);
                } else {
                    transaction.show(addServiceFragment);
                }
                bundle = new Bundle();
                if (object!=null){
                    bundle.putSerializable("data", (ServiceBean.DataBean) object);
                    bundle.putInt("type",1);
                }else{
                    bundle.putInt("type",0);
                }
                addServiceFragment.setArguments(bundle);
                break;
            case 9://查看预约
                if (checkBookFragment == null) {
                    checkBookFragment = new CheckBookFragment();
                    transaction.addToBackStack(null).add(R.id.container, checkBookFragment);
                    stackFragments.add(checkBookFragment);
                } else {
                    transaction.show(checkBookFragment);
                }
                break;
            case 10://已预约服务
                if (bookedServiceFragment == null) {
                    bookedServiceFragment = new BookedServiceFragment();
                    transaction.addToBackStack(null).add(R.id.container, bookedServiceFragment);
                    stackFragments.add(bookedServiceFragment);
                } else {
                    transaction.show(bookedServiceFragment);
                }
                break;
            case 11:
                if (addStaffFragment==null){
                    addStaffFragment =  new AddStaffFragment();
                    transaction.addToBackStack(null).add(R.id.container, addStaffFragment);
                    stackFragments.add(addStaffFragment);
                }else {
                    transaction.show(addStaffFragment);
                }

                bundle = new Bundle();
                if (object != null) {
                    bundle.putSerializable("data", (StaffManagerBean.DataBean) object);
                    bundle.putInt("type", 1);
                } else {
                    bundle.putInt("type", 0);

                }
                addStaffFragment.setArguments(bundle);
                break;
            case 12:
                if (serviceSearchResultFragment==null){
                    serviceSearchResultFragment  =new ServiceSearchResultFragment();
                    transaction.addToBackStack(null).add(R.id.container, serviceSearchResultFragment);
                    stackFragments.add(serviceSearchResultFragment);
                }else {
                    transaction.show(serviceSearchResultFragment);
                }
                break;
            case 13:
                if (serviceSearchResultFragment==null){
                    userAgreementFragment  =new UserAgreementFragment();
                    transaction.addToBackStack(null).add(R.id.container, userAgreementFragment);
                    stackFragments.add(userAgreementFragment);
                }else {
                    transaction.show(userAgreementFragment);
                }
                break;


        }

        transaction.commit();
    }

    BookedServiceFragment bookedServiceFragment;

    private void resetAllFragments() {
        cardDetailFragment = null;
        cardConsumeFragment = null;
        shoppingCartFragment = null;
        shiftFragment = null;
        addcustomerFragment = null;
        editBookFragment = null;
        addBookFragment = null;
        addServiceFragment = null;
        checkBookFragment = null;
        bookedServiceFragment = null;
        addStaffFragment=null;
        serviceSearchResultFragment=null;
        userAgreementFragment=null;
    }

    private void resetSelectFragmentNUll(Fragment fragment) {


        if (fragment instanceof CardDetailFragment) {
            cardDetailFragment = null;
        } else if (fragment instanceof CardConsumeFragment) {
            cardConsumeFragment = null;
        } else if (fragment instanceof ShoppingCartFragment) {
            shoppingCartFragment = null;
        } else if (fragment instanceof ShiftFragment) {
            shiftFragment = null;
        } else if (fragment instanceof AddEditCustomerFragment) {
            addcustomerFragment = null;
        } else if (fragment instanceof EditBookFragment) {
            editBookFragment = null;
            addBookFragment = null;
        } else if (fragment instanceof AddEditServiceFragment) {

            addServiceFragment = null;
        } else if (fragment instanceof CheckBookFragment) {
            checkBookFragment = null;
        } else if (fragment instanceof BookedServiceFragment) {

            bookedServiceFragment = null;
        }else if (fragment instanceof  AddStaffFragment){
            addStaffFragment=null;

        }else if (fragment instanceof  ServiceSearchResultFragment){
            serviceSearchResultFragment=null;
        }else if (fragment instanceof  UserAgreementFragment){
            userAgreementFragment=null;
        }


    }

    private  int  currentPosition;

    public void selectFragment(int index) {
        popAllFragment();
       currentPosition =index;
        FragmentTransaction transaction = magager.beginTransaction();
        hideFragment(transaction);
        switch (index) {
            case 0:
                if (serviceFragment == null) {
                    serviceFragment = new ServiceFragment();
                    transaction.add(R.id.container, serviceFragment);
                } else {
                    transaction.show(serviceFragment);
                }
                break;
            case 1:
                if (customerFragment == null) {
                    customerFragment = new CustomerFragment();
                    transaction.add(R.id.container, customerFragment);
                } else {
                    transaction.show(customerFragment);
                }
                break;
            case 2:
//                if (userBean!=null) {
//                    if (userBean.getType() == 1) {//店长
                        if (staffManagerFragment == null) {
                            staffManagerFragment = new StaffManagerFragment();
                            transaction.add(R.id.container, staffManagerFragment);
                        } else {
                            transaction.show(staffManagerFragment);
                        }
//
//                }else{//员工
//
//                    if (staffFragment == null) {
//                        staffFragment = new StaffFragment();
//                        transaction.add(R.id.container, staffFragment);
//                    } else {
//                        transaction.show(staffFragment);
//                    }
//                }
//                }
                break;
            case 3:
                if (bookFragment == null) {
                    bookFragment = new BookFragment();
                    transaction.add(R.id.container, bookFragment);
                } else {
                    transaction.show(bookFragment);
                }
                break;
            case 4:
                if (menuFragment == null) {
                    menuFragment = new MenuFragment();
                    transaction.add(R.id.container, menuFragment);
                } else {
                    transaction.show(menuFragment);
                }
                break;
            case 5:
                if (orderFragment == null) {
                    orderFragment = new OrderFragment();
                    transaction.add(R.id.container, orderFragment);
                } else {
                    transaction.show(orderFragment);
                }
                break;
            case 6:
                if (cardFragment == null) {
                    cardFragment = new CardFragment();
                    transaction.add(R.id.container, cardFragment);
                } else {
                    transaction.show(cardFragment);
                }
                break;
            case 8:
                if (marketFragment == null) {
                    marketFragment = new MarketFragment();
                    transaction.add(R.id.container, marketFragment);
                } else {
                    transaction.show(marketFragment);
                }
                break;
            case 20:

        }
        transaction.commit();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (stackFragments.size()>0){
                popBackFragment(-1);
            }else{
                finish();
            }
        }
        return false;
    }


    public int getRightWidth() {

        return getResources().getDisplayMetrics().widthPixels - main_left.getWidth();
    }

    public void gotoShoppingCart() {
        selectFragmnetWithParams(2, null);
    }

    public void gotoShift() {
        selectFragmnetWithParams(3, null);
    }

    public void gotoCustomeradd() {
        selectFragmnetWithParams(4, null);
    }

    public void gotoServiceEdit(ServiceBean.DataBean serviceBean) {
        selectFragmnetWithParams(8, serviceBean);
    }

    public void gotoServiceAdd() {
        selectFragmnetWithParams(8, null);
    }

    public void gotoCustomerEdit(CustomerBean.DataBean bean) {
        selectFragmnetWithParams(4, bean);
    }

    public void gotoBookedService() {
        selectFragmnetWithParams(10, null);
    }
    public void gotoStaffDetail(StaffManagerBean.DataBean staffManagerBean) {
        selectFragmnetWithParams(11, staffManagerBean);
    }

    public void gotoSearchSearchResultFragment() {//
        selectFragmnetWithParams(12, null);
    }

    public void gotoUserAgreement() {
        selectFragmnetWithParams(13, null);
    }
    List<Fragment> stackFragments = new ArrayList<>();


    /**
     * @param selectIndex       需要重新选择的位置 -1不切换
     *
     */
    public void popBackFragment(int selectIndex) {
        if (stackFragments.size() < 1)
            return;
        resetSelectFragmentNUll(stackFragments.get(stackFragments.size() - 1));
        stackFragments.remove(stackFragments.size() - 1);
        getSupportFragmentManager().popBackStack();
        if (selectIndex >= 0) {
            selectFragment(selectIndex);
        }else{
            if (stackFragments.size()==0){
                selectFragment(currentPosition);
            }
        }



    }

    private void popAllFragment() {
        if (stackFragments.size() == 0)
            return;
        for (int i = 0; i < stackFragments.size(); i++) {
            getSupportFragmentManager().popBackStack();
        }
        stackFragments.clear();

        resetAllFragments();
    }



}
