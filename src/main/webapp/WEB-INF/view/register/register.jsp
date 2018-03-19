<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="../layouts/header.jsp"%>
<link rel="stylesheet" href="<%=path%>/css/register_style.css">
<script>
	$(document).ready(function() {
		var logined = "<s:property value='#session.logined' />";
		if (logined) {
			bootbox.alert("您已登录，无需注册！", function() {
				location.href = "index";//location.href实现客户端页面的跳转  
			})
		}
	});
</script>
<div class="bs-body">
	<div class="bs-center">
		<div class="register-wrapper">
			<form class="form-signin" action="registerAction" method="post">
				<h2 class="form-signin-heading">Please Register</h2>
				<input type="text" class="form-control" name="usrName"
					placeholder="User Name" required="true" autofocus="" /> <input
					type="email" class="form-control" name="email"
					placeholder="Email Address" required="true" autofocus="" /> <input
					type="number" class="form-control" name="phone"
					placeholder="Phone Number" required="true" autofocus="" /> <input
					type="password" class="form-control" name="pwd"
					placeholder="Password" required="true" />
				<button class="btn btn-lg btn-primary btn-block" type="submit">Register</button>
			</form>
		</div>
	</div>
</div>
</body>
</html>
