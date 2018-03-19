<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>CRUD of user</title>
<% String path = request.getContextPath(); %>
<link rel="stylesheet" type="text/css" href="<%=path%>/css/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/bookstore_crud.css"
	id="css">
<script type="text/javascript" src="<%=path%>/js/jquery-1.6.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jquery.edatagrid.js"></script>
</head>

	<body>
		<div class="container">
			<s:if test="#session.isAdmin">
			<div class="my_link">
				<ul class="links">
					<li><a href="index" class="easyui-linkbutton"
						style="width: 300px"><span class="l-btn-left"
							style="margin-top: 0px"> <span class="l-btn-text"
								style="font-size: 15px; font-weight: bold">Home</span></span></a></li>
					<li><a href="goCrudUser" class="easyui-linkbutton"
						style="width: 300px"><span class="l-btn-left"
							style="margin-top: 0px"> <span class="l-btn-text"
								style="font-size: 15px; font-weight: bold">CRUD of Users</span></span></a></li>
					<li><a href="goCrudBook" class="easyui-linkbutton"
						style="width: 300px"><span class="l-btn-left"
							style="margin-top: 0px"> <span class="l-btn-text"
								style="font-size: 15px; font-weight: bold">CRUD of Books</span></span></a></li>
					<li><a href="goCrudOrder" class="easyui-linkbutton"
						style="width: 300px"><span class="l-btn-left"
							style="margin-top: 0px"> <span class="l-btn-text"
								style="font-size: 15px; font-weight: bold">CRUD of Orders</span></span></a></li>
					<li><a href="goCrudOrderitem" class="easyui-linkbutton"
						style="width: 300px"><span class="l-btn-left"
							style="margin-top: 0px"> <span class="l-btn-text"
								style="font-size: 15px; font-weight: bold">CRUD of Order
									items</span></span></a></li>
					<li><a href="statistics" class="easyui-linkbutton"
						style="width: 300px"><span class="l-btn-left"
							style="margin-top: 0px"> <span class="l-btn-text"
								style="font-size: 15px; font-weight: bold">Sales Statistics</span></span></a></li>
				</ul>
			</div>
			</s:if>