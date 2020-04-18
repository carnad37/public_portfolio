function signUpButton() {
	$('#inputPassword').removeClass('is-invalid');
	$('#confirmPassword').removeClass('is-invalid');
	$('#zoneCode').removeClass('is-invalid');
// 		$('#firstAddress').removeClass('is-invalid');

	if ($('#inputPassword').val().length < 6) {
		$('#inputPassword').siblings('.invalid-feedback').text("6글자 이상 입력해주세요.");
		$('#inputPassword').addClass('is-invalid');
		return;
	}
	let regSpePw = /(?=.*[!@#$%^*()\-_=+\[\]{};:",.?]).{6,50}$/;
	if (!regSpePw.test($('#inputPassword').val())) {
		$('#inputPassword').siblings('.invalid-feedback').text("특수기호를 포함시켜주세요(&lt;, >, (, ), ', /, | 제외)");
		$('#inputPassword').addClass('is-invalid');
		return;
	}
	let regEngPw = /(?=.*[a-zA-Z]{2,50}).{6,50}$/;
	if (!regEngPw.test($('#inputPassword').val())) {
		$('#inputPassword').siblings('.invalid-feedback').text("영문을 포함시켜주세요");
		$('#inputPassword').addClass('is-invalid');
		return;
	}
	let regNumPw = /(?=.*\d{1,50}).{6,50}$/;
	if (!regNumPw.test($('#inputPassword').val())) {
		$('#inputPassword').siblings('.invalid-feedback').text("숫자를 포함시켜주세요");
		$('#inputPassword').addClass('is-invalid');
		return;
	}
	if ($('#inputPassword').val() != $('#confirmPassword').val()) {
		$('#confirmPassword').addClass('is-invalid');
		return;
	}

	if ($('#zoneCode').val() == "") {
		$('#zoneCode').addClass('is-invalid');
		return;
	}

// 		if ($('#firstAddress').val() == "") {
// 			$('#firstAddress').addClass('is-invalid');
// 			return;
// 		}
	$('#submitButton').click();
}
function searchAddress() {
	var zonecode=null;
    new daum.Postcode({
        oncomplete: function(data) {
			$('#zoneCode').attr('value', data.zonecode);
			if (data.buildingName != "") {
				$('#firstAddress').attr('value', data.address + " (" + data.buildingName + ") ");
			} else {
				$('#firstAddress').attr('value', data.address);
			}
        }
    }).open();
}
function idChecker() {
	$('#account').removeClass('is-valid');
	$('#account').removeClass('is-invalid');
	let idVal = $('#account').val();
	let idReg = /^[a-z0-9]{6,15}$/g;

	if (idVal == "") {
		$('#account').siblings('.invalid-feedback').text('아이디를 입력해주세요.');
		$('#account').addClass('is-invalid');
		return;
	} else if (!idReg.test(idVal)) {
    	$('#account').siblings('.invalid-feedback').text('영문소문자와 숫자를 포함한 6-15길이로 입력해주세요.');
    	$('#account').addClass('is-invalid');
		return;
	}

	$.ajax({
	    type: "POST",
	    url: "/ShoppingMall/member/id_check",
	    dataType: "json",
	    data: {"id" : idVal},
		beforeSend : function(xhr) {
			xhr.setRequestHeader(csrf_name, csrf_token);
		},
	    success: function(data){
		    let result = parseInt(data);
		    console.log(result);
		    if (result != 0) {
		    	$('#account').siblings('.invalid-feedback').text('등록된 아이디입니다. 다시 입력해주세요.');
		    	$('#account').addClass('is-invalid');
			} else {
				$('#account').addClass('is-valid');
			}
	    }
	});
}
function nickChecker() {
	$('#nickName').removeClass('is-valid');
	$('#nickName').removeClass('is-invalid');
	let nickVal = $('#nickName').val();
	if (nickVal == "") {
    	$('#nickName').siblings('.invalid-feedback').text('닉네임을 입력해 주세요.');
    	$('#nickName').addClass('is-invalid');
    	return;
	} else if (nickVal.length > 15) {
    	$('#nickName').siblings('.invalid-feedback').text('닉네임은  15글자 이하로 입력해주세요.');
    	$('#nickName').addClass('is-invalid');
    	return;
	}
	$.ajax({
	    type: "GET",
	    url: "/ShoppingMall/member/nick_check",
	    dataType: "json",
	    data: {"id" : nickVal},
		beforeSend : function(xhr) {
			xhr.setRequestHeader(csrf_name, csrf_token);
		},
	    success: function(data){
		    let result = parseInt(data);
		    console.log(result);
		    if (result != 0) {
		    	$('#nickName').siblings('.invalid-feedback').text('등록된 아이디입니다. 다시 입력해주세요.');
		    	$('#nickName').addClass('is-invalid');
			} else {
				$('#nickName').addClass('is-valid');
			}
	    }
	});
}
