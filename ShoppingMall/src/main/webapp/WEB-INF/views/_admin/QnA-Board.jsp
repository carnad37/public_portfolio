<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<div id="container_cover">
<sec:authorize access="isAuthenticated()">
<script>
	$(function(){
		$('#height-controller').click(function(){
			$('#reply-delete-cancel').toggleClass('d-none');	
			$('#reply-delete-cancel').toggleClass('d-block');	
			$('.custom-kakao-background').toggleClass('custom-card-height');
			$('.custom-kakao-background').toggleClass('custom-long-view-top');
			$('#height-controller').parent('div').toggleClass('custom-long-view-bottom');
		});	

		$('#reply-delete-cancel').click(function(){
			confirmCall("정말 삭제하시겠습니까?", deleteReply);
		});

		$('#reply-delete-cancel').click(function(){
			confirmCall("정말 삭제하시겠습니까?", deleteReply);
		});

		$('#reply-sumbit-button').click(function(){
			$('#control-reply-form').attr('action', '<c:url value="/board/QnA_addReply"/>');
			$('#control-reply-form').submit();
		});
	});

	
	function deleteReply() {
		$('#control-reply-form').attr('action', '<c:url value="/board/QnA_del"/>');
		$('#control-reply-form').submit();
	}
</script>

<div class="modal fade" id="talk-box" tabindex="-1" role="dialog" aria-labelledby="talk-box-label" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 750px;">
	  <div class="modal-content">
  		<div class="modal-header card-header">
		  <h4 class="modal-title" id="talk-box-label" style="margin: 0 auto; padding-left: 42px;">회원 가입</h4>
		  <button type="button" class="close" data-dismiss="modal" style="margin-left: 0px;"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		</div>
      <div class="card-body custom-card-height custom-kakao-background">
      <div class="talk_contatiner border-right-0">
<!--         <div class="talk_bubble_left"> -->
<!--            <div class="talk_texts"> -->
<!--            		테스트용입니다.                        -->
<!--            </div> -->
<!--         </div>       -->
<!--         <div class="talk_bubble_right"> -->
<!--           <div class="talk_texts"> -->
<!-- 				테스트용입니다. -->
<!--           </div>      -->
<!--         </div> -->
    </div>    
  	  </div>
  	  <form id="control-reply-form" class="m-0" method="POST">
  	  <div class="modal-footer">
  	    	<sec:authorize access="hasRole('ROLE_ADMIN')">
  	  			<textarea name="ans_text" class="form-control w-75 custom-secret-button" rows="1"></textarea>
  	    	</sec:authorize>
			<sec:authorize access="hasRole('ROLE_USER')">
  	 			<textarea class="form-control w-75 custom-secret-button" rows="1" disabled></textarea>
  	    	</sec:authorize>
  	    <button id="height-controller" type="button" class="btn"><i class="fas fa-arrows-alt-v"></i></button> 
  	    <div>
			<sec:authorize access="hasRole('ROLE_ADMIN')">
  	    		<button id="reply-sumbit-button" type="button" class="btn btn-primary">댓글 등록</button>
  	    	</sec:authorize>
			<sec:authorize access="hasRole('ROLE_USER')">
  	    		<button type="button" class="btn btn-secondary">답변 대기</button>
  	    	</sec:authorize>
<!--   	    	<button id="reply-edit-button" type="button" class="btn btn-primary d-none">질문 삭제</button> -->
  	    	<button id="reply-delete-cancel" type="button" class="btn btn-danger mt-3 mx-auto d-none">질문 삭제</button>
    	    <input type="hidden" name="que_id">
    	    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
  	    </div>
	 	</div>
	 	</form>
    </div>
  </div>
</div>

<!-- Modal -->
<div class="modal fade" id="writePost" tabindex="-1" role="dialog" aria-labelledby="writePostLabel" aria-hidden="true">
	<div class="modal-dialog" style="max-width: 750px;">
	  <form action="<c:url value="/board/QnA_create"/>" method="POST">
	  <div class="modal-content">
		<div class="modal-header card-header">
		  <h4 class="modal-title" id="writePostLabel" style="margin: 0 auto; padding-left: 42px;">관리자에게 질문하기</h4>
		  <button type="button" class="close" data-dismiss="modal" style="margin-left: 0px;"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		</div>
		<div class="modal-header">
		  <h6>관리자전달 사항</h6>
		</div>
		<div class="modal-body">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">질문</span>
				</div>
				<input name="que_title" type="text" class="form-control"><br/>
			</div>
		</div>
		<div class="modal-body"  style="padding-top: 0px;">
			<div class="input-group">
				<div class="input-group-prepend">
					<span class="input-group-text">내용</span>
				</div>
				<textarea name="que_text" class="form-control" rows="15" style="resize: none;"></textarea>
			</div>
		</div>
		<div class="modal-footer">
		  <div class="custom-secret-button w-50 row">
		  	<div>
		  		<button class="btn font-weight-bold" type="button" onclick="$('input[name=secret-control]').click()">비밀글 여부</button>
		  	</div>
		  	<div class="col-5">
			<label class="switch mb-0" style="margin-top: 2px;">
			  <input name="secret-control" type="checkbox" value="secret">
			  <span class="slider round"></span>
			</label>		
			</div>
		  </div>
		  <button type="button" class="btn btn-secondary" data-dismiss="modal">취소</button>
		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		  <button type="submit" class="btn btn-primary">게시물 등록</button>
		</div>
 	 </div> <!-- 모달 콘텐츠 -->.
 	 </form>
   </div> <!-- 모달 다이얼로그 -->
