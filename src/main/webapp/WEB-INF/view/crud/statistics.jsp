<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="model.User"%>
<%@ page import="model.Book"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashSet"%>

<%
	ArrayList<User> users = new ArrayList<User>();
	HashSet<String> categories = new HashSet<String>();
	if (request.getAttribute("categories") != null) {
		categories = (HashSet<String>) request.getAttribute("categories");
	}
	if (request.getAttribute("users") != null) {
		users = (ArrayList<User>) request.getAttribute("users");
	}
%>

<%@ include file="../layouts/header.jsp"%>
<script> document.title = "Statistics"; </script>

<s:if test="#session.isAdmin">
		<div class="bs-body">
			<div class="bs-center container" style="padding-top: 50px">
				<div class="row">
					<div class="col-md-12">
						<h3>Search sales statistics by specific users</h3>
						<form action="statisticsByUsers">
							<div class="form-group" id="userId">
								<label for="userId">User Id:</label> <select
									class="form-control" name="userId">
									<%
										for (int i = 0; i < users.size(); i++) {
												User user = users.get(i);
									%>
									<option value=<%=user.getId()%>><%=user.getId()%></option>
									<%
										}
									%>
								</select>
							</div>
							<div class="form-group" id="addUser">
								<button type="button" class="btn btn-default btn-sm"
									id="addUserButton">add</button>
							</div>
							<button class="btn btn-default btn-sm" type="submit">Go!</button>
						</form>
					</div>
				</div>
				<div class="row">
					<div class="col-md-12">
						<h3>Search sales statistics by dates</h3>
						<form action="statisticsByDates">
							<div class="form-group" id="Dates">
								<label for="startingDate">Starting Date:</label>
								<input type="date" class="form-control" id="startingDate" name="startingDate"
										placeholder="Starting Date" required="true">
							</div>
							<div class="form-group" id="Dates">
								<label for="endingDate">Ending Date:</label>
								<input type="date" class="form-control" id="endingDate" name="endingDate"
										placeholder="Ending Date" required="true">
							</div>
							<button class="btn btn-default btn-sm" type="submit">Go!</button>
						</form>
					</div>
					<div class="col-md-12">
						<h3>Search sales statistics by categories</h3>
						<form action="statisticsByCategories">
							<div class="form-group" id="categories">
								<label for="categories">Category:</label>
								<select
									class="form-control" name="categories">
									<%
										for (String category : categories) {
									%>
									<option value=<%=category%>><%=category%></option>
									<%
										}
									%>
								</select>
							</div>
							<div class="form-group" id="addCate">
								<button type="button" class="btn btn-default btn-sm"
									id="addCateButton">add</button>
							</div>
							<button class="btn btn-default btn-sm" type="submit">Go!</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</s:if>
<script>
	$(document).ready(function() {
		$("#addUserButton").click(function() {
			var select = $("#userId").clone();
			$("#addUser").before(select);
		});
		$("#addCateButton").click(function() {
			var select = $("#categories").clone();
			$("#addCate").before(select);
		});
	});
</script>
<s:else>
	<%
		response.sendRedirect("index");
	%>
</s:else>
</html>