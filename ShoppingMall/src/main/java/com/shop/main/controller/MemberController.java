package com.shop.main.controller;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shop.admin.dto.SignUp_Data;
import com.shop.admin.service.MemberServices;


/**
 * Handles requests for the application home page.
 */
@RequestMapping(value="/member")
@Controller
public class MemberController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private BCryptPasswordEncoder encodePassword;
	
	@Autowired
	private MemberServices services;
	
	@Autowired
	private JavaMailSenderImpl javaMailSenderImpl;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		return "/_admin/blank.board-temp";
	}
	
	//아이디 체크
	@RequestMapping(value = "/id_check")
	@ResponseBody
	public int banDuplicatedId(@RequestParam String id) {
		int result = services.countRowById(id);
		return result;
	}	
	
	//닉네임 체크
	@RequestMapping(value = "/nick_check")
	@ResponseBody
	public int banDuplicatedNick(@RequestParam String id) {
		int result = services.countRowByNick(id);
		return result;
	}	
	
	//회원가입후 확인 창
	@RequestMapping(value = "/complete", method = RequestMethod.GET)
	public String testBlank(
			@RequestParam("userName")String userName,
			@RequestParam("email")String email,
			Model model) {
		model.addAttribute("userName", userName);
		model.addAttribute("email", email);
		
		return "/_admin/Sign-Up-Result.board-temp";
	}
	
	@RequestMapping(value = "/mail_check", method = RequestMethod.GET)
	public String mailCheck(@RequestParam("uuid") String uuid,
						    @RequestParam("u_id") int u_id,
						    RedirectAttributes redirect) {
		
		Map<String, Object> userRoleData = new HashMap<String, Object>();
		
		userRoleData.put("u_id", u_id);
		userRoleData.put("uuid", "'" + uuid + "'");
		
		int retval = services.modifyNewUserRole(userRoleData);
		if (retval == 0) {
			redirect.addFlashAttribute("error", "회원가입 처리에 실패하였습니다.<br>자세한 사항은 관리자에게 문의해주세요.");
		}		
		
		return "redirect:/";
	}

	
	@RequestMapping(value = "/sign_up")
	public String signUpProcess (
			SignUp_Data userData,
			HttpServletRequest request,
			RedirectAttributes redirect) throws IOException, MessagingException {
					
		userData.setU_pw(encodePassword.encode(userData.getU_pw()));
		services.setUserData(userData);
		
		String uuid = UUID.randomUUID().toString();	
		uuid = uuid.replace("-", "");
		
		insertUserRole(userData.getU_id(), uuid);		
		
		/*
		 * 인증 메일이 발송됨
		 */
		
		sendingMail(userData.getU_id(), uuid, userData.getU_mail(), request);
		
		/*
		 * 인증메일이 발송된것을 확인시켜줌.  
		 */
		redirect.addAttribute("userName", userData.getU_account());
		redirect.addAttribute("email", userData.getU_mail());
		
		return "redirect:/member/complete";
	}
	
	private void insertUserRole(int u_id, String uuid) {
		Map<String, Object> userRoleData = new HashMap<String, Object>();
		
		userRoleData.put("u_id", u_id);
		userRoleData.put("u_role", "ROLE_USER");
		userRoleData.put("u_enable", uuid);
		
		services.setUserRoleData(userRoleData);
	}
	
	private void sendingMail(int u_id, String uuid, String email, HttpServletRequest request) 
		throws IOException,	MessagingException {
		StringBuilder mailText = loadMail(u_id, uuid, request);
				
		MimeMessage message = javaMailSenderImpl.createMimeMessage();
		
		message.setFrom(new InternetAddress("java.carnad@gmail.com"));
		message.addRecipient(RecipientType.TO, new InternetAddress(email));
		message.setSubject("연습용 메일입니다.");
		message.setText(mailText.toString(), "utf-8", "html");
		
		javaMailSenderImpl.send(message);
	}
	
	private StringBuilder loadMail(int u_id, String uuid, HttpServletRequest request) throws IOException {
		String path = request.getServletContext().getRealPath("/resources/main/mail/sign-up-check-mail.html");

		File file = new File(path);
		FileReader fileReader = new FileReader(file);
		
		StringBuilder mailText = new StringBuilder();
        BufferedReader buff = new BufferedReader(fileReader);
        String pageContents;
        while((pageContents = buff.readLine())!=null){
        	if (pageContents.contains("INPUT_URL")) {
                StringBuilder url = new StringBuilder();
                url.append("http://");
                url.append(request.getServerName());
                url.append(":");
                url.append(request.getServerPort());
                url.append("/ShoppingMall/member/mail_check?u_id=");
                url.append(u_id);
                url.append("&uuid=");
                url.append(uuid);
                pageContents = pageContents.replace("INPUT_URL", url.toString());
			}
        	mailText.append(pageContents);
        	mailText.append("\r\n");
        }
        buff.close();
        fileReader.close();
        
		return mailText;
	}
	
}
