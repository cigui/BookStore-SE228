<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="model.User"%>

<%@ include file="../layouts/header.jsp"%>
<%
	User u = new User();
	if (request.getAttribute("user") != null) {
		u = (User) request.getAttribute("user");
	}
%>
<div class="bs-body">
	<div class="bs-center container">
		<div class="page-header">
			<h1 class="text-primary">
				Welcome to my BookStore!<br> <small>Your profile is as follows:</small>
			</h1>
		</div>
		<div class="row">
			<div class="col-md-12" style="font-size: 24px; color: #777">ID:
			<%=u.getId()%></div>
		</div>
		<div class="row">
			<div class="col-md-12" style="font-size: 24px; color: #777">Name:
		<%=u.getUsrName()%></div>
		</div>
		<div class="row">
			<div class="col-md-12" style="font-size: 24px; color: #777">Email:
		<%=u.getEmail()%></div>
		</div>
		<div class="row">
			<div class="col-md-6" style="text-align: center">
				<a href="index" class="btn btn-primary btn-lg">Go back to
					homepage</a>
			</div>
			<div class="col-md-6" style="text-align: center">
				<a href="profile" class="btn btn-primary btn-lg">Edit your profile</a>
			</div>
		</div>
	</div>
</div>

</body>
</html>
