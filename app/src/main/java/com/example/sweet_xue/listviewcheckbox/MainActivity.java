package com.example.sweet_xue.listviewcheckbox;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    private ListView listView_orderlist;

    private TextView textView_totalmoney;

    private static boolean isScroll = false;

    private String add = "2";

    ArrayList<SelectOrderBean> selectOrderBeans;

    private String codes;

    StringBuilder sb = new StringBuilder();


    private AbsListView.OnScrollListener onScrollListener = new AbsListView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(AbsListView view, int scrollState) {
            switch (scrollState) {
                case SCROLL_STATE_FLING://滚动状态，计算结果暂停
                    isScroll = true;
                    break;
                case SCROLL_STATE_IDLE://已经停止滚动，可以计算
                    isScroll = false;
                    break;
                case SCROLL_STATE_TOUCH_SCROLL:
                    isScroll = true;
                default:
                    break;
            }
        }

        @Override
        public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

        }
    };

    CheckBox checkBox_together;

    Button btn_finishSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        listView_orderlist = (ListView) findViewById(R.id.listView_orderlist);
        textView_totalmoney = (TextView) findViewById(R.id.textView_totalmoney);

        checkBox_together = (CheckBox) findViewById(R.id.checkBox_together);
        btn_finishSelect = (Button) findViewById(R.id.btn_finishSelect);

        checkBox_together.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    add = "1";
                } else {
                    add = "3";
                }
            }
        });

        listView_orderlist.setOnScrollListener(onScrollListener);

        selectOrderBeans = new ArrayList<SelectOrderBean>();
        for (int i = 0; i < 30; i++) {
            SelectOrderBean selectOrderBean = new SelectOrderBean();
            selectOrderBean.setOrder_no("WC-222");
            selectOrderBean.setInvo_amount("" + 280);
            selectOrderBeans.add(selectOrderBean);
        }
        TaxAdapter adapter = new TaxAdapter(MainActivity.this, selectOrderBeans);


        adapter.setListener(new TaxAdapter.InvoiceSumListener() {
            @Override
            public void canSum(ArrayList<Integer> ids) {
                sumInvocesByIds(ids);
            }
        });
        listView_orderlist.setAdapter(adapter);


    }

    private void sumInvocesByIds(ArrayList<Integer> ids) {
        if (isScroll) {
            return;
        }
        int sum = 0;
        for (int id : ids) {
            String item = selectOrderBeans.get(id).getInvo_amount();
            int intItem = Integer.parseInt(item);
            sum += intItem;

            String order_no = selectOrderBeans.get(id).getOrder_no();
            sb.append(order_no + ",");
        }

        codes = new String(sb);
//        Toast.makeText(this, sum + "", Toast.LENGTH_SHORT).show();
        textView_totalmoney.setText(sum + "");
    }
}
