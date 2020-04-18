<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Confirm 기능 -->

<div class="modal fade" id="confirm-modal" tabindex="-1" role="dialog" aria-labelledby="confirm-modal-title" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content modal-cover">
      <div class="modal-header card-header bg-info">
        <h5 class="modal-title text-light" id="confirm-modal-title">Modal title</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="card-body font-weight-bold">
        	내용을 확인해주세요.
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-primary" onclick="confirmButtonOK()" data-dismiss="modal">예</button>
		<button type="button" class="btn btn-secondary" data-dismiss="modal">아니오</button>	      
      </div>
    </div>
  </div>
</div>
	
<c:if test="${not empty error}">
<div class="modal fade" id="error-modal" tabindex="-1" role="dialog" aria-labelledby="error-modal-title" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content modal-cover">
      <div class="modal-header card-header bg-danger">
        <h5 class="modal-title text-white" id="error-modal-title">ERROR ALERT!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span class="text-white" aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="card-body font-weight-bold">
       	 ${error}
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script>	
	$(window).on('load',function(){
		$('#error-modal').modal('show');
	});
</script>
</c:if>	
	

<!-- Alert 기능 -->
<div class="modal fade" id="alert-modal" tabindex="-1" role="dialog" aria-labelledby="alert-modal-title" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered" role="document">
    <div class="modal-content modal-cover">
      <div class="modal-header card-header bg-danger">
        <h5 class="modal-title text-white" id="alert-modal-title">ERROR ALERT!</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span class="text-white" aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="card-body font-weight-bold">
       	 경고 경고
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<!-- Script Control -->

<script>
	let confirmButtonOK;

	function alertCall(text, title) {
		if (title != null) {
			$('#alert-modal-title').text(title);		
			$('#alert-modal').find('.card-body').html(text);
			$('#alert-modal').modal('toggle');
		} else {
			$('#alert-modal').find('.card-body').html(text);
			$('#alert-modal').modal('toggle');
		}

	}
	
	function confirmCall(text, func, title) {
		if (title != null) {
			$('#confirm-modal-title').text(title);		
			$('#confirm-modal').find('.card-body').html(text);
			confirmButtonOK=func;
			$('#confirm-modal').modal('toggle');
		} else {
			$('#confirm-modal').find('.card-body').html(text);
			confirmButtonOK=func;
			$('#confirm-modal').modal('toggle');
		}		
	}
</script>