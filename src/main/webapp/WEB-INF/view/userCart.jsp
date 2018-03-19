<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="model.Book"%>
<%@ page import="model.ShoppingCartItem"%>
<%
	ArrayList<ShoppingCartItem> cart = new ArrayList<ShoppingCartItem>();
	Map<Long, String> books = new HashMap<Long, String>();
	if (request.getAttribute("cart") != null) {
		cart = (ArrayList<ShoppingCartItem>) request.getAttribute("cart");
	}
	if (request.getAttribute("cartBooks") != null) {
		books = (Map<Long, String>) request.getAttribute("cartBooks");
	}
%>

<%@ include file="layouts/header.jsp"%>
<script src="<%=path%>/js/cart.js"></script>
<script>
	var userId = "<s:property value='#session.userId' />";
	var logined = "<s:property value='#session.logined' />";
	document.title = "My Cart - BookStore";
</script>

<s:if test="#session.logined">
		<div class="bs-body">
			<div class="bs-center container">
				<div class="panel panel-info">
					<table class="table table-striped" id="cart-tb">
						<tr>
							<th>ISBN</th>
							<th>Title</th>
							<th>Number</th>
							<th>Operation</th>
						</tr>

						<%
								for (ShoppingCartItem item : cart) {
						%>
						<tr class="cart-item">
							<td class="c-isbn"><%=item.getBook_id()%></td>
							<td class="c-title"><%=books.get(item.getBook_id())%></td>
							<td class="c-number"><%=item.getNumber()%></td>
							<td class="c-ops">
							<button class="btn btn-default bs-change" data-bid=<%=item.getBook_id()%>>Change Number</button> | 
							<button class="btn btn-default bs-delete" data-bid=<%=item.getBook_id()%>>Delete</button>
							<input type="checkbox" name="checkbox" data-bid=<%=item.getBook_id()%> data-num=<%=item.getNumber()%> /></td>
						</tr>
						<%
							}
						%>
					</table>

					<hr />

					<div class="panel-body">
						<div class="row btn-row">
							<div class="col-md-3" style="float: right">
								<button class="btn btn-block btn-primary bs-place" >Place Order</button>
							</div>
						</div>
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