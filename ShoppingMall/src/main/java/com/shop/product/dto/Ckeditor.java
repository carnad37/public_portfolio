package com.shop.product.dto;

public class Ckeditor {

	private String ck_file;
	private int ck_state;
	private int u_id;
	private int p_id;
	
	public Ckeditor() {
		
	}
	
	public Ckeditor(String ck_file, int ck_state, int u_id, int p_id) {
		super();
		this.ck_file = ck_file;
		this.ck_state = ck_state;
		this.u_id = u_id;
		this.p_id = p_id;
	}
	
	public String getCk_file() {
		return ck_file;
	}
	public void setCk_file(String ck_file) {
		this.ck_file = ck_file;
	}
	public int getCk_state() {
		return ck_state;
	}
	public void setCk_state(int ck_state) {
		this.ck_state = ck_state;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	
	
}
