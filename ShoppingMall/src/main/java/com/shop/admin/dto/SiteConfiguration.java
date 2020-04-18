package com.shop.admin.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//import org.springframework.core.io.ClassPathResource;

import com.shop.dao.ConfigurationDAO;

@Repository("siteConfiguration")
public class SiteConfiguration {
	
	private final static int EMPTY = 0;
	private final static int SELECT_BANNER = 2;
	
	private ConfigurationDAO dao;	
	private int bannerNumber;
	//불러와진 배너의 모든 데이터가 담긴 리스트
	private List<Banner> bannerList;
	//현재 선택된 배너의 파일명 리스트
	private List<String> onBannerList;
	
	@Autowired
	public SiteConfiguration(ConfigurationDAO dao) {
		this.dao = dao;
		this.bannerNumber = dao.getBannerNumber();
		setBannerList();
		loadOnBannerList();		
	}
	
	//새로운 배너이미지들을 선택한다. 기존의 rb_exist=2들은 1로 바꿔준다.
	/*
	 *  rb_exist = 1은 배너로 선택되지 않은 이미지.
	 *  rb_exist = 2는 배너로 선택된 이미지.
	 */
	public void setOnBannerList(List<Integer> setAltList) {
		this.onBannerList.clear();
		
		Collections.sort(setAltList);
		Collections.reverse(setAltList);

		List<Integer> setList = new ArrayList<Integer>();
		for (Integer target : setAltList) {
			Banner banner = bannerList.get(target);
			this.onBannerList.add(banner.getRb_path());
			setList.add(banner.getRb_id());
		}
		
		//DB에 exist=2들을 1로바꾸고 새로운 exist=2를 등록한다.
		dao.uncheckBannerImage();
		dao.setBannerImages(setList);		
	}
	
	private void loadOnBannerList () {
		List<String> setBannerList = new ArrayList<String>();
		for (Banner banner : bannerList) {
			if (banner.getRb_exist() == SELECT_BANNER) {
				setBannerList.add(banner.getRb_path());
			}			
		}
		this.onBannerList = setBannerList;		
	}
	
	public List<String> getOnBannerList() {
		return onBannerList;
	}

	public int getBannerNumber() {
		return bannerNumber;
	}

	public void setBannerNumber(int bannerNumber) {
		dao.setBannerNumber(bannerNumber);
		this.bannerNumber = bannerNumber;
	}

	public List<Banner> getBannerList() {
		return bannerList;
	}

	public boolean delBannerList(List<Integer> delAltList) {
		//delAltList는 현재 불러와진 List의 순서. alt태그의 값.
		//이미 sort되있겠지만 혹시모르니 sort해주고, reverse해준다.
		Collections.sort(delAltList);
		Collections.reverse(delAltList);
		
		List<Integer> delList = new ArrayList<Integer>();
		for (int target : delAltList) {
			//현재 보여주고 있는 배너인지 확인
			boolean onCheck = onBannerList.contains(this.bannerList.get(target).getRb_path());
			if (onCheck) {
				//true일 경우 넘어간다.
				continue;
			}			
			//실제 삭제할 row의 id를 List에 담는다.
			delList.add(this.bannerList.get(target).getRb_id());
			
			//파일을 삭제시킨다.			
			//Bean내의 List에서 Target을 삭제한다.(역순 제거라 인덱스 변화 x)
			this.bannerList.remove(target);
		}
		
		if (delList.size() == EMPTY) {
			//만약 선택된 값이 없을 경우(이미 배너사용중인 이미지만 선택됬을 경우)
			return false;
		} else {
			//DB에서 exist를 0으로 변환한다.
			dao.delBannerImages(delList);
			return true;
		}		
	}
	
	//DB에서 배너 이미지들을 불러온다.
	public void setBannerList() {
		this.bannerList = dao.loadBannerImages();
	}
	
	public void addBannerImages(List<Banner> addBannerList) {
		//DB에 저장한다.
		dao.addBannerImages(addBannerList);
		
		//DB에서 다시 불러온다.(일단 통짜 불러오기)
		setBannerList();
	}	
}

//JSON파일에 설정 저장(deprecated)
//
//private JSONObject jsonObject;
//private ClassPathResource resource;
//
//public SiteConfiguration() throws IOException, ParseException {
//	this.resource = new ClassPathResource("json/Configuration.json");
//	JSONParser jsonParser = new JSONParser();
//	this.jsonObject = (JSONObject)jsonParser.parse(new FileReader(resource.getFile()));
//}
//
//public int getMainPicNum() {
//	return Integer.parseInt((String)this.jsonObject.get("mainPicNum"));
//}
//
//public void setMainPicNum(String mainPicNum) throws IOException {
//	jsonObject.put("mainPicNum", mainPicNum);
//	FileWriter fileWriter = new FileWriter(resource.getFile());
//	fileWriter.write(jsonObject.toJSONString());
//	System.out.println("SiteConfiguratioin Update...!!");
//	
//	fileWriter.flush();
//	fileWriter.close();
//}	