</div> <!-- 모달 전체 윈도우 -->
</sec:authorize>

<div class="custom-page-view">  
 <div class="container h-100 d-table">
  <div class="d-table-cell align-middle mx-auto">  
	  <div class="border-bottom border-dark mb-4" style="border-width: 5px;">
	  	<ul class="nav nav-tabs">
         <li class="nav-item">
         	<h2 onclick="$('#talk-box').modal('show')">상담 게시판</h2>
         </li>
         <li class="nav-item custom-search-width ml-auto">
         <form id="search-form" method="get"> 
          	<div class="input-group pt-2">
				<div class="custom-search-bar input-group">	
				  <select name="search-option" class="custom-select col-md-4 border-0 font-weight-bold custom-delete-boxshadow">
				    <option value="0" selected>제목+내용</option>
				    <option value="1">제목</option>
				    <option value="2">내용</option>
				  </select>
				  <input id="now-page" name="page" type="hidden" value="0">
				  <input name="search-target" class="form-control border-0 custom-delete-reset custom-delete-boxshadow pr-0" type="search" placeholder="검색어를 입력해주세요">		  
				  <i class="fas custom-fa fa-search fa-lg text-dark custom-search-button"></i>
				</div> 
          	</div>
         </form>
         </li>
     </ul>
	  </div>
  	  <ul class="nav nav-tabs">
         <li class="nav-item">
         	<a class="nav-link active" href="#" onclick="boardTabClick(this)" title="public">공개 질문</a>
         </li>
         <sec:authorize access="isAuthenticated()">      
         <li class="nav-item">
         	<a class="nav-link" href="#" onclick="boardTabClick(this)" title="secret">비공개 질문</a>
         </li>
         <li class="nav-item ml-auto" style="margin-left: 200px;">
          	<button class="btn btn-primary mr-2" data-toggle="modal" data-target="#writePost">상담하기</button>
         </li>
         </sec:authorize>
     </ul>
  	<table class="table table-hover" style="text-align:center;">
  		<tr>
  			<th style="width: 10%; border-top: none;">
  				번호
  			</th>
  			<th style="width: 65%; border-top: none;">
  				제목
  			</th>
  			<th style="width: 15%; border-top: none;">
  				등록일
  			</th>
  			<th style="width: 10%; border-top: none;">
  				답변여부
  			</th>
  		</tr>
  		<c:forEach begin="1" end="10">
  		<tr class="custom-cursor-pointer" onclick="loadPostData(this)">
  		     <c:forEach begin="1" end="4">
  		     <td class="align-middle"></td>
  		     </c:forEach>
  		</tr>	
  		</c:forEach>
  	</table>
  	<ul id="testID" class="pagination" style="justify-content: center;">
	</ul>	
  </div>  
  </div>
