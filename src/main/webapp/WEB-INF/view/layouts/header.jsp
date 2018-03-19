<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<% String path = request.getContextPath(); %>
<!DOCTYPE html><html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Book Store</title>
<link rel='stylesheet prefetch' href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/css/index_style.css">
<link rel="stylesheet" href="<%=path%>/css/login_style.css">
<script src="<%=path%>/js/jquery-1.6.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/jquery.dataTables.min.js"></script>
<script src="<%=path%>/js/dataTables.bootstrap.min.js"></script>
<script src="<%=path%>/js/bootbox.min.js"></script>

</head>
<body>
	<div class="navbar navbar-default navbar-fixed-top">
		<a class="navbar-brand" href="index" style="color: cadetblue">BookStore</a>
		<ul class="top_ul">
			<s:if test="#session.logined">
				<li class="top_li">Hi, <s:property value="#session.userName" />!
				</li>
				<li class="top_li"><a href="logoutAction"
					class="btn btn-default btn-sm" role="button">logout</a></li>
				<li class="top_li"><a href="profile" data-uid="#session.userId"
					class="btn btn-default btn-sm" role="button">profile</a></li>
				<li class="top_li"><a href="viewShoppingCart"
					data-uid="#session.userId" class="btn btn-default btn-sm"
					role="button">My ShoppingCart</a></li>
				<li class="top_li"><a href="listOrders"
					data-uid="#session.userId" class="btn btn-default btn-sm"
					role="button">My Orders</a></li>
				<s:if test="#session.isAdmin">
					<li class="top_li"><a href="<%=path%>/goCrudUser"
						class="btn btn-default btn-sm" role="button">Admin Things</a></li>
				</s:if>
			</s:if>
			<s:else>
				<li class="top_li">Not logined, please</li>
				<!-- a trigger button for the modal -->
				<li class="top_li"><button class="btn btn-default btn-sm"
						data-toggle="modal" data-target="#myModal">login</button></li>
				<li class="top_li">or</li>
				<li class="top_li"><a href="<%=path%>/goRegister"
					class="btn btn-default btn-sm" role="button">register</a></li>
				<li class="top_li">! :)</li>
			</s:else>
			<li class="top_li">
				<form action="search" style="	margin: 0; display: inline-flex;
					text-align: center; ">
					<input type="text" name="searchStr" placeholder="输入图书isbn\题名\作者" />
					<button class="btn btn-sm btn-primary" type="submit">Search</button>
				</form>
			</li>
		</ul>
	</div>
	<!-- the modal -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="Login" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel"></h4>
				</div>
				<form class="modal-body form-signin" action="loginAction">
					<h2 class="form-signin-heading">Please login</h2>
					<input type="email" class="form-control" name="email"
						placeholder="Email Address" required="true" autofocus="" /> <input
						type="password" class="form-control" name="pwd"
						placeholder="Password" required="true" />
					<button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
				</form>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal -->
	</div>
