$.validator.setDefaults({
			submitHandler : function() {
				alert("submitted!");
//				var pwdmd5 = window.md5($('#password').val());
//				var cpwdmd5 = window.md5($('#confirm_password').val());
//				$('#password').val(pwdmd5);
//				$('#confirm_password').val(cpwdmd5);
//				alert(pwdmd5);
				this.submit();
			}
		});

$().ready(function() {

	// validate signup form on keyup and submit
	$("#signUpForm").validate({
		rules : {
			username : {
				required : true,
				minlength : 2,
				maxlength: 20
			},
			password : {
				required : true,
				minlength : 5,
				maxlength: 20
			},
			confirm_password : {
				required : true,
				minlength : 5,
				maxlength: 20,
				equalTo : "#password"
			},
			email : {
				required : true,
				email : true
			},
			agree : "required"
		},
		messages : {

			username : {
				required : "Please enter a username",
				minlength : "Your username must consist of at least 2 characters"
			},
			password : {
				required : "Please provide a password",
				minlength : "Your password must be at least 5 characters long"
			},
			confirm_password : {
				required : "Please provide a password",
				minlength : "Your password must be at least 5 characters long",
				equalTo : "Please enter the same password as above"
			},
			email : "Please enter a valid email address",
			agree : "Please accept our policy"
		}
	});

});