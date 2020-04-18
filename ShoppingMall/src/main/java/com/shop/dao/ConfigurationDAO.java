package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.admin.dto.Banner;

public interface ConfigurationDAO {
	//��� ǥ�� ���� �ҷ�����
	public int getBannerNumber();
	
	//��� ǥ�� ���� ����
	public int setBannerNumber(int con_banner_number);
	
	//��� �̹��� �ҷ�����
	public List<Banner> loadBannerImages();
	
	//��� �̹��� ����
	public int delBannerImages(@Param("list") List<Integer> delList);
	
	//��� �̹��� ���ε�
	public int addBannerImages(@Param("list") List<Banner> bannerList);
	
	//��� �̹��� ����(form)
	public int setBannerImages(@Param("list") List<Integer> setList);
	
	//�߰��� ��� �̹��� ����(form)
	public List<Banner> loadNewBannerImages(@Param("list") List<Integer> laodList);
	
	//��� �̹��� ���� ����
	public int uncheckBannerImage();
}
