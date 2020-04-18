package com.shop.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shop.admin.dto.Banner;

public interface ConfigurationDAO {
	//배너 표시 갯수 불러오기
	public int getBannerNumber();
	
	//배너 표시 갯수 변경
	public int setBannerNumber(int con_banner_number);
	
	//배너 이미지 불러오기
	public List<Banner> loadBannerImages();
	
	//배너 이미지 삭제
	public int delBannerImages(@Param("list") List<Integer> delList);
	
	//배너 이미지 업로드
	public int addBannerImages(@Param("list") List<Banner> bannerList);
	
	//배너 이미지 선택(form)
	public int setBannerImages(@Param("list") List<Integer> setList);
	
	//추가된 배너 이미지 선택(form)
	public List<Banner> loadNewBannerImages(@Param("list") List<Integer> laodList);
	
	//배너 이미지 선택 해제
	public int uncheckBannerImage();
}
