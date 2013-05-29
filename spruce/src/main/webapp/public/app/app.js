$(function() {
	$("#loginForm").submit(function() {

		var uid = $('#email').val();
		var pwd = $('#pwd').val();
 
		if (uid == '') {
			alert("Please type email!");
			return false;
		}
		if (pwd == '') {
			alert("Please type password!");
			return false;
		}
		var pwdmd5 = window.md5(pwd);
		$('#pwd').val(pwdmd5);
		$(this).ajaxSubmit({
			dataType : "json",
			success : function(responseText, statusText, xhr, $form) {
				alert('status: '
						+ statusText
						+ '\n\nresponseText: \n'
						+ responseText.username
						+ '\n\nThe output div should have already been updated with the responseText.');
				window.location.reload();
			}
		});

		return false;
	});
});