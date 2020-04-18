package com.shop.admin.dto;

public class SignUp_Data {
	
	private int u_id;
	private String u_account;
	private String u_nick;
	private String u_pw;
	private String u_mail;
	private String u_phone;
	private String u_zipcode;
	private String u_addr;
	private String u_addr_detail;	
	private String u_name;
	private String u_confirmPw;
	private String u_date_start;
	private String u_date_end;	
	private int page;
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public String getU_date_start() {
		return u_date_start;
	}
	public void setU_date_start(String u_date_start) {
		this.u_date_start = u_date_start;
	}
	public String getU_date_end() {
		return u_date_end;
	}
	public void setU_date_end(String u_date_end) {
		this.u_date_end = u_date_end;
	}
	public String getU_confirmPw() {
		return u_confirmPw;
	}
	public void setU_confirmPw(String u_confirmPw) {
		this.u_confirmPw = u_confirmPw;
	}
	public String getU_name() {
		return u_name;
	}
	public void setU_name(String u_name) {
		this.u_name = u_name;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
//	public SignUp_Data(String u_account, String u_nick, String u_pw, String u_mail, String u_phone, String u_zipcode,
//			String u_addr, String u_addr_detail) {
//		super();
//		this.u_account = u_account;
//		this.u_nick = u_nick;
//		this.u_pw = u_pw;
//		this.u_mail = u_mail;
//		this.u_phone = u_phone;
//		this.u_zipcode = u_zipcode;
//		this.u_addr = u_addr;
//		this.u_addr_detail = u_addr_detail;
//	}
	public String getU_account() {
		return u_account;
	}
	public void setU_account(String u_account) {
		this.u_account = u_account;
	}
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	public String getU_pw() {
		return u_pw;
	}
	public void setU_pw(String u_pw) {
		this.u_pw = u_pw;
	}
	public String getU_mail() {
		return u_mail;
	}
	public void setU_mail(String u_mail) {
		this.u_mail = u_mail;
	}
	public String getU_phone() {
		return u_phone;
	}
	public void setU_phone(String u_phone) {
		this.u_phone = u_phone;
	}
	public String getU_zipcode() {
		return u_zipcode;
	}
	public void setU_zipcode(String u_zipcode) {
		this.u_zipcode = u_zipcode;
	}
	public String getU_addr() {
		return u_addr;
	}
	public void setU_addr(String u_addr) {
		this.u_addr = u_addr;
	}
	public String getU_addr_detail() {
		return u_addr_detail;
	}
	public void setU_addr_detail(String u_addr_detail) {
		this.u_addr_detail = u_addr_detail;
	}
	
	
}
