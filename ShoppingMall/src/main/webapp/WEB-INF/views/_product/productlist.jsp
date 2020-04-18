<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <script type="text/javascript">
		function page(idx) {
			var pagenum = idx;
// 			var contentnum = $("#contentnum option:selected").val();
			var contentnum =10;
			location.href="<c:url value="/product/list?pagenum="/>"+pagenum+"&contentnum="+contentnum;
		}
    </script>

    <div id="container_cover">
      <div class="container py-5">
      <div class="productlist">
        <ul class="list_ul m-0 p-0">
        	<c:forEach items="${list}" var="list">
	          <li class="list_li shadow p-3 mt-4" style="width:16%; margin:0 2% 0 2%;">
	            <div class="thumbnail"><img src="<c:url value="/resources/main/images/thum_product/"/>${list.p_thumbnail}" onclick="$(this).closest('li').children('.title').children('a').click();" alt=""></div>
	            <div class="title mt-3 custom-text-overflow"><a href="<c:url value="/product/product_page?p_id=${list.p_id}"/>">${list.p_title}</a></div>
	            <div class="price custom-text-overflow"><small>판매가 : </small><strong><fmt:formatNumber value="${list.p_price}" pattern="#,###"/>원</strong></div>
	            <div class="nick custom-text-overflow"><small>판매자 : </small>${list.u_nick}</div>
	            <div class="date custom-text-overflow">${list.p_date}</div>
	          </li>
	        </c:forEach>  
        </ul>
      </div>
      <div class="container_footer text-center">
      	<ul class="pagination d-inline-block custom-pagination">
			<c:if test="${page.prev}">
				<li class="page-item"><a class="page-link" href="javascript:page(${page.getPagenum()});">이전</a></li>
			</c:if>
	
			<c:forEach begin="${page.getStartPage()}" end="${page.getEndPage()}" var="idx">
				<li class="page-item"><a class="page-link" href="javascript:page(${idx});">${idx}</a></li>
			</c:forEach>
	
			<c:if test="${page.next}">
				<li class="page-item"><a class="page-link" href="javascript:page(${page.getEndPage()});">다음</a></li>
			</c:if>
		</ul>      
      </div>
      </div>
    </div>


