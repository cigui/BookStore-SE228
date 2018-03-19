<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="model.Order"%>
<%@ page import="model.Orderitem"%>
<%
	ArrayList<Order> orders = new ArrayList<Order>();
	Map<Integer, List<Orderitem>> orderitems = new HashMap<Integer, List<Orderitem>>();
	Map<Long, String> books = new HashMap<Long, String>();
	if (request.getAttribute("orders") != null) {
		orders = (ArrayList<Order>) request.getAttribute("orders");
	}
	if (request.getAttribute("orderitems") != null) {
		orderitems = (Map<Integer, List<Orderitem>>) request.getAttribute("orderitems");
	}
	if (request.getAttribute("books") != null) {
		books = (Map<Long, String>) request.getAttribute("books");
	}
%>
<%@ include file="layouts/header.jsp"%>
<script> document.title = "My Orders - BookStore"; </script>
<s:if test="#session.logined">
		<div class="bs-body">
			<div class="bs-center container">
				<%
					for (int i = 0; i < orders.size(); i++) {
							Order order = orders.get(i);
							List<Orderitem> cur = orderitems.get(order.getId());
				%>
				<div class="panel panel-info">
					<div class="panel-heading">
						<h3 class="panel-title">
							<span>订单号：<%=order.getId()%>, 总价： <%=order.getSum()%></span>
						</h3>
					</div>
					<ul class="list-group">
						<%
							for (int j = 0; j < cur.size(); j++) {
								Orderitem oi = cur.get(j);
								long isbn = oi.getBook_ISBN();
						%>
						<li class="list-group-item">Book ISBN: <%=isbn%>, 
							Book Title: <%=books.get(isbn)%>, 
							Number: <%=oi.getNumber()%>, 
							Unit Price: <%=oi.getUnit_price()%></li>
						<%
							}
						%>
					</ul>
				</div>
				<%
					}
				%>
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