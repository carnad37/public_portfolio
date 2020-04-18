<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"  %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Sidebar -->
<sec:authorize access="hasRole('ROLE_ADMIN')">
<ul class="sidebar custom-side-control navbar-nav">
  <li class="nav-item active">
    <a class="nav-link" href="<c:url value="/admin/"/>">
      <i class="fas fa-fw fa-tachometer-alt"></i>
      <span>대시보드</span>
    </a>
  </li>
  <li class="nav-item">
    <a class="nav-link" data-toggle="collapse" href="#siteConfCollapse" role="button" aria-expanded="false" aria-controls="siteConfCollapse">
      <i class="fas fa-fw fa-table"></i>
      <span>사이트 관리</span></a>
  </li>
  <!--collapse메뉴 -->
  <li class="nav-item collapse" id="siteConfCollapse" style="background-color: white; color: black;">
    <a class="nav-link pl-4" href="<c:url value="/admin/category/view"/>" style="color: black;">
      <small>
      <i class="fas fa-fw fa-table"></i>      
    	 카테고리 설정
      </small>
     </a>
    <a class="nav-link pl-4" href="<c:url value="/admin/pic/view"/>" style="color: black;">
      <small>
      <i class="fas fa-fw fa-table"></i>      
    	 배너 설정
      </small>
      </a>
  </li>
  <li class="nav-item">
    <a class="nav-link" data-toggle="collapse" href="#info-control-collapse" role="button" aria-expanded="false" aria-controls="info-control-collapse">
      <i class="fas fa-fw fa-table"></i>
      <span>회원 관리</span></a>
  </li>
  <!--collapse메뉴 -->
  <li class="nav-item collapse" id="info-control-collapse" style="background-color: white; color: black;">
    <a class="nav-link pl-4" href="<c:url value="/admin/member/view"/>" style="color: black;">
      <small>
      <i class="fas fa-fw fa-table"></i>      
    	 회원 관리
      </small>
     </a>
    <a class="nav-link pl-4" href="#" style="color: black;">
      <small>
      <i class="fas fa-fw fa-table"></i>      
    	 거래 관리(미구현)
      </small>
      </a>
  </li>
  <li class="nav-item text-center p-3">
    <button class="custom-radius-small custom-header-button-small custom-delete-focus border-white" type="button" id="controlSideBar">
      <i class="fas fa-angle-left pb-1"></i>
    </button>
  </li>
</ul>
</sec:authorize>
<sec:authorize access="hasRole('ROLE_USER')">
<ul class="sidebar custom-side-control navbar-nav">
  <li class="nav-item active">
    <a class="nav-link" href="<c:url value="/profile/view"/>">
      <i class="fas fa-fw fa-tachometer-alt"></i>
      <span>대시보드</span>
    </a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="ad_charts">
      <i class="fas fa-fw fa-chart-area"></i>
      <span>공지사항</span></a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="<c:url value="/board/QnA_view"/>">
      <i class="fas fa-fw fa-table"></i>
      <span>상담하기</span></a>
  </li>
  <li class="nav-item">
    <a class="nav-link" data-toggle="collapse" href="#dealCollapse" role="button" aria-expanded="false" aria-controls="dealCollapse">
      <i class="fas fa-fw fa-table"></i>
      <span>거래 현황</span></a>
  </li>
  <!--collapse메뉴 -->
  <li class="nav-item collapse" id="dealCollapse" style="background-color: white; color: black;">
    <a class="nav-link pl-4" href="<c:url value="/profile/buy"/>" style="color: black;">
      <small>
      <i class="fas fa-fw fa-table"></i>      
    	 구매 현황
      </small>
     </a>
    <a class="nav-link pl-4" href="<c:url value="/profile/sell"/>" style="color: black;">
      <small>
      <i class="fas fa-fw fa-table"></i>      
    	 판매 현황
      </small>
      </a>
  </li>
  
  <li class="nav-item">
    <a class="nav-link" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample">
      <i class="fas fa-fw fa-table"></i>
      <span>회원 정보</span>
    </a>
  </li>
  <!--collapse메뉴 -->
  <li class="nav-item collapse" id="collapseExample" style="background-color: white; color: black;">
    <a class="nav-link pl-4" href="<c:url value="/profile/edit"/>" style="color: black;">
      <small>
      <i class="fas fa-fw fa-table"></i>      
    	  정보 수정
      </small>
    </a>
    <a class="nav-link pl-4" data-toggle="modal" href="#member-leave-modal" style="color: black;">
      <small>
      <i class="fas fa-fw fa-table"></i>      
    	  회원탈퇴
      </small>
    </a>
  </li>
  <li class="nav-item text-center p-3">
    <button class="custom-radius-small custom-header-button-small custom-delete-focus border-white" type="button" id="controlSideBar">
      <i class="fas fa-angle-left pb-1"></i>
    </button>
  </li>
</ul>
<!-- 회원 탈퇴 모달 추가 -->
<div class="modal fade" id="member-leave-modal" tabindex="-1" role="dialog" aria-labelledby="member-leave-modal-title" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content modal-cover">
      <div class="modal-header card-header bg-info">
        <h5 class="modal-title text-light" id="member-leave-modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="card-body font-weight-bold">
        	정말 탈퇴하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick='location.href="<c:url value="/profile/leave"/>'>예</button>
		<button type="button" class="btn btn-secondary" data-dismiss="modal">아니오</button>	      
      </div>
    </div>
  </div>
</div>
</sec:authorize>