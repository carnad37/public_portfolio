package com.shop.main;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.shop.admin.dto.Banner;
//import com.shop.admin.dto.CustomRememberMeToken;
import com.shop.dao.ConfigurationDAO;
//import com.shop.dao.MemberDAO;
import com.shop.product.service.ProductService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml",
		"file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/security-context.xml" })
public class DataBaseTester {

	@Autowired
	private ConfigurationDAO dao;

	@Autowired
	WebApplicationContext context;
//	
//	@Autowired
//	private MemberDAO memberDAO;

	@Autowired
	ProductService pServices;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}
//	cmember 테이블의 레코드 개수를 model에 저장, 갯수가 맞으면 success

//	@Test
//	public void addBannerImagesTest() throws Exception{
//		List<Banner> bannerList = new ArrayList<Banner>();
//		for (int i = 0; i < 5; i++) {
//			Banner banner = new Banner();
//			banner.setRb_id(1);
//			banner.setRb_date("2018-07-07");
//			banner.setRb_exist(1);
//			banner.setRb_name("new_name");
//			banner.setRb_path("new_path");
//			bannerList.add(banner);
//		}		
//		int ret = dao.addBannerImages(bannerList);
//		assertThat(ret, is(5));
//	}

//	@Test
//	public void signUpTest() throws Exception{
//		this.mockMvc.perform(get("/member/sign_up")
//		.param("firstName", "chailin")
//		.param("nickName", "차일린")
//		.param("inputPassword", "gunzun12")
//		.param("inputEmail", "gunzun@naver.com")
//		.param("userName", "황현수")
//		.param("phone", "01053867292")
//		.param("zoneCode", "12345")
//		.param("firstAddress", "춘천시 후만로 98번길 9 (주공 4단지 아파트 409동 201호)")
//		.param("secondAddress", "409동 201호"))
//		.andExpect(status().isOk());
//	}

//	@Test
//	public void loadTargetBanner() throws Exception{
//		List<Integer> loadList = new ArrayList<Integer>();
//		loadList.add(2);
//		loadList.add(4);
//		List<Banner> bannerList = dao.loadNewBannerImages(loadList);
//		System.out.println(bannerList.size());
//		for (Banner banner : bannerList) {
//			System.out.println(banner.getRb_id() + " : " + banner.getRb_path());
//		}
//	}

//	@Test
//	public void loadTargetBanner() throws Exception{
//		Date date = new Date();
//		memberDAO.createNewToken(new CustomRememberMeToken(new PersistentRememberMeToken("이름", "testest", "ttt", date)));
//		
//	}

//	
//	@Test
//	public void delBannerImagesTest() throws Exception{
//		List<Integer> delList = new ArrayList<Integer>();
//		delList.add(6);
//		delList.add(4);
//		int ret = dao.delBannerImages(delList);
//		assertThat(ret, is(2));
//	}
//	
	@Test
	public void searchDelTarget() throws Exception {
		List<String> result = pServices.selectCkeditorDeleteImage(16);
		if (!result.isEmpty()) {
			System.out.println("널이 아니다");
			for (String string : result) {
				System.out.println(string);
			}
		}
		assertThat(result, is(nullValue()));
	}

}
