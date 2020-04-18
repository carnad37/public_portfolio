package com.shop.product.dto;

public class PageMaker {
	private int totalcount;//��ü �Խù� ����
	private int pagenum; //���� ������ ��ȣ
	private int contentnum; //�� �������� � ǥ������
	private int startPage; //���� ������ ����� ���� ������
	private int endPage; //���� ������ ����� ������ ������
	private boolean prev; //���� �������� ���� ȭ��ǥ
	private boolean next; //���� �������� ���� ȭ��ǥ
	private int currentblock; //���� ������ ���
	private int lastblock; //������ ������ ���
	
	public void prevnext(int pagenum) {
		setPrev(true);
		setNext(true);
		if (pagenum==1)
		{
			setPrev(false);
		}
		else if (pagenum==lastblock)
		{
			setPrev(true);
			setNext(false);
		}
	}
	
	// ��ü ������ ���� ����ϴ� �Լ�
	public int calcpage(int totalcount, int contentnum)	
	{
		//125 / 10 = 12.5
		// 13������
		int totalpage = totalcount / contentnum;
		if (totalpage%contentnum>0) {
			totalpage++;
		}
		return totalpage;
	}
	
	public int getTotalcount() {
		return totalcount;
	}
	public void setTotalcount(int totalcount) {
		this.totalcount = totalcount;
	}
	public int getPagenum() {
		return pagenum;
	}
	public void setPagenum(int pagenum) {
		this.pagenum = pagenum;
	}
	public int getContentnum() {
		return contentnum;
	}
	public void setContentnum(int contentnum) {
		this.contentnum = contentnum;
	}
	public int getStartPage() {
		return startPage;
	}
	public void setStartPage(int currentblock) {
		this.startPage = (currentblock*5)-4;
	}
	public int getEndPage() {
		return endPage;
	}
	
	public void setEndPage(int getlastblock, int getcurrentblock) {
		if (getlastblock == getcurrentblock ) {
			this.endPage = calcpage(getTotalcount(), getContentnum());
		}
		else {
			this.endPage = getEndPage()+4;
		}
	}
	
	public boolean isPrev() {
		return prev;
	}
	public void setPrev(boolean prev) {
		this.prev = prev;
	}
	public boolean isNext() {
		return next;
	}
	public void setNext(boolean next) {
		this.next = next;
	}
	public int getCurrentblock() {
		return currentblock;
	}
	public void setCurrentblock(int pagenum) {
		this.currentblock = pagenum/5;
		if (pagenum%5>0) {
			this.currentblock++;
		}
	}
	public int getLastblock() {
		return lastblock;
	}
	public void setLastblock(int totalcount) {
		//10 ,5 > 10* 5 => 50
		
		this.lastblock = totalcount / (5*this.contentnum);
		if (totalcount == 0) {
			this.lastblock=1;
		}
		else if (totalcount %(5*this.contentnum)>0)
		{
			this.lastblock++;
		}
	}
	
	
}
