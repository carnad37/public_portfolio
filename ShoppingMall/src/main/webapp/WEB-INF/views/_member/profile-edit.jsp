<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!-- Container -->
<div id="content-wrapper">
  <div class="d-flex container-fluid h-100">
    <div class="card align-self-center w-100 border-0 shadow">
      <div class="card-header bg-primary text-white border-0">
        <i class="fas fa-table"></i>
        	개인정보 수정
      </div>
      <div class="form-group mb-0">
      	<ul class="list-group">
		<li class="list-group-item py-5 border-bottom-0 border-left-0 border-right-0">
		<form action="<c:url value="/profile/edit_process"/>" method="POST">
		<div class="col-md-8 mx-auto">
		<div class="card-body">
        <div class="form-group">
           <div class="form-row">
             <div class="col-md-6">
                <div class="form-label-group">
                  <input type="text" id="nickName" name="u_nick" class="form-control" placeholder="닉네임" required="required" value="${userData.u_nick}">
                  <label for="nickName">닉네임</label>
			      <div class="invalid-feedback">
			        	닉네임을 입력해주세요.
			      </div>
			      <div class="valid-feedback">
			        	등록 가능한 닉네임입니다.
			      </div>
                </div>
              </div>
              <div class="col-md-3">
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
		 </div>
         <div class="card-body">
         <div class="form-group">
            <div class="form-row">
              <div class="col-md-6">
                <div class="form-label-group">
                  <input type="text" id="userName" name="u_name" class="form-control" placeholder="이름" required="required" value="${userData.u_name}">
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
                  <input type="text" id="phone" name="u_phone" class="form-control" placeholder="이름" required="required" value="${userData.u_phone}">
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
                  <input type="text" id="zoneCode" name="u_zipcode" class="form-control" placeholder="우편번호" readonly value="${userData.u_zipcode}">
                  <label for="">우편번호</label>
                  <div class="invalid-feedback">
						우편주소를 검색해주세요.
			 	  </div>
                </div>
              </div>
              <div class="col-md-4">
              	<button class="btn btn-info btn-block modal-heigth" type="button" onclick="searchAddress()">우편번호 검색</button>
              </div>
            </div>
         </div>
         <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="firstAddress" name="u_addr" class="form-control" placeholder="기본 주소" readonly value="${userData.u_addr}">
              <label for="">기본 주소</label>

            </div>
          </div>
          <div class="form-group">
            <div class="form-label-group">
              <input type="text" id="secondAddress" name="u_addr_detail" class="form-control" placeholder="상세 주소" required="required"  value="${userData.u_addr_detail}">
              <label for="secondAddress">상세 주소</label>
              <div class="invalid-feedback">
					상세주소를 입력해주세요.
			  </div>
            </div>
          </div>
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <button class="btn btn-primary btn-block" type="button" onclick="profileEdit()">수정하기</button>
          <input class="d-none" id="submitButton" type=submit>
          </div>
			</div>
			</form>
      	</li>
        </ul>
      </div>
    </div>
  </div>
</div>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script src="<c:url value="/resources/admin/js/member_script.js" />"></script>
<script>
	function profileEdit() {
		console.log("Test");
		confirmCall("정말 수정하시겠습니까?", signUpButton, "개인정보 수정");
	}

</script>
