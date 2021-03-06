package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.CardAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.Card2Bean;
import com.jlkf.fsnail.bean.CardBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.DialogChooseDate;
import com.jlkf.fsnail.dialog.SearchCardDialog;
import com.jlkf.fsnail.dialog.SingleFunctionPop;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.widget.PageIndexView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class CardFragment extends BaseFragment{

    List<CardBean.DataBean> mDatas =new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;
    @Bind(R.id.page_index_view)
    PageIndexView page_index_view;

    private int  pageNo=1;
    private int  pageSize=6;

    Map<String,String> mParams;
    private CardAdapter adapter;

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        View view  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_conpous,null);
        ButterKnife.bind(this,view);

        loadData();
        return view;
    }

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        Object object = eventCenter.getData();
        if(eventCenter != null){
            switch (eventCenter.getEventCode()){
                case Constants.CODE_CARD_SELECT_DATE://弹出选择日期
                    DialogChooseDate dialogChooseDate = new DialogChooseDate(getActivity(),((MainActivity) getActivity()).getRightWidth(), new DialogChooseDate.Dialogcallback() {
                        @Override
                        public void pickWeightResult(String date) {

                        }
                    });
                    break;
                case Constants.CODE_SEARCH_CARD:
                    mParams = ((Map<String,String>)object);
                    loadData();
                    break;

            }
        }
    }

    public void loadData(){
        if(mParams == null){
            mParams = new HashMap<String,String>();
        }
        mParams.put("pageNo",pageNo+"");
        mParams.put("pageSize",pageSize+"");
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.CARD_LIST, this, mParams, new MyHttpCallback<CardBean>() {
            @Override
            public void onSuccess(CardBean response) {
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

        recylerview.setLayoutManager(new GridLayoutManager(getActivity(),1));
        adapter = new CardAdapter(mDatas);
        recylerview.setAdapter(adapter);
    }

    boolean isShowMore;
    @OnClick(R.id.iv_service_more)
    public   void  showMoreFunction(){
        isShowMore=!isShowMore;
        iv_service_more.setImageResource(isShowMore?R.mipmap.btn_more_red2:R.mipmap.btn_more_red1);
        if (isShowMore){
            SingleFunctionPop pop =new SingleFunctionPop(1,getActivity(),iv_service_more);
            pop.showPop();
            pop.setOnDismissListener(new SingleFunctionPop.OnDismissListener() {
                public void onDismiss() {
                    isShowMore=false;
                    iv_service_more.setImageResource(R.mipmap.btn_more_red1);
                }
            });
            pop.setOnMoreClickListener(new SingleFunctionPop.OnMoreClickListener() {
                @Override
                public void searchAll() {//搜索所有
                    showSearchDialog();
                }
            });
        }
    }

    private void showSearchDialog() {
        SearchCardDialog dialog = new SearchCardDialog(getActivity(), ((MainActivity) getActivity()).getRightWidth());
        dialog.showDiaglog();
    }

}
