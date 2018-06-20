package com.jlkf.fsnail.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.bean.BaseHttpBean;
import com.jlkf.fsnail.bean.BookBean;
import com.jlkf.fsnail.bean.Card2Bean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.holder.BookViewHolder;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.utils.UiUtil;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class BookAdapter extends RecyclerView.Adapter {

    private List<BookBean.DataBean> datas;

    public BookAdapter(List<BookBean.DataBean> mDatas) {
        this.datas = mDatas;
    }

    public void setDatas(List<BookBean.DataBean> mDatas){
        this.datas = mDatas;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book_recycel_view,null);
        return new BookViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((BookViewHolder)holder).img_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addBook2Service(datas.get(position).getId()+"");
            }
        });
        ((BookViewHolder)holder).img_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventBus.getDefault().post(new EventCenter(Constants.CODE_EDIT_BOOK, datas.get(position)));
            }
        });
        ((BookViewHolder)holder).tv_cancel_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cancelBook(datas.get(position).getId()+"");
            }
        });

        ((BookViewHolder)holder).tv_book_time.setText(datas.get(position).getAppointTime()+"");
        ((BookViewHolder)holder).tv_book_date.setText(datas.get(position).getOptime()+"");
        ((BookViewHolder)holder).tv_employee_nickName.setText(datas.get(position).getUName());
        ((BookViewHolder)holder).tv_customer_name.setText(datas.get(position).getCustomerName());
        ((BookViewHolder)holder).tv_customer_phone.setText(datas.get(position).getCustomerPhone());
        ((BookViewHolder)holder).tv_type.setText(datas.get(position).getType());
        ((BookViewHolder)holder).tv_service.setText(datas.get(position).getService());
        ((BookViewHolder)holder).tv_brand.setText(datas.get(position).getBrand());
        ((BookViewHolder)holder).tv_staus.setText(Constants.getStaus(datas.get(position).getStatus()));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public void cancelBook(String bookId){
        Map<String,String> params = new HashMap<>();
        params.put("id",bookId);
        params.put("status",5+"");

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.CANCEL_BOOK ,this, params,new MyHttpCallback<BaseHttpBean>() {
            @Override
            public void onSuccess(BaseHttpBean response) {

                if (response.getCode()==200){
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_CANCEL_BOOK));
                }else{
                    UiUtil.showToast(response.getMsg());
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                UiUtil.showToast(errorMsg);
            }
        });
    }

    public void addBook2Service(String id){
        Map<String,String> params = new HashMap<>();
        params.put("id",id);
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.ADD_BOOK_TO_SERVICE, this, params, new MyHttpCallback<BookBean>() {

            @Override
            public void onSuccess(BookBean response) {
                if(response.getCode() == 200){
                    UiUtil.showToast(R.string.tv_add_success);
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_UPDATE_SERVICE));
                    EventBus.getDefault().post(new EventCenter(Constants.ADD_BOOK_TO_SERVICE));
                }
            }

            @Override
            public void onFailure(String errorMsg) {
                UiUtil.showToast(errorMsg);
            }
        });
    }

}



