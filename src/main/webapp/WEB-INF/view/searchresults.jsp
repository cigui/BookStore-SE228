<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.Book"%>
<%
	ArrayList<Book> bookList = new ArrayList<Book>();
	if (request.getAttribute("books") != null) {
		bookList = (ArrayList<Book>) request.getAttribute("books");
	}
%>
<%@ include file="layouts/header.jsp"%>
<script src="<%=path%>/js/index.js"></script>

<script>
	var userId = "<s:property value='#session.userId' />";
	var logined = "<s:property value='#session.logined' />";
	document.title = "Search Results - BookStore";
</script>
	<div class="bs-body">
		<div class="bs-center container">
			<%
				for (int i = 0; i < bookList.size(); i++) {
					Book book = bookList.get(i);
			%>
			<div class="panel panel-info">
				<div class="panel-heading">
					<h3 class="panel-title">
						<span><%=book.getBook_name()%></span>
						<button class="btn btn-default btn-sm bk-sc"
							data-bid=<%=book.getId()%>>加入购物车</button>
						<button class="btn btn-default btn-sm bk-purchase"
							data-bid=<%=book.getId()%>>立即购买</button>
					</h3>
				</div>
				<div class="panel-body">
					<img class="img-responsive" src="getCover?isbn=<%=book.getId()%>"
						style="max-width: 40%" />
				</div>
				<ul class="list-group">
					<li class="list-group-item">ISBN: <%=book.getId()%></li>
					<li class="list-group-item">Author: <%=book.getBook_author()%></li>
					<li class="list-group-item">Publisher: <%=book.getBook_publisher()%></li>
					<li class="list-group-item">Price: <%=book.getBook_price()%></li>
					<li class="list-group-item">Inventory: <%=book.getInventory()%></li>
				</ul>
			</div>
			<%
				}
			%>
			<% if (bookList.size() == 0) {%>
			<div class="page-header">
				<h1 class="text-primary">
					Opps！<br> <small>Sorry, but it seems that we haven't got a book with this keyword yet :(</small>
				</h1>
			</div>
			<div class="row">
				<div class="col-md-4" style="font-size: 24px; color: #777">You may:</div>
			</div>
			<div class="row">
				<div class="col-md-6" style="text-align: center">
					<a href="index" class="btn btn-primary btn-lg">Go back to homepage</a>
				</div>
			</div>
			<% } %>
		</div>
	</div>
</body>
</html>