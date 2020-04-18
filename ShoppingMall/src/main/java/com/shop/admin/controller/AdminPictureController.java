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
	
	//��� ����â ����ó��
	@RequestMapping(value = "/view", params={"error"})
	public String configView(Model model, @RequestParam String error) {		
		//config �׸����ϵ��� ��� �ε��ؾ���
		//bannerList : ���� ����� Ȱ��ȭ�� ��� ��ʱ׸�		
		setConfigView(model);
		model.addAttribute("error", error);
		
		return "/_admin/BannerControl.admin-temp";
	}
	
	private void setConfigView(Model model) {		
		//config �׸����ϵ��� ��� �ε��ؾ���
		//bannerList : ���� ����� Ȱ��ȭ�� ��� ��ʱ׸�
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
	
	//��� �̹��� ���� ó��.
	@RequestMapping(value = "/bannerSetNum")
	public String setMainPic(@RequestParam int rb_num) {
		config.setBannerNumber(rb_num);
		return "redirect:/admin/pic/view";
	}
	
	//��� ���� ó��
	@RequestMapping(value = "/select")
	public String setBannerImages(@RequestParam(value="select-banner", required=false)String[] checkImages, RedirectAttributes redirect) {
		List<Integer> setList = new ArrayList<Integer>();
		
		if (checkImages == null) {
			redirect.addAttribute("error", "��ʰ� ���õ��� �ʾҽ��ϴ�.");
			return "redirect:/admin/config_view";
		}else if (checkImages.length != config.getBannerNumber()) {
			redirect.addAttribute("error", "���õ� ����� ���ڰ� �˸��� �ʽ��ϴ�.");
			return "redirect:/admin/pic/view";
		}
		
		for (String check : checkImages) {
			setList.add(Integer.parseInt(check));
		}
		config.setOnBannerList(setList);

		return "redirect:/admin/pic/view";
	}
	
	//��� �̹��� ����ó��
	@RequestMapping(value = "/delete")
	public String delBannerImages(@RequestParam(value="select-banner", required=false)String[] checkImages, RedirectAttributes redirect) {
		List<Integer> delList = new ArrayList<Integer>();
		
		if (checkImages == null) {
			redirect.addAttribute("error", "��ʰ� ���õ��� �ʾҽ��ϴ�.");
			return "redirect:/admin/pic/view";
		}
		
		for (String check : checkImages) {
			delList.add(Integer.parseInt(check));
		}
		if (config.delBannerList(delList)) {
			return "redirect:/admin/pic/view";
		} else {
			redirect.addAttribute("error", "��ʷ� ������� �̹������Դϴ�.");
			return "redirect:/admin/pic/view";
		}

		
	}
	
	
	@RequestMapping(value = "/upload")
	public String addBannerImages(MultipartHttpServletRequest multi, HttpServletRequest request, RedirectAttributes redirect) throws IOException {
		List<MultipartFile> fileList = multi.getFiles("main-picture");
		//���� ����� ���� = ������ ��� ���� + ���� ����� ��� ����
		int currentBannerNumber = config.getBannerList().size() + fileList.size();
		
		//���� ���ε尡 ����� �̷������ ������ fileList ������� 1�̴�, ������ 1���϶��� 1�̹Ƿ� �ش� ��Ұ� FileName�� ������ �ִ��� Ȯ��. 
		if (fileList.size() == 1 && fileList.get(0).getOriginalFilename().equals("")) {
			redirect.addAttribute("error", "�̹��� ������ ��ϵ��� �ʾҽ��ϴ�.");
			return "redirect:/admin/pic/view";
		}
		
		//�ִ� ��� �̹��� ������ 20���� �����Ѵ�.
		if (currentBannerNumber <= 20) {
			//������ ���ε��Ѵ�.
			String path = request.getServletContext().getRealPath("/resources/main/images/banner/");
			List<Banner> addBannerList = services.multiUploadService(fileList, path);
			
			config.addBannerImages(addBannerList);

		} else {
			redirect.addAttribute("error", "�̹������� ��� �Ѱ踦 �ʰ��մϴ�.");
		}
		return "redirect:/admin/pic/view";
	}
	
	//��� ���� ����
	@RequestMapping(value = "/setNum")
	public String setBannerNumber (@RequestParam int bannerNum,  RedirectAttributes redirect) {
		
		//��ʴ� 20������ ��ϰ���
		if (bannerNum > 0 && bannerNum < 21) {
			config.setBannerNumber(bannerNum);
		} else {
			redirect.addAttribute("error", "��� ������ ������ ������ ������ϴ�.");
		}
		
		return "redirect:/admin/pic/view";
	}

}
