<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!--

Copyright (c) YEAR - YOUR NAME - URL TO ORIGINAL

Permission is hereby granted, free of charge, to any person
obtaining a copy of this software and associated documentation
files (the "Software"), to deal in the Software without restriction,
 including without limitation the rights to use, copy, modify,
merge, publish, distribute, sublicense, and/or sell copies of
the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall
be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES
OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT
HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
DEALINGS IN THE SOFTWARE.

-->
<sec:authorize access="isAnonymous()">
<script>
	const sign_error = 0${sign_error};
	$(function(){
		if (sign_error == 1) {
			confirmCall("로그인이 필요한 기능입니다.<br> 로그인 하시겠습니까?",loadSignInModal, "로그인 요청");
		}		  
	});
	
	function loadSignInModal() {
		$('#sign-in').modal('show');
	}
</script>
<!-- 로그인 -->
<input type="hidden" value="false"/>
<div class="modal fade" id="sign-in" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" style="max-width: 400px;">
	  <div class="modal-content">
	  <div class="modal-header card-header">
		  <h4 class="modal-title" id="myModalLabel" style="margin: 0 auto; padding-left: 42px;">로그인</h4>
		  <button type="button" class="close" data-dismiss="modal" style="margin-left: 0px;"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  </div>
      <div class="card-body">
        <form action="<c:url value="/sign_in"/>" method="post">
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="login_id" name="login_id" class="form-control" placeholder="Email address" required="required" autofocus="autofocus">
              <label for="login_id">ID</label>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="password" id="login_pw" name="login_pw" class="form-control" placeholder="Password" required="required">
              <label for="login_pw">Password</label>
            </div>
          </div>
          <div class="form-group">
            <div class="checkbox">
              <label class="small">
                <input class="align-middle" name ="remember-me" type="checkbox"/>
                	로그인 유지(일주일)
              </label>
            </div>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <button class="btn btn-primary btn-block" type="submit">Login</button>
        </form>
        <div class="text-center">

          <a class="d-block small mt-3" href="ad_register.html">회원 가입</a>
          <a class="d-block small" href="ad_forgot-password.html">비밀번호를 잊으셨나요?</a>
        </div>
      </div>
 	 </div> <!-- 모달 콘텐츠 -->
   </div> <!-- 모달 다이얼로그 -->
</div> <!-- 모달 전체 윈도우 -->

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="<c:url value="/resources/admin/js/member_script.js" />"></script>

