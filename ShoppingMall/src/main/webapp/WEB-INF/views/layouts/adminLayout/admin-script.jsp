<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

  <!-- 사이드바 조절 자바스크립트 -->
  <script src="<c:url value="/resources/admin/js/sb-admin.min.js"/>"></script>

  <script>
  $(document).ready(function() {
	  if($('#content-wrapper').innerHeight() + 200 < 800) {
	      $('#container_cover').addClass('custom-page-view');
	  }
	  $('#controlSideBar').click(function() {
		  if ($('body').width() > 768) {
			  $('.sidebar').toggleClass('custom-side-width');
			  $('.sidebar').toggleClass('custom-side-control');
			  $('#controlSideBar').children('i').toggleClass('fa-angle-right');
			  $('#controlSideBar').children('i').toggleClass('fa-angle-left');
		  }		  
	  });
  });
  </script>
    