package com.shop.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.admin.dto.Banner;
import com.shop.admin.dto.SiteConfiguration;
import com.shop.admin.service.AdminServices;
import com.shop.dao.ConfigurationDAO;

@Controller
@RequestMapping(value = "/admin/pic")
public class AdminPictureController {
	
	@Autowired
	private AdminServices services;
	
	@Autowired
	private SiteConfiguration config;

	@RequestMapping(value = "/view")
	public String configView(Model model) {	
		setConfigView(model);
		
		return "/_admin/BannerControl.admin-temp";
	}
	
	//배너 설정창 에러처리
	@RequestMapping(value = "/view", params={"error"})
	public String configView(Model model, @RequestParam String error) {		
		//config 그림파일들을 모두 로드해야함
		//bannerList : 현재 저장된 활성화된 모든 배너그림		
		setConfigView(model);
		model.addAttribute("error", error);
		
		return "/_admin/BannerControl.admin-temp";
	}
	
	private void setConfigView(Model model) {		
		//config 그림파일들을 모두 로드해야함
		//bannerList : 현재 저장된 활성화된 모든 배너그림
		List<Banner> bannerList = config.getBannerList();
		int bannerRow = 0;
		if (bannerList.size() / 5 == 1) {
			bannerRow = (bannerList.size() / 5);
		} else {
			bannerRow = (bannerList.size() / 5) + 1;
		}		
		model.addAttribute("bannerCount", config.getBannerNumber());
		model.addAttribute("bannerLength", bannerList.size());
		model.addAttribute("bannerRow", bannerRow);
		model.addAttribute("bannerList", bannerList);
	}
	
	//배너 이미지 갯수 처리.
	@RequestMapping(value = "/bannerSetNum")
	public String setMainPic(@RequestParam int rb_num) {
		config.setBannerNumber(rb_num);
		return "redirect:/admin/pic/view";
	}
	
	//배너 선택 처리
	@RequestMapping(value = "/select")
	public String setBannerImages(@RequestParam(value="select-banner", required=false)String[] checkImages, RedirectAttributes redirect) {
		List<Integer> setList = new ArrayList<Integer>();
		
		if (checkImages == null) {
			redirect.addAttribute("error", "배너가 선택되지 않았습니다.");
			return "redirect:/admin/config_view";
		}else if (checkImages.length != config.getBannerNumber()) {
			redirect.addAttribute("error", "선택된 배너의 숫자가 알맞지 않습니다.");
			return "redirect:/admin/pic/view";
		}
		
		for (String check : checkImages) {
			setList.add(Integer.parseInt(check));
		}
		config.setOnBannerList(setList);

		return "redirect:/admin/pic/view";
	}
	
	//배너 이미지 삭제처리
	@RequestMapping(value = "/delete")
	public String delBannerImages(@RequestParam(value="select-banner", required=false)String[] checkImages, RedirectAttributes redirect) {
		List<Integer> delList = new ArrayList<Integer>();
		
		if (checkImages == null) {
			redirect.addAttribute("error", "배너가 선택되지 않았습니다.");
			return "redirect:/admin/pic/view";
		}
		
		for (String check : checkImages) {
			delList.add(Integer.parseInt(check));
		}
		if (config.delBannerList(delList)) {
			return "redirect:/admin/pic/view";
		} else {
			redirect.addAttribute("error", "배너로 사용중인 이미지들입니다.");
			return "redirect:/admin/pic/view";
		}

		
	}
	
	
	@RequestMapping(value = "/upload")
	public String addBannerImages(MultipartHttpServletRequest multi, HttpServletRequest request, RedirectAttributes redirect) throws IOException {
		List<MultipartFile> fileList = multi.getFiles("main-picture");
		//최종 배너의 갯수 = 기존의 배너 갯수 + 새로 등록한 배너 갯수
		int currentBannerNumber = config.getBannerList().size() + fileList.size();
		
		//파일 업로드가 제대로 이루워지지 않으면 fileList 사이즈는 1이다, 하지만 1개일때도 1이므로 해당 요소가 FileName을 가지고 있는지 확인. 
		if (fileList.size() == 1 && fileList.get(0).getOriginalFilename().equals("")) {
			redirect.addAttribute("error", "이미지 파일이 등록되지 않았습니다.");
			return "redirect:/admin/pic/view";
		}
		
		//최대 배너 이미지 갯수는 20개로 제한한다.
		if (currentBannerNumber <= 20) {
			//파일을 업로드한다.
			String path = request.getServletContext().getRealPath("/resources/main/images/banner/");
			List<Banner> addBannerList = services.multiUploadService(fileList, path);
			
			config.addBannerImages(addBannerList);

		} else {
			redirect.addAttribute("error", "이미지파일 등록 한계를 초과합니다.");
		}
		return "redirect:/admin/pic/view";
	}
	
	//배너 숫자 변경
	@RequestMapping(value = "/setNum")
	public String setBannerNumber (@RequestParam int bannerNum,  RedirectAttributes redirect) {
		
		//배너는 20개까지 등록가능
		if (bannerNum > 0 && bannerNum < 21) {
			config.setBannerNumber(bannerNum);
		} else {
			redirect.addAttribute("error", "배너 갯수가 지정된 범위를 벗어났습니다.");
		}
		
		return "redirect:/admin/pic/view";
	}

}