</div>
</div>
  <script>
  $(function(){
	  $('.custom-search-button').click(function(){
		  searchSubmit();
	  });

	  $("#search-form").submit(function(){
		  searchSubmit();
		  return false;		  
	  });
  });

  function loadPostData(obj) {
	  if($(obj).children('td').eq(1).text() == "\u00a0") {
		  return;
	  }
	  $('.talk_contatiner').empty();
	  const targetId = $(obj).attr("id");
	  $.ajax({
			type : "GET",
			url : "<c:url value="/board/getPostData"/>",
			data : {"targetId": targetId},
         	dataType : 'json',
			beforeSend : function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
				},
			success : function(data) {
				if (data.length == 2) {
					leftTalkBox(data[1]);
				}
				rightTalkBox(data[0]);
				$('input[name=que_id]').val(targetId);
				$('#talk-box').modal('show');
			},
			error:function(request,status,error){
		        alertCall(error);
		    }
	  });
  }

  function searchSubmit() {
	  if (selectCheck()) {
		  tabState = "public_search";
		  nowPage = 0;
		  $('.pagination').empty();
		  makePost();
	  }
  }
  
  function selectCheck() {
	  if ($('input[name=search-target]').val() == "") {
		  alertCall("검색어를 입력해주세요.");
		  return false;
	  } else if ($('input[name=search-target]').val().length < 2) {
		  alertCall("검색어는 최소 2자리입니다.");
		  return false;
	  }
	  return true;
	  
  }

  $(function(){
	  makePost();	  
  });
  
  const nowPageTag = $('#now-page');
  let nowPage = 0;
  let tabState = "public";
  let currentTotalRows = 0;
  
  function boardTabClick(obj) {
	tabState = $(obj).attr("title");	  
  	$(obj).parent().siblings().children('a').removeClass('active');
  	$(obj).addClass('active');
  	nowPageTag.val(0);
	makePost();
  }  

  function makePaging() {
	    $('.pagination').empty();
		$('.pagination').append($('<li class="page-item"><a class="page-link" href="#" onclick="pagingPrevious()">Previous</a></li>'));
		if (currentTotalRows <= 10) {
			$('.pagination').append($('<li class="page-item"><a class="page-link" href="#">1</a></li>'));
			$('.pagination').append($('<li class="page-item"><a class="page-link" href="#">Next</a></li>'));
		} else {
			for (let i = 1; i <= (currentTotalRows / 10) + 1; i++) {
				$('.pagination').append($('<li class="page-item"><a class="page-link" href="#" onclick="pagingClick(this)">' + i + '</a></li>'));
			}
			$('.pagination').append($('<li class="page-item"><a class="page-link" href="#" onclick="pagingNext()">Next</a></li>'));
	    }	    
  }

  function makePost() {
	  //현재 페이지 값을 input태그에 부여
	  nowPageTag.val(nowPage);
	  //form을 직렬화
	  let seralizeData = $("#search-form").serialize();
	  $.ajax({
			type : "GET",
			url : "<c:url value="/board/"/>" + tabState,
			data : seralizeData,
            dataType : 'json',
			beforeSend : function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
				},
			success : function(data) {
				console.log(nowPage);
				//받아온 data의 길이가 10개보다 안될때.
				if (data.length < 10) {
					for (let index = data.length; index <= 10; index++) {
						var $tr = $('tr').eq(index).children('td');
						$tr.eq(0).text("\u00a0");
						$tr.eq(1).text("\u00a0");
						$tr.eq(2).text("\u00a0");
						$tr.eq(3).text("\u00a0");
					}
				}
				if (nowPage == 0) {
					if (data.length != 0) {
						currentTotalRows = parseInt(data[0].no);
					} else {
						currentTotalRows = 0;
					}
					makePaging();
				}
				for (let index = 0; index < data.length; index++) {			
					var $tr = $('tr').eq(index + 1);
					$tr.attr("id", data[index].que_id);
					$tr.children('td').eq(0).text(data[index].no);
					$tr.children('td').eq(1).text(data[index].que_title);
					$tr.children('td').eq(2).text(data[index].que_date);
					$tr.children('td').eq(3).empty();
					if (data[index].que_check == 1) {
						$tr.children('td').eq(3).append($('<button class="btn btn-secondary"><small>답변완료</small></button>'));
					} else {
						$tr.children('td').eq(3).append($('<button class="btn btn-primary"><small>답변대기</small></button>'));
					}
					
				}
				delete data;
				pageActiveChange();
				
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
	  });
  }

  function rightTalkBox(data) {
		console.log(data);
	  
		//가장 테두리를 싸는 토크박스 생성
		let $right_talk = $('<div/>');
		$right_talk.addClass('talk_bubble_right custom-cursor-pointer');

		//토그박스 데이터 생성
		let $inner_talk_data = $('<div/>').addClass('custom-reply-tab');
		$inner_talk_data.prepend($('<p class="m-0 text-right font-weight-bold"></p>').text(data.u_nick));
		$inner_talk_data.append($('<p class="m-0 text-right"></p>').text(data.que_date));
		
		$right_talk.prepend($inner_talk_data);				
		$right_talk.append($('<div/>').addClass('talk_texts').html(data.que_text));
		
		$('.talk_contatiner').prepend($right_talk);	
  }
  
  function leftTalkBox(data) {
		//가장 테두리를 싸는 토크박스 생성
		let $lef_talk = $('<div/>');
		$lef_talk.addClass('talk_bubble_left custom-cursor-pointer');
		$lef_talk.append($('<div/>').addClass('talk_texts').html(data.que_text));
		
		$('.talk_contatiner').prepend($lef_talk);	
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
	  if (currentTotalRows != 0 && nowPage + 1 < Math.floor(currentTotalRows / 10 + 1)){
			nowPage++;
		  	makePost();		
	  }
  }
  </script>