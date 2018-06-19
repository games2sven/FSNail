package com.jlkf.fsnail.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.CheckBookAdapter;
import com.jlkf.fsnail.adapter.ServiceAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.Cell;
import com.jlkf.fsnail.bean.CheckBookBean;
import com.jlkf.fsnail.bean.ColTitle;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.RowTitle;
import com.jlkf.fsnail.bean.ServiceBean;
import com.jlkf.fsnail.constants.UrlConstants;
import com.jlkf.fsnail.dialog.DialogChooseDate;
import com.jlkf.fsnail.net.MyHttpCallback;
import com.jlkf.fsnail.net.OKHttpUtils;
import com.jlkf.fsnail.widget.excelpanel.ExcelPanel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class CheckBookFragment extends BaseFragment implements ExcelPanel.OnLoadMoreListener,View.OnClickListener{

    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String WEEK_FORMAT_PATTERN = "EEEE";
    public static final String[] CHANNEL = {"途牛", "携程", "艺龙", "去哪儿", "其他"};
    public static final String[] NAME = {"刘亦菲", "迪丽热巴", "柳岩", "范冰冰", "唐嫣", "杨幂", "刘诗诗"};
    public static final long ONE_DAY = 24 * 3600 * 1000;

    public static final int ROW_SIZE = 20;

    public static final String [] TIME = {"09:00","09:30","10:00","10:30","11:00","11:30",
            "12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30"
            ,"17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30"
            ,"22:00","22:30","23:00","23:30","00:00"};
//    public static final int PAGE_SIZE = 14;

    private ExcelPanel excelPanel;
    private ProgressBar progress;
    private CheckBookAdapter adapter;
    private List<RowTitle> rowTitles;
    private List<ColTitle> colTitles;
    private List<List<Cell>> cells;
    private SimpleDateFormat dateFormatPattern;
    private SimpleDateFormat weekFormatPattern;
    private boolean isLoading;
    private long historyStartTime;
    private long moreStartTime;
    private TextView tv_date;

    List<CheckBookBean.DataBean> mDatas =new ArrayList<>();
    RecyclerView recyclerView;
    MainActivity activity;
    //标题:
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        View view  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_check_book,null);

        progress = (ProgressBar) view.findViewById(R.id.progress);
        excelPanel = (ExcelPanel) view.findViewById(R.id.content_container);
        tv_date = (TextView)view.findViewById(R.id.tv_date);
        adapter = new CheckBookAdapter(getActivity(), blockListener);
        excelPanel.setAdapter(adapter);
        excelPanel.setOnLoadMoreListener(this);
        tv_date.setOnClickListener(this);
        initData();
        return view;
    }

    private View.OnClickListener blockListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cell cell = (Cell) view.getTag();
            showPopupWindow(view);
