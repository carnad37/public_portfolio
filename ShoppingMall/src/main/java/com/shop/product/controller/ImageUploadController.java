package com.shop.product.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.admin.dto.UploadFileSet;
import com.shop.main.security.role.CustomUser;
import com.shop.main.service.CustomFileUplaod;
import com.shop.product.dto.Ckeditor;
import com.shop.product.service.ProductService;

@Controller
@RequestMapping(value = "/upload")
public class ImageUploadController {

	@Autowired
	CustomFileUplaod fileUpload;
	
	@Autowired
	private ProductService pServices;
	
    private static final Logger logger = LoggerFactory.getLogger(ImageUploadController.class);

    @RequestMapping(value="/imageUpload") 
    public void imageUpload(HttpServletRequest request,
    		HttpServletResponse response, @RequestParam MultipartFile upload,
    		@AuthenticationPrincipal CustomUser user)
            throws IOException {
 
    	int currentUserId = user.getDetails().getU_id();
    	
        String path = request.getSession().getServletContext().getRealPath("/resources/main/images/main_product/");
        
        UploadFileSet uploadFile = fileUpload.fileUpload(upload, path);
        pServices.insertCkeditorImage(new Ckeditor(uploadFile.getSaveFileName(), 0, currentUserId, 0));
        
		HttpSession session = request.getSession();
		if (session.getAttribute("u_id") == null) {
			session.setAttribute("u_id", currentUserId);
		}
		
        // 클라이언트에 결과 표시
        String callback = request.getParameter("CKEditorFuncNum");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=utf-8");
        PrintWriter printWriter = response.getWriter();
        String fileUrl = request.getContextPath() + "/resources/main/images/main_product/" + uploadFile.getSaveFileName();

        printWriter.println("<script>window.parent.CKEDITOR.tools.callFunction(" + callback + ",'" + fileUrl
                + "','이미지가 업로드되었습니다.')" + "</script>");
        printWriter.flush();

    }
}