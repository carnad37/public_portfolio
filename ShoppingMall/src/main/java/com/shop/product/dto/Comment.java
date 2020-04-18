package com.shop.product.dto;

public class Comment {
//	`pq_id`     INT(11)      NOT NULL, -- 댓글번호
//	`u_id`      INT(11)      NOT NULL, -- 유저번호(구매예정자)
//	`p_id`      INT(11)      NOT NULL, -- 상품번호
//	`pq_text`   VARCHAR(500) NOT NULL, -- 댓글내용
//  `pq_answer` VARCHAR(500)           -- 판매자 답글
//	`pq_date`   DATE         NOT NULL, -- 댓글날짜
//	`pq_secret` INT(1)       NOT NULL, -- 비밀글설정여부
//	`pq_exist`  INT(1)       NOT NULL  -- 삭제여부
	
	private int pq_id;
	private int u_id;
	private int p_id;
	private String pq_text;
	private String pq_answer;
	private String pq_date;
	private boolean pq_secret;
	private int pq_exist;
	private String u_nick;
	private int pq_status;
	private String pq_answerdate;	
	
	public boolean isPq_secret() {
		return pq_secret;
	}
	public void setPq_secret(boolean pq_secret) {
		this.pq_secret = pq_secret;
	}
	public int getPq_id() {
		return pq_id;
	}
	public void setPq_id(int pq_id) {
		this.pq_id = pq_id;
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
	public String getPq_text() {
		return pq_text;
	}
	public void setPq_text(String pq_text) {
		this.pq_text = pq_text;
	}
	public String getPq_answer() {
		return pq_answer;
	}
	public void setPq_answer(String pq_answer) {
		this.pq_answer = pq_answer;
	}
	public String getPq_date() {
		return pq_date;
	}
	public void setPq_date(String pq_date) {
		this.pq_date = pq_date;
	}

	public int getPq_exist() {
		return pq_exist;
	}
	public void setPq_exist(int pq_exist) {
		this.pq_exist = pq_exist;
	}
	public String getU_nick() {
		return u_nick;
	}
	public void setU_nick(String u_nick) {
		this.u_nick = u_nick;
	}
	public int getPq_status() {
		return pq_status;
	}
	public void setPq_status(int pq_status) {
		this.pq_status = pq_status;
	}
	public String getPq_answerdate() {
		return pq_answerdate;
	}
	public void setPq_answerdate(String pq_answerdate) {
		this.pq_answerdate = pq_answerdate;
	}
}
