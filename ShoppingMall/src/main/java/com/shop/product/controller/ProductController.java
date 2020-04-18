package com.shop.product.controller;


import java.io.IOException;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.nhncorp.lucy.security.xss.XssPreventer;
import com.shop.product.dto.PageMaker;
import com.shop.product.dto.Product;
import com.shop.product.dto.ProductOrder;
import com.shop.admin.dto.Category;
import com.shop.admin.dto.UploadFileSet;
import com.shop.admin.service.CategoryServices;
import com.shop.main.security.role.CustomUser;
import com.shop.main.service.CustomFileDelete;
import com.shop.main.service.CustomFileUplaod;
import com.shop.product.dto.Ckeditor;
import com.shop.product.dto.Comment;
import com.shop.product.service.OrderServices;
import com.shop.product.service.ProductService;



@Controller
@RequestMapping(value = "/product")
public class ProductController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	
	private static final int VIEWER_WRITER_SAME = -1;
	private static final int VIEWER_WRITER_DIFFERENT = 0;
	
	@Autowired
	CustomFileUplaod fileUpload;
	
	@Autowired
	ProductService pServices;
	
	@Autowired
	CategoryServices cServices;
	
	@Autowired
	OrderServices oServices;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	//상품등록 페이지 호출
	@RequestMapping(value = "/call_product", method = RequestMethod.GET)
	public String callAdder(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		
		//세션에 기존에 본몬용으로 업로드된 파일들이 있을 시에 삭제
		if (session.getAttribute("u_id") != null) {
			CustomFileDelete fileDel = new CustomFileDelete();
			fileDel.fileDeleter(session, pServices);
		}
		
		List<Category> categoryList = cServices.getCategory();
		model.addAttribute("categoryList", categoryList);
		
		return "/_product/product-adder.product-temp";
	}
	
	//상품등록 메소드 호출
	@RequestMapping(value = "/call_adder",method=RequestMethod.POST)
	public String productAdd(Product product,  @AuthenticationPrincipal CustomUser user,
			HttpServletRequest request, @RequestParam MultipartFile thumbnailfile) throws IOException {

		int u_id = user.getDetails().getU_id();
		//파일업로드 클래스
		String path = request.getServletContext().getRealPath("/resources/main/images/thum_product/");
		//업로드와 동시에 DTO에 값을 담는다
		UploadFileSet fileSet = fileUpload.fileUpload(thumbnailfile, path);
		
		String thumbnailLocal = fileSet.getSaveFileName();
		
		//product에 새로 만들어진 파일명을 입력.
        product.setP_thumbnail(thumbnailLocal);		
		product.setU_id(u_id);
		
		//product등록(p_id반환)
		int p_id = pServices.productAdd(product);
		
		pServices.updateCkeditorState(new Ckeditor(null, 1, u_id, p_id));
		
		//세션에 등록된 u_id를 삭제해 세션종료시 이미지 삭제 대상에서 제외.
		HttpSession session = request.getSession();
		session.removeAttribute("u_id");

		return "redirect:/product/list?pagenum=1&contentnum=10";
	}
	
	@RequestMapping("/list")
	public String list(HttpServletRequest request, Model model, Product product) {
		PageMaker pagemaker = new PageMaker();
		String pagenum = request.getParameter("pagenum");
		String contentnum = request.getParameter("contentnum");
		int cpagenum = Integer.parseInt(pagenum);
		int ccontentnum = Integer.parseInt(contentnum);
		
		pagemaker.setTotalcount(pServices.totalCount()); //전체 게시글 개수를 지정한다
		pagemaker.setPagenum(cpagenum-1); //현제 페이지를 페이지 객체에 지정한다 -1을 해야 쿼리에서 사용 가능
		pagemaker.setContentnum(ccontentnum); //한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
		pagemaker.setCurrentblock(cpagenum); // 현제 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
		pagemaker.setLastblock(pagemaker.getTotalcount()); //마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
		
		pagemaker.prevnext(cpagenum);
		pagemaker.setStartPage(pagemaker.getCurrentblock());
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
		
		List<Product> prdouctlist = pServices.productList(pagemaker.getPagenum()*10, pagemaker.getContentnum());
		
		model.addAttribute("list", prdouctlist);
		model.addAttribute("page", pagemaker);
		
		return "/_product/productlist.product-temp";
	}

	@RequestMapping("/search_list")
	public String searchProductList(int pagenum, int contentnum, String target_word, String c_id, Model model) {
		PageMaker pagemaker = new PageMaker();
		
		pagemaker.setTotalcount(pServices.totalCount()); //전체 게시글 개수를 지정한다
		pagemaker.setPagenum(pagenum-1); //현제 페이지를 페이지 객체에 지정한다 -1을 해야 쿼리에서 사용 가능
		pagemaker.setContentnum(contentnum); //한 페이지에 몇개씩 게시글을 보여줄지 지정한다.
		pagemaker.setCurrentblock(pagenum); // 현제 페이지 블록이 몇번인지 현재 페이지 번호를 통해서 지정한다.
		pagemaker.setLastblock(pagemaker.getTotalcount()); //마지막 블록 번호를 전체 게시글 수를 통해서 정한다.
		
		pagemaker.prevnext(pagenum);
		pagemaker.setStartPage(pagemaker.getCurrentblock());
		pagemaker.setEndPage(pagemaker.getLastblock(), pagemaker.getCurrentblock());
		
		List<Product> prdouctlist = pServices.productSearchList(pagemaker.getPagenum()*10, pagemaker.getContentnum(), target_word, Integer.parseInt(c_id));

		model.addAttribute("list", prdouctlist);
		model.addAttribute("page", pagemaker);
		
		return "/_product/productlist.product-temp";
	}	
	
	@RequestMapping(value="/product_page")
	public String prouct_page(Model model,
							  HttpServletResponse response,
							  @RequestParam String p_id,
							  @AuthenticationPrincipal CustomUser user,
							  @CookieValue(value="recentProduct", required=false) String recentProduct) {
		
		int targetP_id = Integer.parseInt(p_id);
		//로그인 여부 체크
		Product product = pServices.productPage(targetP_id);
		product.setP_text(XssPreventer.unescape(product.getP_text()));
		
		//보는 유저랑 작성자랑 같은지 비교
		int viewUser = user.getDetails().getU_id();
		int writeUser = product.getU_id();

		if (viewUser == writeUser) {
			model.addAttribute("confirmID", "match");
		} else {
			model.addAttribute("confirmID", "not_match");
		}
		
		//오더 카운트로 판매중인지 확인
		int orderCount = oServices.getOrderCount(targetP_id);		
		if (orderCount != 0) {
			model.addAttribute("EndSale", "true");
		}

		model.addAttribute("product", product);
		
		//쿠키 등록		
		String cookieData = null;
		if (recentProduct != null) {
			cookieData = pServices.cookieSetting(recentProduct, String.valueOf(product.getP_id()));
		} else {
			cookieData = String.valueOf(product.getP_id());
		}
		Cookie newCookie = new Cookie("recentProduct", cookieData);
		newCookie.setMaxAge(50000);
		newCookie.setPath("/");
		response.addCookie(newCookie);
		
		return "/_product/productpage.product-temp";
		
	} 
 
    @RequestMapping(value="/getReplyData")
    @ResponseBody
    public List<Comment> getReplyData(int p_id) {

		int u_id = pServices.productPage(p_id).getU_id();
    	List<Comment> replyData = pServices.getProductReplyList(p_id);
    	
    	for (Comment comment : replyData) {
    		if (comment.getU_id() == u_id) {
    			comment.setU_id(VIEWER_WRITER_SAME);
    		} else {
    			comment.setU_id(VIEWER_WRITER_DIFFERENT);
    		}
		}   	    	
    	return replyData;
    }
    
	@RequestMapping(value="/reply_add")
	public String insertProductReply(Comment comment,
			@AuthenticationPrincipal CustomUser user,
			RedirectAttributes redirect) {
		comment.setU_id(user.getDetails().getU_id());
		
		//입력값 체크
		if (comment.getPq_text().isEmpty()) {
			redirect.addFlashAttribute("p_id", comment.getP_id());
			redirect.addFlashAttribute("error", "빈값이 입력되었습니다.");
			return "redirect:/product/product_page"; 
		}
		
		pServices.insertProductReply(comment);
		
		redirect.addAttribute("p_id", comment.getP_id());
		
		return "redirect:/product/product_page";
		
	}
	
	@RequestMapping(value="/new_order")
	public String insertNewOrder(
			ProductOrder order,
			@AuthenticationPrincipal CustomUser user,
			RedirectAttributes redirect) {
				
		order.setU_id(user.getDetails().getU_id());
		
		oServices.insertNewOrder(order);
		
		redirect.addAttribute("p_id", order.getP_id());
		
		return "redirect:/product/product_page";
		
	}

}

