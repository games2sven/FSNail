package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.ConsumeAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.ConsumeBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.widget.PageIndexView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;


public class CardConsumeFragment extends BaseFragment implements View.OnClickListener{

    List<ConsumeBean.DataBean> mDatas =new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    @Bind(R.id.text_total_money)
    TextView text_total_money;
    @Bind(R.id.tv_return)
    TextView tv_return;
    @Bind(R.id.page_index_view)
    PageIndexView page_index_view;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;

    private int  pageNo=1;
    private int  pageSize=6;

    String cardid;

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.card_consume,null);
        ButterKnife.bind(this,view);

        iv_service_more.setVisibility(View.GONE);

        Bundle bundle = getArguments();
        cardid = bundle.getString("cardId");
        loadData();
        initView();
        return view;
    }

    public void loadData(){
        Map<String,String> params = new HashMap<String,String>();
        if(TextUtils.isEmpty(cardid)){
            params.put("cardId",cardid);
        }

        params.put("pageNo",pageNo+"");
        params.put("pageSize",pageSize+"");
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.CARD_CONSUME, this, params, new MyHttpCallback<ConsumeBean>() {
            @Override
            public void onSuccess(ConsumeBean response) {
                if(response.getCode() == 200){
                    page_index_view.setTotalPage(response.getTotalPage());
                    page_index_view.setCurrentPage(pageNo);
                    mDatas = response.getData();
                    initRecyclerview();
                }
            }

            @Override
            public void onFailure(String errorMsg) {

            }
        });
    }


    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {

    }

    public void initView(){
        tv_return.setOnClickListener(this);
    }

    public void initRecyclerview(){
        page_index_view.setOnPageIndexListener(new PageIndexView.OnPageIndexListener() {
            @Override
            public void onLastClick() {
                pageNo--;
                if (pageNo>0){
                    loadData();
                }
            }

            @Override
            public void onNextClick() {
                pageNo++;
                loadData();
            }

            @Override
            public void onIndexClick(int page) {
                pageNo=page;
                page_index_view.setCurrentPage(page);
                loadData();
            }
        });

        recylerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        recylerview.setAdapter(new ConsumeAdapter(mDatas));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_return:
                ((MainActivity)getActivity()).popBackFragment(-1);
                ((MainActivity)getActivity()).cardConsumeFragment=null;
                break;
        }
    }
}
