package com.example.sweet_xue.listviewcheckbox;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.example.sweet_xue.listviewcheckbox.listview.SwipeMenu;
import com.example.sweet_xue.listviewcheckbox.listview.SwipeMenuCreator;
import com.example.sweet_xue.listviewcheckbox.listview.SwipeMenuItem;
import com.example.sweet_xue.listviewcheckbox.listview.SwipeMenuListView;
import com.example.sweet_xue.listviewcheckbox.listview.SwipeMenuListView.OnMenuItemClickListener;

public class Main2Activity extends AppCompatActivity implements OnClickListener {

    private ArrayList<SelectOrderBean> selectOrderBeans = null;

    CheckBox checkBox_together;

    Button btn_finishSelect;

    private SwipeMenuListView listView_orderlist;

    private TextView textView_totalmoney;

    private int checkNum; // 记录选中的条目数量

    TaxAdapter2 adapter2;

    private Button btn;

    private LinearLayout container;

    private int tag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main2);

        selectOrderBeans = new ArrayList<SelectOrderBean>();
        for (int i = 0; i < 30; i++) {
            SelectOrderBean selectOrderBean = new SelectOrderBean();
            selectOrderBean.setOrder_no("WC-222");
            selectOrderBean.setInvo_amount("" + 280);
            selectOrderBeans.add(selectOrderBean);
        }

        listView_orderlist = (SwipeMenuListView) findViewById(R.id.listView_orderlist);
        textView_totalmoney = (TextView) findViewById(R.id.textView_totalmoney);


        checkBox_together = (CheckBox) findViewById(R.id.checkBox_together);
        btn_finishSelect = (Button) findViewById(R.id.btn_finishSelect);


        adapter2 = new TaxAdapter2(Main2Activity.this, selectOrderBeans);
        listView_orderlist.setAdapter(adapter2);

        checkBox_together.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    for (int i = 0; i < selectOrderBeans.size(); i++) {
                        TaxAdapter2.getIsSelected().put(i, true);
                    }

                    checkNum = selectOrderBeans.size();
                    dataChanged();
                } else {
                    for (int i = 0; i < selectOrderBeans.size(); i++) {
                        if (TaxAdapter2.getIsSelected().get(i)) {
                            TaxAdapter2.getIsSelected().put(i, false);
                            checkNum--;
                        }
                    }

                    dataChanged();
                }
            }
        });


        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);
        container = (LinearLayout) findViewById(R.id.conainer_bottom);




        //滑动删除之添加删除按键
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem swipeMenuItem = new SwipeMenuItem(getApplicationContext());
                swipeMenuItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9, 0XCE)));
                swipeMenuItem.setWidth(dp2px(90));
                swipeMenuItem.setTitle("删除");
                swipeMenuItem.setTitleSize(19);
                swipeMenuItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(swipeMenuItem);

            }
        };

        listView_orderlist.setMenuCreator(creator);

        listView_orderlist.setOnMenuItemClickListener(new OnMenuItemClickListener() {
            @Override
            public void onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index){
                    case 0:

                        //通知后台删除相应的项
                        selectOrderBeans.remove(position);
                        adapter2.notifyDataSetInvalidated();

                        break;
                }
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
    // 刷新listview和TextView的显示
    private void dataChanged() {
        // 通知listView刷新
        adapter2.notifyDataSetInvalidated();
    }

    ;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn:

                if (tag == 0){

                    container.setVisibility(View.VISIBLE);
                    btn.setText("取消");
                    adapter2.setTAG(1);
                    dataChanged();

                    tag = 1;
                }else if (tag == 1){

                    container.setVisibility(View.GONE);
                    btn.setText("编辑");
                    adapter2.setTAG(0);
                    dataChanged();

                    tag = 0;
                }
                break;
        }
    }
}
