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
        	사이트 설정
      </div>
      <div class="form-group mb-0">
      	<ul class="list-group">
		<li class="list-group-item py-5 border-bottom-0 border-left-0 border-right-0">
			<form action="<c:url value="/admin/pic/bannerSetNum"/>" method="POST">
	  		<div class="col-md-8 mx-auto">
	  			<p class="font-weight-bold">롤링 배너의 이미지 수를 정해주세요. <strong>(현재 : ${bannerCount}개)</strong></p>
	  			<div class="row">
				<div class="col-md-10">
					<select class="form-control" id="select_category" name=rb_num>
					<c:forEach begin="1" end="10" var="roll_num">
						<option value="${roll_num}">${roll_num}</option>				  
					</c:forEach>
					</select>
				</div>
				<div  class="col-md-2">
					<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
					<button class="btn btn-primary btn-block" type="submit">변경</button>
				</div>
				</div>
			</div>
			</form>
		</li>
		<li class="list-group-item py-5 border-bottom-0 border-left-0 border-right-0 border-top">
	  		<div class="col-md-8 mx-auto">
	  			<p class="font-weight-bold">롤링 배너의 이미지를 선택해주세요.</p>
	  			<div class="row">
				<div class="col-md-12">
					<button class="btn btn-primary btn-block" type="button"  data-toggle="modal" data-target="#select-picture">이미지 선택</button>
				</div>
				</div>
			</div>
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		</li>
        </ul>
      </div>
    </div>
  </div>
</div>
<c:url var="resource" value="/resources/main/images/banner/"/>
<div class="modal fade" id="select-picture" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
    <div class="modal-content">
      <div class="modal-header card-header bg-info">
        <h5 class="modal-title text-white" id="select-picture-title">사진 선택</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span class="text-white" aria-hidden="true">&times;</span>
        </button>
      </div>
      <div id="select-menu-toggle" class="card-body btn-group">
      	<button class="btn btn-secondary active" onclick="clickToSelect(this)">
	    	이미지 선택
	 	</button>
	  	<button class="btn btn-secondary" onclick="clickToUpload(this)">
	    	이미지 업로드
	  	</button>
	  	<button class="btn btn-secondary" onclick="clickToDelete(this)">
	   		이미지 삭제
	  	</button>
      </div>
<script>
	function clickToSelect(obj) {
		clickToActive(obj);
		$('.check-banner').removeClass('d-none');	
		$('#select-tab').siblings('div').addClass('d-none');
		$('#select-tab').removeClass('d-none');
		$('.check-banner').attr('disabled', false);
	}
	function clickToUpload(obj) {
		clickToActive(obj);
		$('.check-banner').addClass('d-none');
		$('#upload-tab').siblings('div').addClass('d-none');
		$('#upload-tab').removeClass('d-none');
		$('.check-banner').attr('disabled', true);
		
	}
	function clickToDelete(obj) {
		clickToActive(obj);
		$('.check-banner').removeClass('d-none');		
		$('#delete-tab').siblings('div').addClass('d-none');
		$('#delete-tab').removeClass('d-none');
		$('.check-banner').attr('disabled', false);
	}
	function clickToActive(obj) {
		if ($(obj).hasClass('active')) {
			return;
		} else {
			$(obj).siblings('button').removeClass('active');
			$(obj).siblings('button').removeClass('font-weight-bold');
			$(obj).addClass('font-weight-bold');
			$(obj).addClass('active');
			clearUploadFile();
			$("input[name=select-banner]").prop("checked", false);
		}
	}

