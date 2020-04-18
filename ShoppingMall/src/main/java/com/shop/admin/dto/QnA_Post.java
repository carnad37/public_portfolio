package com.shop.admin.dto;

public class QnA_Post {

	private int no;
	private int que_id;
	private int u_id;
	private int que_secret = 0;
	private String que_title;
	private String que_text;
	private String que_date;
	private int que_check = 0;
	private String u_nick;
	
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getQue_id() {
		return que_id;
	}
	public void setQue_id(int que_id) {
		this.que_id = que_id;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getQue_secret() {
		return que_secret;
	}
	public void setQue_secret(int que_secret) {
		this.que_secret = que_secret;
	}
	public String getQue_title() {
		return que_title;
	}
	public void setQue_title(String que_title) {
		this.que_title = que_title;
	}
	public String getQue_text() {
		return que_text;
	}
	public void setQue_text(String que_text) {
		this.que_text = que_text;
	}
	public String getQue_date() {
		return que_date;
	}
	public void setQue_date(String que_date) {
		this.que_date = que_date;
	}
	public int getQue_check() {
		return que_check;
	}
	public void setQue_check(int que_check) {
		this.que_check = que_check;
	}	
	
}
