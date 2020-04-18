<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>

<div id="container_cover" class="custom-page-view">
  <div class="container">
 
    <div class="jumbotron text-center" style="margin-top: 2rem;">
      <h3 class="display-4"><c:out value="${userName}"/>님은 가입 대기상태입니다.</h3>
      <p style="margin-top:50px;">중고거래사이트를 이용하시려면 인증 메일을 확인해주세요!</p>
      <p>인증메일은 회원가입시 입력한 <c:out value="${email}"/>로 발송되었습니다.</p>
    </div>

  </div>
</div>