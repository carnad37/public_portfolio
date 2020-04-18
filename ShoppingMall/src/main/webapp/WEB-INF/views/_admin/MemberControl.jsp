<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<div id="content-wrapper">
  <div class="d-flex container-fluid h-100">
    <!-- DataTables Example -->
    <div class="card align-self-center w-100 border-0 shadow">
      <div class="card-header bg-primary text-white border-0">
        <i class="fas fa-table"></i>
        	회원 관리
      </div>
      <div class="form-group mb-0">
      	<ul class="list-group">
		<li class="list-group-item py-5 border-bottom-0 border-left-0 border-right-0">
			<form id="member-search-form" action="/admin/member/loadSel" method="POST">
			<input type="hidden" name="page">
   			<div class="col-md-10 mx-auto">
   				<p class="font-weight-bold">검색 조건을 입력해주세요.</p>
   				<p class="mb-1">아이디</p>
   				<div class="row">
	   				<div class="col-md-10">
	   				 	<input class="form-control" type="text" name="u_account" disabled>
	   				</div>			
	 				<div class="col-md-2">
	  				    <label class="switch mb-0" style="margin-top: 2px;">
						  <input type="checkbox" onclick="inputControl(this)">
						  <span class="slider round"></span>
						</label>
	 				</div>
 				</div>

 				<p class="mb-1">닉네임</p>
   				<div class="row">
	   				<div class="col-md-10">
	   				 	<input class="form-control" type="text" name="u_nick" disabled>
	   				</div>			
	 				<div class="col-md-2">
	  				    <label class="switch mb-0" style="margin-top: 2px;">
						  <input type="checkbox" onclick="inputControl(this)">
						  <span class="slider round"></span>
						</label>
	 				</div>
 				</div>
 				<p class="mb-1">가입일</p>
   				<div class="row">
	   				<div class="col-md-5">
	   				 	<input class="form-control" type="date" name="u_date_start" disabled>
	   				</div>
	   				<div class="col-md-5">
	   					<input class="form-control" type="date" name="u_date_end" disabled>
	   				</div> 				
	 				<div class="col-md-2">
	  				    <label class="switch mb-0" style="margin-top: 2px;">
						  <input type="checkbox" onclick="inputControl(this)">
						  <span class="slider round"></span>
						</label>
	 				</div>
 				</div>
 				<p class="mb-1">이메일</p>
   				<div class="row">
	   				<div class="col-md-10">
	   				 	<input class="form-control" type="text" name="u_mail" disabled>
	   				</div>		
	 				<div class="col-md-2">
	  				    <label class="switch mb-0" style="margin-top: 2px;">
						  <input type="checkbox" onclick="inputControl(this)">
						  <span class="slider round"></span>
						</label>
	 				</div>
 				</div>
 				<hr>
 				<button id="member-search-button" type="button" class="btn btn-primary w-100">설정한 조건으로 검색하기</button>
			</div>  
			</form>
      	</li>
		<li class="list-group-item py-5 border-bottom-0 border-left-0 border-right-0 border-top">
			<div class="container" style="margin-top:20px; margin-bottom:20px;">  
			  	<table class="table table-hover" style="text-align:center;">
			  		<tr>
			  			<th class="align-middle" style="width: 10%; border-top: none;">
			  				아이디
			  			</th>
			  			<th class="align-middle" style="width: 20%; border-top: none;">
			  				닉네임
			  			</th>
			  			<th class="align-middle" style="width: 20%; border-top: none;">
			  				이메일
			  			</th>
			  			<th class="align-middle" style="width: 15%; border-top: none;">
			  				이름
			  			</th>
			  			<th class="align-middle" style="width: 10%; border-top: none;">
			  				전화번호
			  			</th>
			  			<th class="align-middle" style="width: 10%; border-top: none;">
			  				
			  			</th>
			  		</tr>
			  		<c:forEach begin="1" end="10">
			  		<tr class="print-tr">
			  		     <c:forEach begin="1" end="6">
			  		     <td class="align-middle"></td>
			  		     </c:forEach>
			  		</tr>	
			  		</c:forEach>
			  	</table>
			  	<ul class="pagination" style="justify-content: center;">
					<li class="page-item"><button class="page-link" href="#" onclick="pagingClick(this)">Previous</button></li>
				</ul>
			  </div>

  <script>

  let uploadData = null;
  let targetSet = "loadSel";
  var nowPage = 0;
  var currentTabRows = 0;
  var cur_user = 1; 

  function makePaging() {
		$('.pagination').children().remove();
		$('.pagination').append(('<li class="page-item"><button class="page-link" href="#" onclick="pagingPrevious()">Previous</button></li>'));

		if (currentTabRows <= 10) {
			$('.pagination').append(('<li class="page-item"><button class="page-link" href="#">1</button></li>'));
			$('.pagination').append(('<li class="page-item"><button class="page-link" href="#">Next</button></li>'));
		} else {
			for (let i = 1; i <= (currentTabRows / 10) + 1; i++) {
				$('.pagination').append($('<li class="page-item"><button class="page-link" href="#" onclick="pagingClick(this)">' + i + '</button></li>'));
			}
			$('.pagination').append(('<li class="page-item"><button class="page-link" href="#" onclick="pagingNext()">Next</button></li>'));
	    }	    
  }
  
  function makePost() {
	  $('td').empty();
	  $.ajax({
			type : "GET",
			url : "<c:url value="/admin/member/"/>" + targetSet,
			data: uploadData,
			beforeSend : function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
				},
			success : function(data) {
				console.log(data);
				//받아온 data의 길이가 10개보다 안될때.				
				if (data.userList == null) {
					return;
				}				
				if (nowPage % 10 == 0) {
					currentTotalRows = data.totalRows;
					makePaging();
				}
				data = data.userList;
				for (let index = 0; index < data.length; index++) {
					var $tr = $('.print-tr').eq(index).children('td');

					if (data[index].u_id == -1) {
						$tr.eq(0).text("\u00a0");
						continue;
					}		
					
					$tr.eq(0).text(data[index].u_account);
					$tr.eq(1).text(data[index].u_nick);
					$tr.eq(2).text(data[index].u_mail);
					$tr.eq(3).text(data[index].u_name);
					$tr.eq(4).text(data[index].u_phone);	
					$tr.eq(5).append('<button class="btn btn-primary" onclick="userEditControl(this)" type="button">수정</button>');
					var $input = $('<input type="hidden" name="u_id" class="btn btn-primary" type="button">');
					$input.val(data[index].u_id);
					$tr.eq(5).append($input);
				}								
				delete data;
				pageActiveChange();
				
			},
			error:function(request,status,error){
		        alertCall("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
	  });
  }

  function pagingClick(obj) {
	  	nowPage = parseInt($(obj).text()) - 1;
	  	makePost();
  }

  function pagingPrevious() {
		if (nowPage != 0){
			nowPage--;
		  	makePost();		
		}		
  }

  function pageActiveChange() {
	    var $li = $('.pagination').children('li');
	    $li.removeClass('active');
	    $li.eq(nowPage + 1).addClass('active');
  }

  function pagingNext() {
	  if (currentTabRows != 0 && nowPage + 1 < Math.floor(currentTabRows / 10 + 1)){
			nowPage++;
		  	makePost();		
	  }
  }

  function inputControl(obj) {
	  const result = $(obj).prop('checked');
	  $(obj).closest('.row').find('.form-control').attr('disabled', !result);
  }

  //=====================//

   $(document).ready(function() {    	 
	   makePost();
       $('#container_cover').removeClass('custom-page-view');

       $('#member-search-button').click(function(){
    	   $('input[name=page]').val(nowPage);
    	   uploadData = $('#member-search-form').serialize();
    	   targetSet = "loadSel";
    	   makePost();
       });
   });
  


  //=====================//  
  

  
	function userEditControl(obj) {
		const targetId = $(obj).siblings('input').val();
		$.ajax({
			type : "GET",
			url : "<c:url value="/admin/member/userInfo"/>",
			data: {"u_id" : targetId},
			beforeSend : function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
				},
			success : function(data) {
				console.log(data);
				$('#nickName').val(data.u_nick);
				$('#inputPassword').val();
				$('#confirmPassword').val();
				$('#inputEmail').val(data.u_mail);

				$('#userName').val(data.u_name);
				$('#phone').val(data.u_phone);
				$('#zoneCode').val(data.u_zipcode);
				$('#firstAddress').val(data.u_addr);
				$('#secondAddress').val(data.u_addr_detail);

				$('#submit-u_id').val(targetId);
				
				$('#edit-user-info').modal('show');
				
			},
			error:function(request,status,error){
		        alertCall("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
	    });
	}
    
  </script>
		</li>
        </ul>
      </div>
    </div>
  </div>
</div>

<!-- 회원정보 수정 모달 -->
<div class="modal fade" id="edit-user-info" tabindex="-1" role="dialog" aria-labelledby="edit-user-info-label" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 750px;">
	  <div class="modal-content">
  		<div class="modal-header card-header">
		  <h4 class="modal-title" id="edit-user-info-label" style="margin: 0 auto; padding-left: 42px;">회원 가입</h4>
		  <button type="button" class="close" data-dismiss="modal" style="margin-left: 0px;"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		</div>
		<form id="signUp-form" action="<c:url value="/admin/member/edit_process"/>" method="POST">
      <div class="card-body">
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
          <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
          <button class="btn btn-primary btn-block" type="button" onclick="signUpButton()">등록하기</button>
          <input class="d-none" id="submitButton" type=submit>
          <input id="submit-u_id" name="u_id" type="hidden">
          </div>
        </form>
    </div>
  </div>
</div>
<script src="<c:url value="/resources/admin/js/member_script.js" />"></script>
