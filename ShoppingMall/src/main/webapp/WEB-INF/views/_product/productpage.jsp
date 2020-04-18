<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<div id="container_cover">

<script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.1.5.js"></script>
<script type="text/javascript">
	const p_id = ${product.p_id};
	const $csrf_token = $('<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />');
	const $p_id = $('<input type="hidden" name="p_id">').val(p_id);

	function submitForm(targetForm) {
		targetForm.append($csrf_token);
		targetForm.append($p_id);
		targetForm.submit();
	} 
	
	$(function(){
		$('.custom-qna-button').click(function(){
			loadPostData();
			$('#talk-box').modal('show');
		});

		$('#reply-sumbit-button').click(function(){
			submitForm($('#relpy-add-form'));
		});
		
		$('#height-controller').click(function(){
// 			if (!$('#reply-edit-cancel').hasClass('d-none')) {
// 				$('#reply-edit-cancel').removeClass('d-block');
// 				$('#reply-edit-cancel').addClass('d-none');
// 				$('#reply-edit-button').addClass('d-none');
// 				$('#reply-sumbit-button').removeClass('d-none');
// 			}
			$('#reply-delete-cancel').toggleClass('d-none');	
			$('#reply-delete-cancel').toggleClass('d-block');	
			$('.custom-kakao-background').toggleClass('custom-card-height');
			$('.custom-kakao-background').toggleClass('custom-long-view-top');
			$('#height-controller').closest('div').toggleClass('custom-long-view-bottom');
		});	

		$('#reply-delete-cancel').click(function(){
			confirmCall("정말 삭제하시겠습니까?", deleteReply);
		});

		$('#new-order').click(function(){
			submitForm($('#new-order-form'));
		});

	});

	function deleteReply() {
		$('#control-reply-form').attr('action', '<c:url value="/board/QnA_del"/>');
		$('#control-reply-form').submit();
	}

    function loadPostData() {
	    $('.talk_contatiner').empty();
	    $.ajax({
			type : "GET",
			url : "<c:url value="/product/getReplyData"/>",
			data : {"p_id": p_id},
         	dataType : 'json',
			beforeSend : function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
				},
			success : function(data) {

				for(let index = 0; index < data.length; index++) {
					if (data[index].u_id != 0) {	//글쓴이와 리플러가 같음
						leftTalkBox(data[index]);
					} else {
						rightTalkBox(data[index]);
					}
				}			
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
		});
    }

    function rightTalkBox(data) {
		//가장 테두리를 싸는 토크박스 생성
		let $right_talk = $('<div/>');
		$right_talk.addClass('talk_bubble_right custom-cursor-pointer');

		//토그박스 데이터 생성
		let $inner_talk_data = $('<div/>').addClass('custom-reply-tab');
		$inner_talk_data.prepend($('<p class="m-0 text-right font-weight-bold"></p>').text(data.u_nick));
		$inner_talk_data.append($('<p class="m-0 text-right"></p>').text(data.pq_date));
		
		$right_talk.prepend($inner_talk_data);				
		$right_talk.append($('<div/>').addClass('talk_texts').html(data.pq_text));
		
		$('.talk_contatiner').prepend($right_talk);	
    }
    
    function leftTalkBox(data) {
		//가장 테두리를 싸는 토크박스 생성
		let $lef_talk = $('<div/>');
		$lef_talk.addClass('talk_bubble_left custom-cursor-pointer');
		$lef_talk.append($('<div/>').addClass('talk_texts').html(data.pq_text));
		
		$('.talk_contatiner').prepend($lef_talk);	
    }

</script>

<div class="modal fade" id="talk-box" tabindex="-1" role="dialog" aria-labelledby="talk-box-label" aria-hidden="true">
	<div class="modal-dialog modal-dialog-centered" style="max-width: 750px;">
	  <div class="modal-content">
  		<div class="modal-header card-header">
		  <h4 class="modal-title" id="talk-box-label" style="margin: 0 auto; padding-left: 42px;">상품 질문과 답변</h4>
		  <button type="button" class="close" data-dismiss="modal" style="margin-left: 0px;"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
		</div>
      <div class="card-body custom-card-height custom-kakao-background">
      <div class="talk_contatiner border-right-0">
    </div>
  	  </div>
  	  <form id="relpy-add-form" class="m-0" action="<c:url value="/product/reply_add"/>" method="POST"> 
  	  <div class="modal-footer">
  	    <textarea class="form-control w-75 custom-secret-button" name="pq_text" rows="1"></textarea>
  	    <button id="height-controller" type="button" class="btn"><i class="fas fa-arrows-alt-v"></i></button> 
  	    <div>
  	    	<button id="reply-sumbit-button" type="button" class="btn btn-primary">댓글 등록</button>
  	    	<button id="reply-delete-cancel" type="button" class="btn btn-danger mt-3 mx-auto d-none">댓글 삭제</button>
  	    </div>
	 </div>
	 </form>	 
    </div>
  </div>
</div>

<div class="container">
  <div class="row">
    <div class="col-lg-12" style=" margin-top : 35px;">
      <div class="card mb-10">		
        <div class="card-body store-body">
          <div class="product-info">
            <div class="product-gallery">
              <div class="product-gallery-featured">
                <img src="<c:url value="/resources/main/images/thum_product/"/>${product.p_thumbnail}" alt="" style="width: 400px; height: auto;">
              </div>
            </div>
            <div class="product-seller-recommended">
              <div class="product-description mb-5">
				${product.p_text}
              </div>
            </div>
          </div>
          <div class="product-payment-details">
			<p class="last-sold text-muted"><small>재고 : ${product.p_quantity}</small></p>
			<h4 class="product-title mb-2">${product.p_title}</h4>
			<h2 class="product-price display-4"><fmt:formatNumber value="${product.p_price}" pattern="#,###"/>원</h2>					
			<form id="new-order-form" class="m-0" method="POST" action="<c:url value="/product/new_order"/>">
				<label for="quant" style="margin-top : 20px;">수량</label>
				<input type="number" name="quantity" min="1" max="${product.p_quantity}" id="quant" class="form-control mb-5 input-lg" placeholder="Choose the quantity">
			</form>
			<div class="text-muted mb-2"><small></small></div>
				<c:choose>
					<c:when test="${not empty EndSale}">
						<button type="button" class="btn btn-secondary btn-lg btn-block">거래중</button>	
					</c:when>
					<c:otherwise>
						<button type="button" id="new-order" class="btn btn-primary btn-lg btn-block">구매하기</button>
					</c:otherwise>
				</c:choose>
			<div class="product-comments" style="border-bottom-style: inset;">
				<input id="csrf_token" type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
				<input type="hidden" name="p_id" value="${product.p_id}"/>
				<sec:authorize access="isAuthenticated()">
				<c:choose>
					<c:when test="${confirmID eq 'match'}">
						<button type="button" class="btn btn-primary btn-lg btn-block mt-4 custom-qna-button">상품 Q&amp;A답변하기</button>
					</c:when>
					<c:when test="${confirmID eq 'not_match'}">
						<button type="button" class="btn btn-primary btn-lg btn-block mt-4 custom-qna-button">상품 Q&amp;A작성하기</button>
					</c:when>
				</c:choose>
				</sec:authorize>					
          	</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
</div>

