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
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * CSD_App
 * Created by sweet_xue on 16/4/6.
 */
public class TaxAdapter extends BaseAdapter {
    public static final String TAG = TaxAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<SelectOrderBean> list;
    private LayoutInflater inflater;
    private boolean[] state;
    private InvoiceSumListener listener;

    private static HashMap<Integer,Boolean> isSelected;


    public TaxAdapter(Context context, ArrayList<SelectOrderBean> list) {
        this.context = context;
        this.list = list;
        state = new boolean[list.size()];


//        isSelected = new HashMap<Integer,Boolean>();
//        //初始化数据
//        initData();
    }

    private void initData() {
        for (int i =0;i<list.size();i++){
            getIsSelected().put(i,false);
        }
    }

    public static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        TaxAdapter.isSelected = isSelected;
    }

    public InvoiceSumListener getListener() {
        return listener;
    }

    public void setListener(InvoiceSumListener listener) {
        this.listener = listener;
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
        viewHolder.checkBox_item.setOnCheckedChangeListener(new ItemOnCheckChangedListener(position, state));
        viewHolder.checkBox_item.setChecked(state[position]);
        return convertView;
    }

    public interface InvoiceSumListener {
        public void canSum(ArrayList<Integer> ids);
    }

    private class ItemOnCheckChangedListener implements CompoundButton.OnCheckedChangeListener {

        private int id;
        private boolean[] state;
        ArrayList<Integer> ids;

        public ItemOnCheckChangedListener(int id, boolean[] state) {
            this.id = id;
            this.state = state;

        }

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            state[id] = isChecked;
            ids = new ArrayList<Integer>();
            for (int i = 0; i < state.length; i++) {
                if (state[i]) {
                    ids.add(i);
                }
            }
            Log.i(TAG, this.id + ":" + ids.toString());
            listener.canSum(ids);
        }
    }


    private class ViewHolder {
        TextView textView_ordercode,
                textView_ordermoney;
        CheckBox checkBox_item;
        LinearLayout container_selectOrder;
    }
}