</script>
      <div class="card-body">
      <form class="mb-0" id="select-banner-form" method="post" action="">
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<c:forEach var="cover_index" begin="1" end="4">
	      	<div class="row">      
		      <c:forEach var="index" begin="${(cover_index*5)-4}" end="${cover_index*5}">
		      	<c:choose>
		      		<c:when test="${index > bannerLength}">
		      			<div class="col-sm">
			       		  <img id="preView_${index - bannerLength}" src="${resource}no_img.png" class="img-fluid" alt="Responsive image">
			       		  <p class="text-center">No Image!</p>
			       		</div>	
		      		</c:when>
		      		<c:otherwise>
		      		    <div class="col-sm text-truncate">
		      		      <div class="img-checkBox position-relative" onclick="clickToCheckBox(this)">
		      		        <img src="<c:url value="/resources/main/images/banner/${bannerList[index-1].rb_path}"/>" class="img-fluid" alt="Responsive image">
		      		        <input class="position-absolute check-banner" onclick="checkBoxDisable(this)" style="left: 5px; top: 5px;" type="checkbox" name="select-banner" value="${index - 1}">
		      		      </div>
			       		  <p class="text-center mb-0 text-truncate">${bannerList[index-1].rb_name}</p>
			       		  <p class="text-center">${bannerList[index-1].rb_date}</p>
			       		</div>
		      		</c:otherwise>
		      	</c:choose>
		      </c:forEach>
	       	</div>
	       	</c:forEach>
	  </form>
      </div>
      <div class="modal-footer">
		  <div id="select-tab">
			  <button type="button" class="btn btn-primary" onclick="clickToBannerSelect()" disabled="true">배너 이미지 등록</button>
		  </div>
		  <div id="delete-tab" class="d-none">
			  <button type="button" class="btn btn-danger" onclick="clickToBannerDelete()">배너 이미지 삭제</button>
		  </div>
		  <div id="upload-tab" class="d-none">
			  <form class="mb-0" method="post" action="<c:url value="/admin/pic/upload"/>?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
			  	  <input class="d-none" type="file" multiple="multiple" name="main-picture" accept=".jpg, .png">
				  <button type="button" class="btn btn-info" onclick="clickUpload()">파일 등록</button>
				  <button id="banner-img-submit" type="submit" class="btn btn-secondary banner-img-control" disabled="true">업로드</button>
				  <input type="reset" class="d-none" id="reset-button">
				  <button class="btn btn-warning mr-2 banner-img-control" disabled="true" onclick="clearUploadFile()">리셋</button>
			  </form>
		  </div>
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script>  
	const bannerCount = parseInt(${bannerCount});	
	const nowImgLength = parseInt(${bannerLength});	
	const resource = "${resource}";
	function clickUpload() {
		$('input[name=main-picture]').click();
	}
    $(document).ready(function() {    	 
        $("input[name=main-picture]").change(function () {
        	fileUploadSystem();
        });
        $('#container_cover').removeClass('custom-page-view');
    });

    function checkBoxDisable(obj) {
		$(obj).prop('checked',function(){
	        return !$(this).prop('checked');
	    });
   	}

    function fileUploadSystem() {
    	const uploadImgs = $('input[name=main-picture]').get(0).files;
    	let uploadImgLength = uploadImgs.length;
    	for (let index=0; index < (20 - uploadImgLength - nowImgLength); index++) {
			if (index < uploadImgLength) {
				readURL(uploadImgs[index], index);
			} else {
				var target = '#preView_' + (index + 1);
	            $(target).attr("src",resource + "no_img.png");
	            $(target).siblings('p').removeClass('font-weight-bold');
	            $(target).siblings('p').text('No Image!');
			}
        }

        //File내용을 읽어 dataURL형식의 문자열로 저장
        
		if (uploadImgLength == 0) {
	        $('#banner-img-submit').removeClass('btn-primary');				
	        $('#banner-img-submit').addClass('btn-secondary');
	        $('.banner-img-control').attr('disabled', true);
    	}else if ((nowImgLength + uploadImgLength) > 20) {
            $('#banner-img-submit').removeClass('btn-primary');
            $('#banner-img-submit').addClass('btn-secondary');
            $('.banner-img-control').attr('disabled', true);
    		alertCall("선택한 이미지 갯수가 지정된 범위를 벗어났습니다.", "업로드 경고");
        } else {
            $('#banner-img-submit').removeClass('btn-secondary');
            $('#banner-img-submit').addClass('btn-primary');
            $('.banner-img-control').attr('disabled', false);
        }
    }
    
    function readURL(target, index) {
        let reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
        reader.onload = function (e) {
        	console.log(e.target.result);
            var target = '#preView_' + (index + 1);
            $(target).attr("src",e.target.result);
            $(target).siblings('p').addClass('font-weight-bold');
            $(target).siblings('p').text('업로드 대기');
        }

		reader.readAsDataURL(target);
    }//readURL()--

    //file 양식으로 이미지를 선택(값이 변경) 되었을때 처리하는 코드
	function clickToCheckBox(obj) {	
		$(obj).children('input').prop('checked',function(){
	        return !$(this).prop('checked');
	    });
		let selectCount = parseInt($("input[name=select-banner]:checked").length);
		console.log(selectCount);
		if (selectCount == bannerCount) {
		$('#select-tab').children('button').attr('disabled', false);
		} else {
			$('#select-tab').children('button').attr('disabled', true);
		}	
	}

	function clearUploadFile() {
		$('#reset-button').click();
		fileUploadSystem();	
	}

	function clickToBannerSelect() {
		$('#select-banner-form').attr("action", "<c:url value="/admin/pic/select"/>");
		$('#select-banner-form').submit();
	}
	
	function clickToBannerDelete() {
		$('#select-banner-form').attr("action", "<c:url value="/admin/pic/delete"/>");
		$('#select-banner-form').submit();
	}
	

</script>


