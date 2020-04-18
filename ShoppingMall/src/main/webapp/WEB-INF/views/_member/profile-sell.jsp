<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!-- Container -->
<div id="content-wrapper">
  <div class="d-flex container-fluid h-100">
    <!-- DataTables Example -->
    <div class="card align-self-center w-100 border-0 shadow">
      <div class="card-header bg-primary text-white border-0">
        <i class="fas fa-table"></i>
        	판매 현황
      </div>
      <div class="form-group mb-0">
      	<ul class="list-group">
		<li class="list-group-item py-5 border-bottom-0 border-left-0 border-right-0">
		<form action="<c:url value="/member/edit_process"/>" method="POST">
			<div class="col-md-8 mx-auto">
				<table class="table table-hover text-center">
				<tr>
		  			<th style="width: 40%; border-top: none;">
		  				판매글
		  			</th>
		  			<th style="width: 40%; border-top: none;">
		  				제품명
		  			</th>
		  			<th style="width: 20%; border-top: none;">
		  				거래상태
		  			</th>
		  		</tr>
		  		<c:forEach begin="1" end="10">
		  		<tr class="custom-cursor-pointer" onclick="loadPostData(this)">
		  		     <c:forEach begin="1" end="3">
		  		     	<td></td>
		  		     </c:forEach>
		  		</tr>	
		  		</c:forEach>
				</table>
				<ul id="testID" class="pagination" style="justify-content: center;">
				</ul>
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

	$(function(){
		makeList();
	});


	let nowPage = 0;

	function loadPostData(obj) {
		  $('.talk_contatiner').empty();
		  const targetId = $(obj).attr("id");
	}

	function makeList() {
		$('td').empty();
		$.ajax({
			type : "GET",
			url : "<c:url value="/profile/sell_data"/>",
			data : {"page": nowPage},
            dataType : 'json',
			beforeSend : function(xhr) {
				xhr.setRequestHeader("${_csrf.headerName}", "${_csrf.token}");
				},
			success : function(data) {
				if (data.productList == null) {
					return;
				}				
				if (nowPage % 10 == 0) {
					currentTotalRows = data.totalRows;
					makePaging();
				}
				data = data.productList;
				for (let index = 0; index < data.length; index++) {		
					var $tr = $('tr').eq(index + 1);

					if (data[index].o_state == 0) {
						$tr.children('td').eq(2).append($('<button type="button" class="btn btn-success"><small>판매중</small></button>'));
					} else if (data[index].o_state == 1) {
						$tr.children('td').eq(2).append($('<button type="button" class="btn btn-primary"><small>입금대기</small></button>'));		
					} else if (data[index].o_state == 2) {
						$tr.children('td').eq(2).append($('<button type="button" class="btn btn-secondary"><small>배송대기</small></button>'));
					} else if (data[index].o_state == -1) {
						$tr.children('td').eq(0).text("\u00a0");
						continue;
					}					
					$tr.children('td').eq(0).text(data[index].p_title);
					$tr.children('td').eq(1).text(data[index].p_name);

				}
				delete data;
				pageActiveChange();				
			},
			error:function(request,status,error){
		        alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
		    }
	  	});
	}

	  function pagingClick(obj) {
		  	nowPage = parseInt($(obj).text()) - 1;
		  	makeList();
	  }

	  function pagingPrevious() {
			if (nowPage != 0){
				nowPage--;
				makeList();		
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
				makeList();		
		  }
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
</script>
