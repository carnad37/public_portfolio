<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <script src="<c:url value="/resources/admin/vendor/jquery-easing/jquery.easing.min.js"/>"></script>
  <script>
  $(document).ready(function() {
	  if($('#content-wrapper').innerHeight() > 800) {
	      $('#container_cover').removeClass('custom-page-view');
	  }	 
  });
  </script>