<!-- 회원가입 -->
<div class="modal fade" id="sign-up" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 750px;">
	  <div class="modal-content">
  		<div class="modal-header card-header">
		  <h4 class="modal-title" id="myModalLabel" style="margin: 0 auto; padding-left: 42px;">회원 가입</h4>
		  <button type="button" class="close" data-dismiss="modal" style="margin-left: 0px;"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		</div>
		<form id="signUp-form" action="<c:url value="/member/sign_up"/>" method="GET">
      <div class="card-body">
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="text" id="account" name="u_account" class="form-control" placeholder="아이디" required="required" autofocus="autofocus">
                  <label for="account">아이디</label>
			      <div class="invalid-feedback">
			        	아이디를 입력해주세요.
			      </div>
			      <div class="valid-feedback">
			        	등록 가능한 아이디입니다.
			      </div>
                </div>
              </div>
              <div class="col-md-2">
              	<button class="btn btn-info btn-block modal-heigth" type="button" onclick="idChecker()">중복확인</button>
              </div>
            </div>
          </div>
        <div class="form-group">
           <div class="form-row">
             <div class="col-md-6">
                <div class="form-label-group">
                  <input type="text" id="nickName" name="u_nick" class="form-control" placeholder="닉네임" required="required">
                  <label for="nickName">닉네임</label>
			      <div class="invalid-feedback">
			        	닉네임을 입력해주세요.
			      </div>
			      <div class="valid-feedback">
			        	등록 가능한 닉네임입니다.
			      </div>
                </div>
              </div>
              <div class="col-md-2">
              	<button class="btn btn-info btn-block modal-heigth" type="button" onclick="nickChecker()">중복확인</button>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="password" id="inputPassword" name="u_pw" class="form-control" placeholder="Confirm password" required="required">
                  <label for="inputPassword">비밀번호</label>
                  <div class="invalid-feedback">
			        	특수기호를 포함시켜주세요<br>(&lt;, >, (, ), #, ', /, | 제외)

			      </div>
                </div>
              </div>
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="password" id="confirmPassword" name="u_confirmPw" class="form-control" placeholder="Confirm password" required="required">
                  <label for="confirmPassword">비밀번호 확인</label>
                  <div class="invalid-feedback">
						입력된 비밀번호와 다릅니다.
			      </div>
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="email" id="inputEmail" name="u_mail" class="form-control" placeholder="Email address" required="required">
              <label for="inputEmail">이메일 주소</label>
              <div class="invalid-feedback">
						이메일 주소를 입력해주세요.
			  </div>
            </div>
          </div>
         </div>
         <div class="card-body">
         <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="text" id="userName" name="u_name" class="form-control" placeholder="이름" required="required">
                  <label for="userName">이름</label>
                  <div class="invalid-feedback">
						이름을 입력해주세요.
			  	  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="text" id="phone" name="u_phone" class="form-control" placeholder="이름" required="required">
                  <label for="phone">전화번호</label>
                  <div class="invalid-feedback">
						전화번호를 입력해주세요
			      </div>
                </div>
              </div>
            </div>
          </div>
          <div class="form-group">
            <div class="form-row">
              <div class="col-md-3">
                <div class="form-label-group">
                  <input type="text" id="zoneCode" name="u_zipcode" class="form-control" placeholder="우편번호" readonly>
                  <label for="">우편번호</label>
                  <div class="invalid-feedback">
						우편주소를 검색해주세요.
			 	  </div>
                </div>
              </div>
              <div class="col-md-3">
              	<button class="btn btn-info btn-block modal-heigth" type="button" onclick="searchAddress()">우편번호 검색</button>
              </div>
            </div>
         </div>
         <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="firstAddress" name="u_addr" class="form-control" placeholder="기본 주소" readonly>
              <label for="">기본 주소</label>

            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="secondAddress" name="u_addr_detail" class="form-control" placeholder="상세 주소" required="required">
              <label for="secondAddress">상세 주소</label>
              <div class="invalid-feedback">
					상세주소를 입력해주세요.
			  </div>
            </div>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="`" />
          <button class="btn btn-primary btn-block" type="button" onclick="signUpButton()">등록하기</button>
          <input class="d-none" id="submitButton" type=submit>

        <div class="text-center">
          <a class="d-block small mt-3" href="ad_login.html">로그인</a>
          <a class="d-block small" href="ad_forgot-password.html">비밀번호를 잊으셨나요?</a>
        </div>
          </div>
        </form>
    </div>
  </div>
</div>
<!-- 모달 콘텐츠 -->

</sec:authorize>
<sec:authorize access="isAuthenticated()">
<!-- Modal -->
<div class="modal fade" id="sign-out" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog" style="max-width: 500px;">
	  <div class="modal-content">
	  <div class="modal-header card-header">
		  <h4 class="modal-title" id="myModalLabel" style="margin: 0 auto; padding-left: 42px;">정말 로그아웃 하시겠습니까?</h4>
		  <button type="button" class="close" data-dismiss="modal" style="margin-left: 0px;"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	  </div>
	      <div class="card-body">
			  <div class="row mx-auto">
			  	<div class="col-lg-6">
<!-- 			  	    <form action="/main/sign_out" method="POST"> -->
			  	    <form action="<c:url value="/profile/sign_out"/>" method="POST">
				        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				        <button class="btn btn-primary btn-block" type="submit">네</button>
				    </form>
			  	</div>
	 	        <div class="col-lg-6">
				   <a href="#sign-out" data-toggle="modal" class="btn btn-secondary btn-block">아니요</a>
			    </div>
		      </div>
	      </div>
 	  </div>
   </div>
</div>
</sec:authorize>

<nav class="navbar fixed-top navbar-expand-lg navbar-dark bg-white fixed-top shadow">
  <div class="nav-user">
  </div>
  <div class="row container mx-auto">
  	<div class="col-md-3" id="main-mark">
 		<img src="<c:url value="/resources/main/images/mark/test_mark.png"/>" width="100%" onclick="location.href='<c:url value="/"/>'" style="cursor:pointer;">
  	</div>
  	<div class="col-md-6 text-center">
		<form id="product-search-form" class="custom-search-form custom-radius" action="<c:url value="/product/search_list"/>" method="GET">
		 <div class="custom-search-bar input-group">
		  <select id="category-select" name="c_id" class="custom-select col-md-4 border-0 font-weight-bold custom-delete-boxshadow">
		    <option value="0" selected>모든 카테고리</option>
		  </select>
		  <input class="form-control border-0 custom-delete-reset custom-delete-boxshadow" name="target_word" type="search" placeholder="검색어를 입력해주세요">		  
		  <input type="hidden" name="pagenum" value="1">
		  <input type="hidden" name="contentnum" value="10">
		</div>
		  <i class="fa custom-fa fa-search" onclick="$('#product-search-form').submit();"></i>
		</form>
  	</div>
  	<div class="col-md-3 text-center">
  		<div class="row">
  		  <div class="col-6 custom-display-button">
  		  <div class="row">
			  <sec:authorize access="hasRole('ROLE_USER')">
				  <div class="col p-0">
				    <button class="custom-radius custom-header-button custom-delete-focus" onclick="location.href='<c:url value="/product/call_product"/>'">
						<i class="fas fa-store custom-fa-position"></i>
					</button>
				  </div>
				  <div class="w-100"></div>				  
				  <div class="col p-0 custom-menu-font-size mt-1">물품 판매</div>
			  </sec:authorize>

	  		</div>
  		  </div>
  		  <div class="col-6">
  		  	<div class="row">
  		  	  <sec:authorize access="isAnonymous()">
	  		  	  <div class="col p-0">  		  	  
				  	<button class="custom-radius custom-header-button custom-delete-focus" data-toggle="modal" data-target="#sign-in">
						<i class="fas fa-sign-in-alt custom-fa-position"></i>
					</button>
				  </div>
				  <div class="col p-0">
				    <button class="custom-radius custom-header-button custom-delete-focus" data-toggle="modal" data-target="#sign-up">
						<i class="fas fa-user custom-fa-position"></i>
					</button>
				  </div>
				  <div class="w-100"></div>				  
				  <div class="col p-0 custom-menu-font-size mt-1">로그인</div>
				  <div class="col p-0 custom-menu-font-size mt-1">회원가입</div>
			  </sec:authorize>
			  <sec:authorize access="hasRole('ROLE_USER')">
				  <div class="col p-0">  		  	  
				  	<button class="custom-radius custom-header-button custom-delete-focus" onclick="location.href='<c:url value="/profile/view"/>'">
						<i class="fas fa-user custom-fa-position"></i>
					</button>
				  </div>
				  <div class="col p-0">
				    <button class="custom-radius custom-header-button custom-delete-focus" data-toggle="modal" data-target="#sign-out">
						<i class="fas fa-sign-out-alt custom-fa-position"></i>
					</button>
				  </div>
				  <div class="w-100"></div>				  
				  <div class="col p-0 custom-menu-font-size mt-1">회원정보</div>
				  <div class="col p-0 custom-menu-font-size mt-1">로그아웃</div>
			  </sec:authorize>
			  <sec:authorize access="hasRole('ROLE_ADMIN')">
				  <div class="col p-0">  		  	  
				  	<button class="custom-radius custom-header-button custom-delete-focus" onclick="location.href='<c:url value="/admin/"/>'">
						<i class="fas fa-tools custom-fa-position"></i>
					</button>
				  </div>
				  <div class="col p-0">
				    <button class="custom-radius custom-header-button custom-delete-focus" data-toggle="modal" data-target="#sign-out">
						<i class="fas fa-power-off custom-fa-position"></i>
					</button>
				  </div>
				  <div class="w-100"></div>				  
				  <div class="col p-0 custom-menu-font-size mt-1">관리자 메뉴</div>
				  <div class="col p-0 custom-menu-font-size mt-1">로그아웃</div>
			  </sec:authorize>
	  		</div>
  		  	</div>
  		  </div>
     </div>
  	</div>
</nav>
<script type="text/javascript">
// window.onload = function(){
// 	$('.custom-fa').addClass('add-transition');
// 	$('.custom-header-button-small').addClass('add-transition');
// 	$('.custom-header-button').addClass('add-transition');	
// };
    const csrf_name = "${_csrf.headerName}";
    const csrf_token = "${_csrf.token}";
	$(document).ready(function() {
		$('.custom-fa').addClass('add-transition');
		$('.custom-header-button-small').addClass('add-transition');
		$('.custom-header-button').addClass('add-transition');	
		$.ajax({
			type : "GET",
			url : "<c:url value="/getCategory"/>",
	     	dataType : 'json',
			beforeSend : function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
				},
			success : function(data) {
				for (let index=0; index < data.length; index++) {
					let $option = $('<option/>');
					$option.text(data[index].c_name);
					$option.attr('value', data[index].c_id);
					$('#category-select').append($option);
				}					
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		});
	});
</script>