//            if (cell != null) {
//                if (cell.getStatus() == 0) {
//                    Toast.makeText(getActivity(), "空房", Toast.LENGTH_SHORT).show();
//                } else if (cell.getStatus() == 1) {
//                    Toast.makeText(getActivity(), "已离店，离店人:" + cell.getBookingName(), Toast.LENGTH_SHORT).show();
//                } else if (cell.getStatus() == 2) {
//                    Toast.makeText(getActivity(), "入住中，离店人:" + cell.getBookingName(), Toast.LENGTH_SHORT).show();
//                } else if (cell.getStatus() == 3) {
//                    Toast.makeText(getActivity(), "预定中，离店人:" + cell.getBookingName(), Toast.LENGTH_SHORT).show();
//                }
//            }
        }
    };

    private void initData() {
        moreStartTime = Calendar.getInstance().getTimeInMillis();
        historyStartTime = moreStartTime - ONE_DAY * TIME.length;
        dateFormatPattern = new SimpleDateFormat(DATE_FORMAT_PATTERN);
        weekFormatPattern = new SimpleDateFormat(WEEK_FORMAT_PATTERN);
        rowTitles = new ArrayList<>();
        colTitles = new ArrayList<>();
        cells = new ArrayList<>();
        for (int i = 0; i < TIME.length; i++) {
            cells.add(new ArrayList<Cell>());
        }
        loadData(moreStartTime, false);
    }

    private void loadData(long startTime, final boolean history) {

        Map<String,String> params = new HashMap<>();
//        params.put("optime",);

        OKHttpUtils.getIntance().oKHttpPost(UrlConstants.CHECK_BOOK, this, params, new MyHttpCallback<CheckBookBean>() {

            @Override
            public void onSuccess(CheckBookBean response) {
                Log.i("Sven","response "+response.getCode());
                if(response.getCode() == 200){
                    mDatas = response.getData();
//                    progress.setVisibility(View.GONE);
                    //模拟网络加载
                    isLoading = true;
                     Message message = new Message();
                    message.arg1 = history ? 1 : 2;
                    loadDataHandler.sendMessageDelayed(message, 1000);

                }
            }

            @Override
            public void onFailure(String errorMsg) {
                progress.setVisibility(View.GONE);
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


    private Handler loadDataHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isLoading = false;

            for(int i = 0;i<5;i++){
                CheckBookBean.DataBean dataBean = new  CheckBookBean.DataBean();
                dataBean.setCustomer_name("Sven"+i);
                mDatas.add(dataBean);
            }

            if (colTitles.size() == 0) {
                colTitles.addAll(genColData());
            }



            List<List<Cell>> cells1 = genCellData();

            for (int i = 0; i < cells1.size(); i++) {
                cells.get(i).addAll(cells1.get(i));
            }

            progress.setVisibility(View.GONE);
            adapter.setAllData(colTitles, mDatas, cells);
            adapter.disableFooter();
            adapter.disableHeader();
//            if (msg.arg1 == 1) {//history
//                historyStartTime -= ONE_DAY * TIME.length;
//                rowTitles.addAll(0, rowTitles1);
//                for (int i = 0; i < cells1.size(); i++) {
//                    cells.get(i).addAll(0, cells1.get(i));
//                }
//                //加载了数据之后偏移到上一个位置去
//                if (excelPanel != null) {
//                    excelPanel.addHistorySize(TIME.length);
//                }
//            } else {
//                moreStartTime += ONE_DAY * TIME.length;
//                rowTitles.addAll(rowTitles1);
//                for (int i = 0; i < cells1.size(); i++) {
//                    cells.get(i).addAll(cells1.get(i));
//                }
//            }

        }
    };

    @Override
    public void onLoadMore() {
        if (!isLoading) {
            loadData(moreStartTime, false);
        }
    }

    @Override
    public void onLoadHistory() {
        if (!isLoading) {
            loadData(historyStartTime, true);
        }
    }

    //====================================模拟生成数据==========================================
    private List<RowTitle> genRowData(long startTime) {
        List<RowTitle> rowTitles = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < TIME.length; i++) {
            RowTitle rowTitle = new RowTitle();
            rowTitle.setAvailableRoomCount(random.nextInt(10) + 10);
            rowTitle.setDateString(dateFormatPattern.format(startTime + i * ONE_DAY));
            rowTitle.setWeekString(weekFormatPattern.format(startTime + i * ONE_DAY));
            rowTitles.add(rowTitle);
        }
        return rowTitles;
    }

    private List<ColTitle> genColData() {
        List<ColTitle> colTitles = new ArrayList<>();
        for (int i = 0; i < TIME.length; i++) {
            ColTitle colTitle = new ColTitle();
            colTitle.setTime(TIME[i]);
            colTitles.add(colTitle);
        }
        return colTitles;
    }

    private List<List<Cell>> genCellData() {
        List<List<Cell>> cells = new ArrayList<>();
        for (int i = 0; i < TIME.length; i++) {
            List<Cell> cellList = new ArrayList<>();
            cells.add(cellList);
            for (int j = 0; j < mDatas.size(); j++) {
                Cell cell = new Cell();
                Random random = new Random();
                int number = random.nextInt(6);
                if (number == 1 || number == 2 || number == 3) {
                    cell.setStatus(number);
                    cell.setChannelName(CHANNEL[random.nextInt(CHANNEL.length)]);
                    cell.setBookingName(NAME[random.nextInt(NAME.length)]);
                } else {
                    cell.setStatus(0);
                }
                cellList.add(cell);
            }
        }
        return cells;
    }

    PopupWindow mPopWindow;
    private void showPopupWindow(View view) {
        //设置contentView
        if(mPopWindow != null && mPopWindow.isShowing()){
            mPopWindow.dismiss();
        }

        View contentView = LayoutInflater.from(getActivity()).inflate(R.layout.popuplayout, null);
        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPopWindow.dismiss();
            }
        });
        mPopWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        mPopWindow.setContentView(contentView);
        //设置各个控件的点击响应
        mPopWindow.showAsDropDown(view,view.getWidth(),-view.getHeight());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_date:
                DialogChooseDate dialogChooseDate = new DialogChooseDate(getActivity(), activity.getRightWidth(), new DialogChooseDate.Dialogcallback() {
                    @Override
                    public void pickWeightResult(String date) {
                        tv_date.setText(date);
                    }
                });
                break;
        }
    }
}
