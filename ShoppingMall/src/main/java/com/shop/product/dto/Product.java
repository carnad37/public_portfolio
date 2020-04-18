package com.shop.product.dto;

public class Product {

	private int p_id; //상품번호
	private int c_id; //카테고리번호   
	private String p_name; //상품이름
	private String p_text; //상품내용
	private String p_title; //상품제목  
	private int p_price; //가격 
	private int p_quantity; //수량
	private int u_id; //유저번호(판매자)
	private String p_date; //등록기준 년/월/일/시분초
    private String u_nick; //유저 닉네임(판매자)
    private String p_thumbnail; //썸네일 이미지 경로
	private int p_exits;
	private int o_state;
 
    public int getO_state() {
		return o_state;
	}
	public void setO_state(int o_state) {
		this.o_state = o_state;
	}
	public int getP_id() {
		return p_id;
	}
	public void setP_id(int p_id) {
		this.p_id = p_id;
	}
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_text() {
		return p_text;
	}
	public void setP_text(String p_text) {
		this.p_text = p_text;
	}
	public String getP_title() {
		return p_title;
	}
	public void setP_title(String p_title) {
		this.p_title = p_title;
	}
	public int getP_price() {
		return p_price;
	}
	public void setP_price(int p_price) {
		this.p_price = p_price;
	}
	public int getP_quantity() {
		return p_quantity;
	}
	public void setP_quantity(int p_quantity) {
		this.p_quantity = p_quantity;
	}
	public int getU_id() {
		return u_id;
	}
	public void setU_id(int u_id) {
		this.u_id = u_id;
	}
	public String getP_date() {
		return p_date;
	}
	public void setP_date(String p_date) {
		this.p_date = p_date;
	}
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	public String getP_thumbnail() {
		return p_thumbnail;
	}
	public void setP_thumbnail(String p_thumbnail) {
		this.p_thumbnail = p_thumbnail;
	}
	public int getP_exits() {
		return p_exits;
	}
	public void setP_exits(int p_exits) {
		this.p_exits = p_exits;
	}
}
