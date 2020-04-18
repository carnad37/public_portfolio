package com.shop.main.service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.shop.admin.dto.UploadFileSet;

@Service
public class CustomFileUplaod {
	
	public UploadFileSet fileUpload(MultipartFile file, String path) throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String originFileName = file.getOriginalFilename();
		System.out.println("originFileName : " + originFileName);
		String extName = originFileName.substring(originFileName.lastIndexOf("."), originFileName.length());
		System.out.println("extName : " + extName);

		// 서버에서 저장 할 파일 이름
		String saveFileName = makeFileName(authentication.getName(), extName);
		writeFile(file, saveFileName, path);
		
		return new UploadFileSet(originFileName, extName, saveFileName);
	}
	
	private String makeFileName(String u_account, String extraName) {
		LocalDateTime currentDate = LocalDateTime.now();
		
		StringBuilder dateString = new StringBuilder();
		dateString.append(currentDate.getYear());
		dateString.append(currentDate.getMonthValue() + 1);
		dateString.append(currentDate.getDayOfMonth());
		dateString.append(currentDate.getHour());
		dateString.append(currentDate.getMinute());
		dateString.append(currentDate.getNano());
		dateString.append("_");
		dateString.append(u_account);
		dateString.append(extraName);
		
		return dateString.toString();
	}	
	
	
	private boolean writeFile(MultipartFile multipartFile, String saveFileName, String path)
			throws IOException{
		boolean result = false;
		
		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(path + saveFileName);
		fos.write(data);
		fos.close();
		
		return result;
	}

}
