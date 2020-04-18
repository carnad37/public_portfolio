<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

  <div id="container_cover">
  <header>
    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel">
      <ol class="carousel-indicators">
        <c:forEach var="count_bar" begin="1" end="${bannerNumber}">  
        	<c:choose>
        		<c:when test="${count_bar eq 1}">
        			<li data-target="#carouselExampleIndicators" data-slide-to="1" class="active"></li>
        		</c:when>
        		<c:otherwise>
        			<li data-target="#carouselExampleIndicators" data-slide-to="${count_bar}"></li>
        		</c:otherwise>
        	</c:choose>
         </c:forEach>
      </ol>
      <div class="carousel-inner" role="listbox">
      <c:forEach var="count_banner" begin="1" end="${bannerNumber}">
	  <c:choose>
		<c:when test="${count_banner eq 1}">
	        <div class="carousel-item active" style="background-image: url('<c:url value="/resources/main/images/banner/${bannerList[count_banner - 1]}"/>')">
	        </div>
        </c:when>
		<c:otherwise>
	        <div class="carousel-item" style="background-image: url('<c:url value="/resources/main/images/banner/${bannerList[count_banner - 1]}"/>')">

	        </div>
	 	 </c:otherwise>
	  </c:choose>
      </c:forEach>
      </div>
      <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
      </a>
      <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
        <span class="carousel-control-next-icon" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
      </a>
      </div>
  </header>

  <!-- Page Content -->
  <div class="container">
	<div class="p-4 shadow mt-5" style="height:400px;">
  		<h2 class="my-4 d-block custom-bottom-border">최근 본 상품</h2>
		<div class="row">
			<c:forEach items="${cookieList}" var="list">
				<div class="col-2 mx-auto" style="height:200px;">
		            <div class="thumbnail"><img src="<c:url value="/resources/main/images/thum_product/"/>${list.p_thumbnail}" onclick="$(this).closest('li').children('.title').children('a').click();" alt=""></div>
		            <div class="title mt-3 custom-text-overflow"><a href="<c:url value="/product/product_page?p_id=${list.p_id}"/>">${list.p_title}</a></div>
		            <div class="price custom-text-overflow"><small>판매가 : </small><strong><fmt:formatNumber value="${list.p_price}" pattern="#,###"/>원</strong></div>
		            <div class="date custom-text-overflow">${list.p_date}</div>
				</div>
			</c:forEach>
		</div>
	</div>
	
	<div class="p-4 shadow my-5" style="height:400px;">
  		<h2 class="my-4 d-block custom-bottom-border">최근 등록된 상품</h2>
		<div class="row">
			<c:forEach items="${addProductList}" var="list">
				<div class="col-2 mx-auto" style="height:200px;">
		            <div class="thumbnail"><img src="<c:url value="/resources/main/images/thum_product/"/>${list.p_thumbnail}" onclick="$(this).closest('li').children('.title').children('a').click();" alt=""></div>
		            <div class="title mt-3 custom-text-overflow"><a href="<c:url value="/product/product_page?p_id=${list.p_id}"/>">${list.p_title}</a></div>
		            <div class="price custom-text-overflow"><small>판매가 : </small><strong><fmt:formatNumber value="${list.p_price}" pattern="#,###"/>원</strong></div>
		            <div class="date custom-text-overflow">${list.p_date}</div>
				</div>
			</c:forEach>
		</div>
	</div>
  </div>
  </div>
