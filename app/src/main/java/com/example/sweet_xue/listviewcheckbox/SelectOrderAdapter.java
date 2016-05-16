package com.example.sweet_xue.listviewcheckbox;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;


public class SelectOrderAdapter extends BaseAdapter {
	
	
	List<SelectOrderBean> list = null;
	Context context;
	Integer money = 0;
	Integer money_l ;
	TextView moneyText;

	public SelectOrderAdapter(List<SelectOrderBean> list, Context context,TextView text) {
		super();
		this.list = list;
		this.context = context;
		this.moneyText = text;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated mepohod stub
		return position;
	}

	CheckBox checkBox_item;
	TextView textView_ordermoney;
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		convertView = LayoutInflater.from(context).inflate(R.layout.select_order_item, null);
		CheckBox mCheckBox = (CheckBox) convertView.findViewById(R.id.checkBox_item);
		TextView textView_ordercode = (TextView) convertView.findViewById(R.id.textView_ordercode);
		textView_ordermoney = (TextView) convertView.findViewById(R.id.textView_ordermoney);
		checkBox_item = (CheckBox) convertView.findViewById(R.id.checkBox_item);
		checkBox_item.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if(isChecked){
					String money1 = textView_ordermoney.getText().toString();
					money = money + Integer.parseInt(money1);
					moneyText.setText(money.toString());
				}else{
					String money2 = moneyText.getText().toString();
					String ordermoney = textView_ordermoney.getText().toString();
					money = Integer.parseInt(money2) - Integer.parseInt(ordermoney);
					moneyText.setText(money.toString());
				}
			}
		});
		
		return convertView;
	}
//	public int Money(){
//		return money;
//	}
//	interface TestTemplateCallBack{
//		Object doSomthing();
//	}
//	class Template2{
//		public void execute(TestTemplateCallBack callBack){
//			callBack.doSomthing();
//			final TextView textView = new TextView(context);
//			checkBox_item.setOnCheckedChangeListener(new OnCheckedChangeListener() {
//				
//				@Override
//				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//					if(isChecked){
//						String money1 = textView_ordermoney.getText().toString();
//						money = money + Integer.parseInt(money1);
//						textView.setText(money.toString());
//					}else{
//						String money2 = textView.getText().toString();
//						String ordermoney = textView_ordermoney.getText().toString();
//						money = Integer.parseInt(money2) - Integer.parseInt(ordermoney);
//						moneyText.setText(money.toString());
//					}
//				}
//			});
//		}
//	}
}
