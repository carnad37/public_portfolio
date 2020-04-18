<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<script src="<c:url value="/ckeditor/ckeditor.js"/>"></script> 
	<script>
		$(function(){
			$("#thumbnail_image").click(function(){
				$("input[name=thumbnailfile]").click();
				console.log("click picture")				
				});
	        $("input[name=thumbnailfile]").change(function () {
	        	let test = $("input[name=thumbnailfile]").get(0).files[0];
	        	readURL(test);
	        }); 
		});
		
    function readURL(target) {
        let reader = new FileReader(); //파일을 읽기 위한 FileReader객체 생성
        reader.onload = function (e) {
        	console.log(e.target.result);
        	$("#thumbnail_image").attr("src", e.target.result);
        }
		reader.readAsDataURL(target);
    }    
	</script>
	<div id="container_cover">    
      <div class="container mb-5">      
       <form action="call_adder?${_csrf.parameterName}=${_csrf.token}" method="POST" enctype="multipart/form-data"> 
       <div class="row">       
       	<div class="col-lg-6 mb-3 d-table" style="height:496px;">
       		  <div class="d-table-cell align-middle">
       	 	  <input type="file" name="thumbnailfile" style="display : none;">      	  
			  <label for="inp_1" class="inp">
  				<input autocomplete="off" type="text" id="inp_1" name="p_title">
  				<span class="label">제목</span>
  				<span class="border"></span>
			  </label>
			  
			  <label for="inp_2" class="inp mt-3">
  				<input autocomplete="off" type="text" id="inp_2" name="p_name">
  				<span class="label">상품명</span>
  				<span class="border"></span>
			  </label>
	          
			  <label for="inp_3" class="inp mt-3">
  				<input autocomplete="off" type="text" id="inp_3" name="p_quantity" >
  				<span class="label">수량</span>
  				<span class="border"></span>
			  </label>

			  <label for="inp_4" class="inp mt-3">
  				<input autocomplete="off" type="text" id="inp_4" name="p_price">
  				<span class="label">가격</span>
  				<span class="border"></span>
			  </label>

			  <div class="sBox mt-3">
		  	  	<select id="productType" name="c_id" >
	           		<option value="0">카테고리</option>
	           		<option value="-1">======</option>
	           		<c:forEach var="category" items="${categoryList}">
						<option class="text-dark" value="${category.c_id}">${category.c_name}</option>					  
					</c:forEach>
               </select>
			  </div>
			 </div>
	       	</div> <!-- 상단 좌측 -->
   	  	<div class="col-lg-6 d-table">
			<img class="d-table-cell custom-image-control mx-auto my-5" style="width:400px; height:400px;" src="<c:url value="/resources\main\images\main_product\400x400.png"/>" id="thumbnail_image">
   		</div> <!-- 상단 우측 -->
	     <div class="col-lg-12">
	     	 <textarea id="editor" name="p_text" rows="8" cols="80"></textarea>
	   		 <script>
				CKEDITOR.replace('editor',
				{
					filebrowserUploadUrl:'<c:url value="/upload/imageUpload"/>?${_csrf.parameterName}=${_csrf.token}',
					height : '600px'
				});
			 </script> 
	     </div> <!-- 하단 -->
	     <div class="col-lg-12">
	     	<input type="submit" name="" value="등록" class="btn btn-primary btn-lg btn-block">
	     </div>
		</div>
		</form>	
  	   </div> <!-- row -->
  </div>
