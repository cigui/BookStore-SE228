<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.HashMap"%>
<%
	Map<Long, String> books = new HashMap<Long, String>();
	if (request.getAttribute("books") != null) {
		books = (Map<Long, String>) request.getAttribute("books");
	}
%>
<%@ include file="layouts/header.jsp"%>
<script src="<%=path%>/js/index.js"></script>
<script>
	var cur = 0;
	var userId = "<s:property value='#session.userId' />";
	var logined = "<s:property value='#session.logined' />";
</script>


	<div class="bs-body">
		<div class="bs-center">
			<div class="bs-info">
				<pre id="info" style="display: none;"></pre>
				<img id="cover" src="" style="display: none;" />
			</div>
			<div class="bs-list">
				<table class="table table-striped table-bordered table-hover"
					id="dataTables">
					<thead>
						<tr>
							<th width="0">Title</th>
							<th width="1"></th>
						</tr>
					</thead>
					<tbody>
						<%
							for (Map.Entry<Long, String> entry : books.entrySet()) {
						%>
						<tr>
							<td>
								<button class="btn btn-default btn-lg bk-button"
									data-bid=<%=entry.getKey()%>>
									<%=entry.getValue()%>
								</button>
							</td>
							<td style="text-align: center">
								<button class="btn btn-default btn-sm bk-sc"
									data-bid=<%=entry.getKey()%>>加入购物车</button>
								<button class="btn btn-default btn-sm bk-purchase"
									data-bid=<%=entry.getKey()%>>立即购买</button>
							</td>
						</tr>
						<%
							}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>
