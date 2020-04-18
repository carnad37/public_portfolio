package com.shop.admin.dto;

public class Comment {

	private int ans_id;
	private String ans_date;
	private String ans_text;
	private int ans_exist;
	private int que_id;
	
	public int getAns_id() {
		return ans_id;
	}
	public void setAns_id(int ans_id) {
		this.ans_id = ans_id;
	}
	public String getAns_date() {
		return ans_date;
	}
	public void setAns_date(String ans_date) {
		this.ans_date = ans_date;
	}
	public String getAns_text() {
		return ans_text;
	}
	public void setAns_text(String ans_text) {
		this.ans_text = ans_text;
	}
	public int getAns_exist() {
		return ans_exist;
	}
	public void setAns_exist(int ans_exist) {
		this.ans_exist = ans_exist;
	}
	public int getQue_id() {
		return que_id;
	}
	public void setQue_id(int que_id) {
		this.que_id = que_id;
	}
	
	
	
}
