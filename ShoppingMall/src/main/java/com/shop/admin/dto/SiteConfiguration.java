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
	//�ҷ����� ����� ��� �����Ͱ� ��� ����Ʈ
	private List<Banner> bannerList;
	//���� ���õ� ����� ���ϸ� ����Ʈ
	private List<String> onBannerList;
	
	@Autowired
	public SiteConfiguration(ConfigurationDAO dao) {
		this.dao = dao;
		this.bannerNumber = dao.getBannerNumber();
		setBannerList();
		loadOnBannerList();		
	}
	
	//���ο� ����̹������� �����Ѵ�. ������ rb_exist=2���� 1�� �ٲ��ش�.
	/*
	 *  rb_exist = 1�� ��ʷ� ���õ��� ���� �̹���.
	 *  rb_exist = 2�� ��ʷ� ���õ� �̹���.
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
		
		//DB�� exist=2���� 1�ιٲٰ� ���ο� exist=2�� ����Ѵ�.
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
		//delAltList�� ���� �ҷ����� List�� ����. alt�±��� ��.
		//�̹� sort���ְ����� Ȥ�ø𸣴� sort���ְ�, reverse���ش�.
		Collections.sort(delAltList);
		Collections.reverse(delAltList);
		
		List<Integer> delList = new ArrayList<Integer>();
		for (int target : delAltList) {
			//���� �����ְ� �ִ� ������� Ȯ��
			boolean onCheck = onBannerList.contains(this.bannerList.get(target).getRb_path());
			if (onCheck) {
				//true�� ��� �Ѿ��.
				continue;
			}			
			//���� ������ row�� id�� List�� ��´�.
			delList.add(this.bannerList.get(target).getRb_id());
			
			//������ ������Ų��.			
			//Bean���� List���� Target�� �����Ѵ�.(���� ���Ŷ� �ε��� ��ȭ x)
			this.bannerList.remove(target);
		}
		
		if (delList.size() == EMPTY) {
			//���� ���õ� ���� ���� ���(�̹� ��ʻ������ �̹����� ���É��� ���)
			return false;
		} else {
			//DB���� exist�� 0���� ��ȯ�Ѵ�.
			dao.delBannerImages(delList);
			return true;
		}		
	}
	
	//DB���� ��� �̹������� �ҷ��´�.
	public void setBannerList() {
		this.bannerList = dao.loadBannerImages();
	}
	
	public void addBannerImages(List<Banner> addBannerList) {
		//DB�� �����Ѵ�.
		dao.addBannerImages(addBannerList);
		
		//DB���� �ٽ� �ҷ��´�.(�ϴ� ��¥ �ҷ�����)
		setBannerList();
	}	
}

//JSON���Ͽ� ���� ����(deprecated)
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