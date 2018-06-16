package com.jlkf.fsnail.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.jlkf.fsnail.R;
import com.jlkf.fsnail.activity.MainActivity;
import com.jlkf.fsnail.adapter.CheckBookAdapter;
import com.jlkf.fsnail.base.BaseFragment;
import com.jlkf.fsnail.bean.Cell;
import com.jlkf.fsnail.bean.ColTitle;
import com.jlkf.fsnail.bean.CustomerBean;
import com.jlkf.fsnail.bean.EventCenter;
import com.jlkf.fsnail.bean.RowTitle;
import com.jlkf.fsnail.bean.StaffBean;
import com.jlkf.fsnail.bean.StaffManagerBean;
import com.jlkf.fsnail.bean.StaffTimeBean;
import com.jlkf.fsnail.constants.Constants;
import com.jlkf.fsnail.dialog.SelectStaffTimeDialog;
import com.jlkf.fsnail.dialog.StaffSetRestDialog;
import com.jlkf.fsnail.widget.excelpanel.ExcelPanel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.jlkf.fsnail.fragment.CheckBookFragment.ONE_DAY;

/**
 * Created by Administrator on 2018/5/28 0028. 轮班
 */

public class AddStaffFragment extends BaseFragment {
    MainActivity mainActivity;
  int  type=0;
    StaffManagerBean.DataBean customerBean;
  @Bind(R.id.custom_page_name)
    TextView custom_page_name;
  @Bind(R.id.checkBox1)
  CheckBox checkBox1;
  @Bind(R.id.checkBox2)
  CheckBox checkBox2;
  @Bind(R.id.checkBox3)
  CheckBox checkBox3;
  @Bind(R.id.checkBox4)
  CheckBox checkBox4;
  @Bind(R.id.checkBox5)
  CheckBox checkBox5;
  @Bind(R.id.checkBox6)
  CheckBox checkBox6;
  @Bind(R.id.checkBox7)
  CheckBox checkBox7;
  @Bind(R.id.checkBox8)
  CheckBox checkBox8;
  @Bind(R.id.checkBox9)
  CheckBox checkBox9;
  @Bind(R.id.checkBox10)
  CheckBox checkBox10;
  @Bind(R.id.checkBox11)
  CheckBox checkBox11;
  @Bind(R.id.checkBox12)
  CheckBox checkBox12;
  @Bind(R.id.checkBox13)
  CheckBox checkBox13;
  @Bind(R.id.checkBox14)
  CheckBox checkBox14;
  @Bind(R.id.checkBox15)
  CheckBox checkBox15;
  @Bind(R.id.checkBox16)
  CheckBox checkBox16;
  @Bind(R.id.content_container)
  ExcelPanel excelPanel;
    public static final String[] CHANNEL = {"途牛", "携程", "艺龙", "去哪儿", "其他"};
    public static final String[] NAME = {"刘亦菲", "迪丽热巴", "柳岩", "范冰冰", "唐嫣", "杨幂", "刘诗诗"};
    public static final String [] TIME = {"09:00","09:30","10:00","10:30","11:00","11:30",
            "12:00","12:30","13:00","13:30","14:00","14:30","15:00","15:30","16:00","16:30"
            ,"17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00"};
    private CheckBookAdapter adapter;
    @SuppressLint("HandlerLeak")
    private Handler loadDataHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            isLoading = false;
            long startTime = (Long) msg.obj;
            List<RowTitle> rowTitles1 = genRowData(startTime);
            List<List<Cell>> cells1 = genCellData();
            if (msg.arg1 == 1) {//history
                historyStartTime -= ONE_DAY * TIME.length;
                rowTitles.addAll(0, rowTitles1);
                for (int i = 0; i < cells1.size(); i++) {
                    cells.get(i).addAll(0, cells1.get(i));
                }

                //加载了数据之后偏移到上一个位置去
                if (excelPanel != null) {
                    excelPanel.addHistorySize(TIME.length);
                }
            } else {
                moreStartTime += ONE_DAY * TIME.length;
                rowTitles.addAll(rowTitles1);
                for (int i = 0; i < cells1.size(); i++) {
                    cells.get(i).addAll(cells1.get(i));
                }
            }
            if (colTitles.size() == 0) {
                colTitles.addAll(genColData());
            }
//            progress.setVisibility(View.GONE);
            adapter.setAllData(colTitles, rowTitles, cells);
            adapter.disableFooter();
            adapter.disableHeader();
        }
    };
    private boolean isLoading;
    private long moreStartTime;
    private long historyStartTime;
    private SimpleDateFormat dateFormatPattern;
    private SimpleDateFormat weekFormatPattern;

    @Override
    protected boolean isBindEventBusHere() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        int  code =eventCenter.getEventCode();
        switch (code){
            case Constants.CODE_SELETC_STAFF_TIME:
                List<StaffTimeBean> staffTimeBeans = (List<StaffTimeBean>) eventCenter.getData();
                timeBeans.clear();
                timeBeans.addAll(staffTimeBeans);

                initChart();
                break;
             case Constants.CODE_SELETC_CANCEL_TIME:

                 break;

        }


    }

    private void initChart() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootview  =LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_staff_add,null);
        ButterKnife.bind(this,rootview);
        mainActivity = (MainActivity) getActivity();
        Bundle bundle =  getArguments();
        if (bundle!=null){
           type =  bundle.getInt("type");
            customerBean = (StaffManagerBean.DataBean) bundle.getSerializable("data");
        }

        initView();

        initCheckBox();

        initData();
        return rootview ;
    }

    private void initCheckBox() {

        setDrawable(checkBox1);
        setDrawable(checkBox2);
        setDrawable(checkBox3);
        setDrawable(checkBox4);
        setDrawable(checkBox5);
        setDrawable(checkBox6);
        setDrawable(checkBox7);
        setDrawable(checkBox8);
        setDrawable(checkBox9);
        setDrawable(checkBox10);
        setDrawable(checkBox11);
        setDrawable(checkBox12);
        setDrawable(checkBox13);
        setDrawable(checkBox14);
        setDrawable(checkBox15);
        setDrawable(checkBox16);

        setCheckListener();
    }

    private void   setDrawable(CheckBox checkBox){
        Drawable drawable = this.getResources().getDrawable(R.drawable.checked_box_selector);
        drawable.setBounds(0,0, (int) getResources().getDimension(R.dimen.DIMEN_24PX), (int) getResources().getDimension(R.dimen.DIMEN_24PX));
        checkBox.setCompoundDrawables(drawable,null,null,null);
    }

    private void setCheckListener() {
        checkBox1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox9.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox11.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox12.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox13.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox14.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox15.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });
        checkBox16.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            }
        });


    }

    private void initView() {
         if (type==0){
             custom_page_name.setText("添加员工");
         }else{
             custom_page_name.setText("员工详情");
         }
        adapter = new CheckBookAdapter(getActivity(), blockListener);
        excelPanel.setAdapter(adapter);
        excelPanel.setOnLoadMoreListener(new ExcelPanel.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
//                if (!isLoading) {
//                    loadData(moreStartTime, false);
//                }
            }

            @Override
            public void onLoadHistory() {
//                if (!isLoading) {
//                    loadData(historyStartTime, true);
//                }
            }
        });

    }

    int  weekdays=7;
    public static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd";
    public static final String WEEK_FORMAT_PATTERN = "EEEE";
    private List<RowTitle> rowTitles;
    private List<ColTitle> colTitles;
    private List<List<Cell>> cells;
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


        timeBeans.add(new StaffTimeBean("星期一","9:00","21:00",true));
        timeBeans.add(new StaffTimeBean("星期二","9:00","21:00",true));
        timeBeans.add(new StaffTimeBean("星期三","9:00","21:00",true));
        timeBeans.add(new StaffTimeBean("星期四","9:00","21:00",true));
        timeBeans.add(new StaffTimeBean("星期五","9:00","21:00",true));
        timeBeans.add(new StaffTimeBean("星期六","9:00","21:00",true));
        timeBeans.add(new StaffTimeBean("星期天","9:00","21:00",true));
    }

    List<StaffTimeBean> timeBeans =new ArrayList<>();

    private void loadData(long startTime, final boolean history) {
        //模拟网络加载
        isLoading = true;
        Message message = new Message();
        message.arg1 = history ? 1 : 2;
        message.obj = new Long(startTime);
        loadDataHandler.sendMessageDelayed(message, 0);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @OnClick({R.id.tv_add_customer_ensure,R.id.tv_add_customer_cancel})
   public void  finishFragment(){
        mainActivity.popBackFragment(2);

   }

    //====================================模拟生成数据==========================================
    private List<RowTitle> genRowData(long startTime) {
        List<RowTitle> rowTitles = new ArrayList<>();
        for (int i = 0; i < weekdays; i++) {
            RowTitle rowTitle = new RowTitle();
            rowTitle.setAvailableRoomCount(weekdays);
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
            for (int j = 0; j <weekdays ; j++) {
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
    private View.OnClickListener blockListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Cell cell = (Cell) view.getTag();
            showPopupWindow(view);
        }
    };


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


    @OnClick({R.id.staff_add_week1,R.id.staff_add_week2,R.id.staff_add_week3,R.id.staff_add_week4})
    public  void showStaffTimeDialog(){
        SelectStaffTimeDialog dialog = new SelectStaffTimeDialog(mainActivity,timeBeans);
        dialog.showDialog();

    }


    @OnClick(R.id.staff_add_edit)
    public  void  showSetRestDialog(){
        StaffSetRestDialog staffSetRestDialog =new StaffSetRestDialog(mainActivity);
        staffSetRestDialog.showDialog();

    }

}
