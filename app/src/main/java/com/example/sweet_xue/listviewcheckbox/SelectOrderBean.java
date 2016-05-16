package com.example.sweet_xue.listviewcheckbox;

public class SelectOrderBean {

	public static final int TYPE_CHECKED = 1;
	public static final int TYPE_NOCHECKED = 0;

	public int type;

	private String order_no;

	private String invo_amount;

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getInvo_amount() {
		return invo_amount;
	}

	public void setInvo_amount(String invo_amount) {
		this.invo_amount = invo_amount;
	}

}
