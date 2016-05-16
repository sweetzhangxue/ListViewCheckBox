package com.example.sweet_xue.listviewcheckbox;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * CSD_App Created by sweet_xue on 16/4/6.
 */
public class TaxAdapter2 extends BaseAdapter {

    private Context context;

    private ArrayList<SelectOrderBean> list;

    private LayoutInflater inflater;

    private static HashMap<Integer, Boolean> isSelected;

    private int TAG;


    public TaxAdapter2(Context context, ArrayList<SelectOrderBean> list) {
        this.context = context;
        this.list = list;

        isSelected = new HashMap<Integer, Boolean>();
        //初始化数据
        initData();
    }

    public void setTAG(int TAG) {
        this.TAG = TAG;
    }

    private void initData() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
        }
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        TaxAdapter2.isSelected = isSelected;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            inflater = LayoutInflater.from(this.context);
            convertView = inflater.inflate(R.layout.select_order_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView_ordercode = (TextView) convertView.findViewById(R.id.textView_ordercode);
            viewHolder.textView_ordermoney = (TextView) convertView.findViewById(R.id.textView_ordermoney);
            viewHolder.checkBox_item = (CheckBox) convertView.findViewById(R.id.checkBox_item);
            viewHolder.container_selectOrder = (LinearLayout) convertView.findViewById(R.id.container_selectOrder);
            viewHolder.checkBox_item = (CheckBox) convertView.findViewById(R.id.checkBox_item);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final SelectOrderBean order = list.get(position);
        String orderCode = order.getOrder_no();
        viewHolder.textView_ordercode.setText(orderCode);
        String orderMoney = order.getInvo_amount();
        viewHolder.textView_ordermoney.setText(orderMoney);
        if (TAG == 1) {
            viewHolder.checkBox_item.setVisibility(View.VISIBLE);
        } else if (TAG == 0) {
            viewHolder.checkBox_item.setVisibility(View.GONE);
        }
        viewHolder.checkBox_item.setOnCheckedChangeListener(new ItemOnCheckChangedListner(position, getIsSelected()));
        viewHolder.checkBox_item.setChecked(getIsSelected().get(position));
        return convertView;
    }


    private class ItemOnCheckChangedListner implements OnCheckedChangeListener {

        HashMap<Integer, Boolean> isSelected;

        int id;

        public ItemOnCheckChangedListner(int position, HashMap<Integer, Boolean> isSelected) {
            this.isSelected = isSelected;
            this.id = position;
        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            getIsSelected().put(id, isChecked);
        }
    }

    private class ViewHolder {

        TextView textView_ordercode,
                textView_ordermoney;

        CheckBox checkBox_item;

        LinearLayout container_selectOrder;
    }
}
