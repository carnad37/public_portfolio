package com.shop.main.service;

import java.io.File;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.shop.product.service.ProductService;

public class CustomFileDelete {

	public void fileDeleter(HttpSession session, ProductService pServices) {
		if (session.getAttribute("u_id") != null) {
			int targetID = (int) session.getAttribute("u_id");
			List<String> deleteList = pServices.selectCkeditorDeleteImage(targetID);

			for (String delTarget : deleteList) {
				String path = session.getServletContext().getRealPath("/resources/main/images/main_product/");
		        File file = new File(path + delTarget); 
				if (file.exists()) {
					file.delete();
				}
			}
			pServices.deleteCkeditorImageByU_id(targetID);
		}
	}
	
}
