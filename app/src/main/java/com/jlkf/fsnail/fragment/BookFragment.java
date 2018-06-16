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

    private BookAdapter adapter;

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

        switch (eventCenter.getEventCode()){
            case Constants.CODE_CANCEL_BOOK:
                loadData();
                break;
            case Constants.CODE_ADD_BOOK_SUCCESS:
                loadData();
                break;
        }
    }

    public void loadData(){
        Map<String, String> params = new HashMap<>();
        OKHttpUtils.getIntance().oKHttpGet(UrlConstants.BOOK_LIST, this,  new MyHttpCallback<BookBean>() {

            @Override
            public void onSuccess(BookBean response) {
                Log.i("Sven","code::::  "+response.getCode());
                if(response.getCode() == 200){
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
        recylerview.setLayoutManager(new GridLayoutManager(getActivity(),1));
        adapter = new BookAdapter(mDatas);
        recylerview.setAdapter(adapter);
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
