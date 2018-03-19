$(function() {
	$("#editPro").click(function() {
		var id = $("input[name='id']").val();
		var usrName = $("input[name='usrName']").val();
		var email = $("input[name='email']").val();
		var phone = $("input[name='phone']").val();
		var pwd = $("input[name='pwd']").val();
		
		$.post("editProfile", {
			id : id,
			usrName : usrName,
			email : email,
			phone : phone,
			pwd : pwd,
		}, function(json) {
			if (json.error == "dupEmail") {
				bootbox.alert({
					message : 'Email地址已被使用过！', 
					callback : function() {
						location.reload();
					}
				});
			}
			else {
				bootbox.alert({
					message : '修改成功！', 
					callback : function() {
						location.reload();
					}
				});
			}
		})
		
	})
})