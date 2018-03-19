<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.User"%>
<%
	User u = new User();
	if (request.getAttribute("user") != null) {
		u = (User) request.getAttribute("user");
	}
%>
<%@ include file="layouts/header.jsp"%>
<script src="<%=path%>/js/profile.js"></script>
<script> document.title = "My Profile - BookStore" </script>
<s:if test="#session.logined">
		<div class="bs-body">
			<div class="bs-center">
				<div class="profile-wrapper" style="padding: 80px">
					<div class="form-signin">
						<h2 class="form-signin-heading">Edit Your Profile</h2>
						ID: <input type="text" class="form-control" name="id"
							value=<%=u.getId()%> readonly="readonly" /> Name: <input
							type="text" class="form-control" name="usrName"
							placeholder="User Name" value=<%=u.getUsrName()%> required="true"
							autofocus="" /> Email: <input type="email" class="form-control"
							name="email" placeholder="Email Address" value=<%=u.getEmail()%>
							required="true" autofocus="" readonly="readonly" /> Phone: <input
							type="tel" class="form-control" name="phone"
							placeholder="Phone Number" value=<%=u.getPhone()%>
							required="true" autofocus="" /> Password: <input type="password"
							class="form-control" name="pwd" placeholder="Password"
							value=<%=u.getPwd()%> required="true" />
						<button id="editPro" class="btn btn-lg btn-primary btn-block">Submit</button>
					</div>
				</div>
			</div>
		</div>
	</body>
</s:if>
<s:else>
	<%
		response.sendRedirect("index");
	%>
</s:else>
</html>

