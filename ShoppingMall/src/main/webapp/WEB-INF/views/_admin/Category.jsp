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
        	카테고리 설정
      </div>
      <div class="form-group mb-0">
      	<ul class="list-group">
		<li class="list-group-item py-5 border-bottom-0 border-left-0 border-right-0">
			<form action="<c:url value="/admin/category/create"/>" method="POST">
   			<div class="col-md-8 mx-auto">
   				<p class="font-weight-bold">추가할 카테고리의 이름을 입력해주세요.</p>
   				<div class="row">
 				<div class="col-md-9">
 				<input type="text" class="form-control" id="add_category" name="c_name">
 				</div>
 				<div class="col-md-3">
  				<button class="btn btn-primary btn-block font-weight-bold" type="button" onclick="createControl(this)">추가</button>
 				</div>
 				</div>
			</div>  
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
      	</li>
		<li class="list-group-item py-5 border-bottom-0 border-left-0 border-right-0 border-top">
			<form action="<c:url value="/admin/category/delete"/>" method="POST">
	  		<div class="col-md-8 mx-auto">
	  			<p class="font-weight-bold">수정 또는 삭제할 카테고리를 선택해주세요.</p>
	  			<div class="row">
				<div class="col-md-8">
					<select class="form-control" id="select_category" name="c_id">
					  <option value="none">---카테고리를 선택해주세요---</option>
					<c:forEach var="category" items="${categoryList}">
					<option value="${category.c_id}">${category.c_name}</option>					  
					</c:forEach>
					</select>
				</div>
				<div class="col-md-4 btn-group" role="group" aria-label="Basic example">
					<button class="btn btn-primary font-weight-bold" type="button" data-toggle="collapse" data-target="#modify_li" aria-expanded="false" aria-controls="modify_li">수정</button>
					<button class="btn btn-danger font-weight-bold" type="button" onclick="deleteControl(this)">삭제</button>
				</div>	
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
		</li>
   		<li class="list-group-item py-0 border-bottom-0 border-left-0 border-right-0 border-top">
   			<form class="mb-0" action="<c:url value="/admin/category/modify"/>" method="POST">
			<div class="collapse" id="modify_li">
			<div class="card-body py-5">
				<div class="col-md-8 p-0 pb-3 mx-auto">
					<label class="font-weight-bold" for="select_category">변경될 카테고리 명을 입력해주세요.(대상 : <span></span>)</label>
					<input class="form-control" id="name-edit" name="c_name" type="text" disabled>
				</div>
			    <button class="btn btn-primary btn-block col-md-8 mx-auto font-weight-bold" id="testBtn" type="button" onclick="modifyControl(this);">수정한 내용 보내기</button>
			</div>
			</div>
			<input type="hidden" name="c_id" id="modify_id" />
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			</form>
     	</li>	
        </ul>
      </div>
    </div>
  </div>
</div>

  <script>
// 	function createControl(obj) {
// 		if ($('#add_category').val() == "") {
// 			$('#alert-modal').find('.card-body').text("카테고리명을 입력해주세요!!");
// 			$('#alert-modal').modal('toggle');
// 			return;
// 		} else {
// 			$(obj).parents('form').submit();
// 		}
// 	}
  
// 	function categoryCheck() {
// 		if ('none' == $('#select_category option:selected').val()) {
// 			$('#alert-modal').find('.card-body').text("카테고리를 선택해주세요!!");
// 			$('#alert-modal').modal('toggle');
// 			return false;
// 		}
// 		return true;
// 	}
	
// 	function modifyControl(obj) {
// 		if (categoryCheck() == true){
// 			if ($('#name-edit').val() == "") {
// 				$('#alert-modal').find('.card-body').text("변경할 값을 입력해주세요!!");
// 				$('#alert-modal').modal('toggle');
// 				return;
// 			}
// 			$('#modify_id').val($('#select_category option:selected').val());
// 			$(obj).parents('form').submit();
// 		}		
// 	}

// 	function deleteControl(obj) {
// 		if (categoryCheck() == true) {
// 			$('#modify_id').val($('#select_category option:selected').val());
// 			$(obj).parents('form').submit();
// 		}		
// 	}

// 	$('#select_category').on("change", function(){
// 		var target = $('#select_category option:selected');
// 		if (target.val() == 'none') {
// 			$('#name-edit').attr("disabled",true);
// 		} else {
// 			$('#name-edit').attr("disabled",false);
// 			$('#name-edit').siblings().children('span').text(target.text());
// 		}
// 	});

	function createControl(obj) {
		if ($('#add_category').val() == "") {
			alertCall("카테고리명을 입력해주세요!!");
			return;
		} else {
			$(obj).parents('form').submit();
		}
	}
  
	function categoryCheck() {
		if ('none' == $('#select_category option:selected').val()) {
			alertCall("카테고리를 선택해주세요!!");
			return false;
		}
		return true;
	}
	
	function modifyControl(obj) {
		if (categoryCheck() == true){
			if ($('#name-edit').val() == "") {
				alertCall("변경할 값을 입력해주세요!!");
				return;
			}
			$('#modify_id').val($('#select_category option:selected').val());
			$(obj).parents('form').submit();
		}		
	}

	function deleteControl(obj) {
		if (categoryCheck() == true) {
			$('#modify_id').val($('#select_category option:selected').val());
			$(obj).parents('form').submit();
		}
	}

	$('#select_category').on("change", function(){
		var target = $('#select_category option:selected');
		if (target.val() == 'none') {
			$('#name-edit').attr("disabled",true);
		} else {
			$('#name-edit').attr("disabled",false);
			$('#name-edit').siblings().children('span').text(target.text());
		}
	});
	
  </script>


