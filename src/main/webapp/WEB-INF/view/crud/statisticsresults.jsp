<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="model.Order"%>
<%@ page import="model.Orderitem"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Statistics</title>
<%
	String path = request.getContextPath();
	ArrayList<Order> orders = new ArrayList<Order>();
	if (request.getAttribute("staticsResults") != null) {
		orders = (ArrayList<Order>) request.getAttribute("staticsResults");
	}
	ArrayList<Orderitem> orderitems = new ArrayList<Orderitem>();
	if (request.getAttribute("items") != null) {
		orderitems = (ArrayList<Orderitem>) request.getAttribute("items");
	}
	int cnt = orders.size();
	int sum = 0;
	int itemcnt = 0;
	for (int i = 0; i < cnt; i++) {
		sum+=orders.get(i).getSum();
	}
	for (int i = 0; i < orderitems.size(); i++) {
		itemcnt += orderitems.get(i).getNumber();
	}
%>

<link rel='stylesheet prefetch' href="<%=path%>/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=path%>/css/index_style.css">
<link rel="stylesheet" href="<%=path%>/css/login_style.css">
<script src="<%=path%>/js/jquery-1.6.min.js"></script>
<script src="<%=path%>/js/bootstrap.min.js"></script>
<script src="<%=path%>/js/jquery.dataTables.min.js"></script>
<script src="<%=path%>/js/dataTables.bootstrap.min.js"></script>
<script src="<%=path%>/js/bootbox.min.js"></script>

</head>
<s:if test="#session.isAdmin">
	<body>
		<div class="navbar navbar-default navbar-fixed-top">
			<a class="navbar-brand" href="index" style="color: cadetblue">BookStore</a>
			<ul class="top_ul">
				<li class="top_li">Hi, <s:property value="#session.userName" />!
				</li>
				<li class="top_li"><a href="logoutAction"
					class="btn btn-default btn-sm" role="button">logout</a></li>
				<li class="top_li"><a href="<%=path%>/crud/user.jsp"
					class="btn btn-default btn-sm" role="button">Admin Things</a></li>
				<li class="top_li">
					<form action="search"
						style="margin: 0; display: inline-flex; text-align: center;">
						<input type="text" name="searchStr" placeholder="输入图书isbn\题名\作者" />
						<button class="btn btn-sm btn-primary" type="submit">Search</button>
					</form>
				</li>
			</ul>
		</div>

		<div class="bs-body">
			<div class="bs-center container" style="padding-top:50px">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span>Query Results</span>
						</h3>
					</div>
					<ul class="list-group">
						<li class="list-group-item">Order placed:
						<%=cnt%></li>
						<li class="list-group-item">Book sold:
						<%=itemcnt%></li>
						<li class="list-group-item">Total sum:
						<%=sum%></li>
						<li class="list-group-item">
						<a href="statistics" class="btn btn-sm btn-primary">Back</a></li>
					</ul>
				</div>
			</div>
		</div>
	</body>
</s:if>
<s:else>
	<%
		response.sendRedirect("../index");
	%>
</s:else>
</html>