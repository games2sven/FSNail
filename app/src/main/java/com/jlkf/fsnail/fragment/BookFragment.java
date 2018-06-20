package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
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
import com.jlkf.fsnail.adapter.BookAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.BookBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.SearchBookDialog;
import com.jlkf.fsnail.dialog.TwoFunctionPop;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.widget.PageIndexView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class BookFragment extends BaseFragment{

    MainActivity mainActivity;
    List<BookBean.DataBean> mDatas =new ArrayList<>();

    @Bind(R.id.recylerview)
    RecyclerView recylerview;
    @Bind(R.id.iv_service_more)
    ImageView iv_service_more;
    @Bind(R.id.tv_check)
    TextView tv_check;
    @Bind(R.id.page_index_view)
    PageIndexView page_index_view;

    private BookAdapter adapter;
    private int  pageNo=1;
    private int  pageSize=6;

    private int status = -1;
    Map<String,String> mParams;

    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater,container,savedInstanceState);
        mainActivity= (MainActivity) getActivity();
        View view  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_book,null);
        ButterKnife.bind(this,view);

        initRecyclerview();
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
        switch (eventCenter.getEventCode()){
            case Constants.CODE_CANCEL_BOOK:
                pageNo = 1;
                loadData();
                break;
            case Constants.CODE_ADD_BOOK_SUCCESS:
                pageNo = 1;
                loadData();
                break;
            case Constants.CODE_SEARCH_BOOK:
                mParams = (Map<String,String>)object;
                loadData();
                break;
            case Constants.ADD_BOOK_TO_SERVICE:
                pageNo = 1;
                loadData();
                break;
        }
    }

    public void loadData(){
        if(mParams == null){
            mParams = new HashMap<>();
        }
        mParams.put("pageNo",pageNo+"");
        mParams.put("pageSize",pageSize+"");

        Log.i("Sven",OKHttpUtils.getMapParamStr(mParams));
        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.BOOK_LIST, this, mParams, new MyHttpCallback<BookBean>() {

            @Override
            public void onSuccess(BookBean response) {
                if(response.getCode() == 200){
                    page_index_view.setTotalPage(response.getTotalPage());
                    page_index_view.setCurrentPage(pageNo);
                    mDatas = response.getData();
                    adapter.setDatas(mDatas);
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
        adapter = new BookAdapter(mDatas);
        recylerview.setAdapter(adapter);
    }

    @OnClick(R.id.tv_check)
    public void checkBook(){
        EventBus.getDefault().post(new EventCenter(Constants.CODE_CHECK_BOOK));
    }

    boolean isShowMore;
    @OnClick(R.id.iv_service_more)
    public   void  showMoreFunction(){
        isShowMore=!isShowMore;
        iv_service_more.setImageResource(isShowMore ? R.mipmap.btn_more_red2 : R.mipmap.btn_more_red1);

        if (isShowMore) {
            TwoFunctionPop pop  =new  TwoFunctionPop(0,mainActivity,iv_service_more);
            pop.setOnDismissListener(new TwoFunctionPop.OnDismissListener() {
                public void onDismiss() {
                    isShowMore = false;
                    iv_service_more.setImageResource(R.mipmap.btn_more_red1);
                }
            });
            pop.setOnMoreClickListener(new TwoFunctionPop.OnMoreClickListener() {
                @Override
                public void add() {//添加
                    EventBus.getDefault().post(new EventCenter(Constants.CODE_ADD_BOOK));
                }

                @Override
                public void searchAll() {//搜
                    showSearchDialog();
                }
            });
            pop.showPop();
        }
    }

    private void showSearchDialog() {
        SearchBookDialog dialog = new SearchBookDialog(getActivity(), ((MainActivity) getActivity()).getRightWidth());
        dialog.showDiaglog();
    }

}
