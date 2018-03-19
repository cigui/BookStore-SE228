<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="../layouts/headerForCrud.jsp"%>
			<s:if test="#session.isAdmin">
			<div class="my_block">
				<h2>CRUD of Orders</h2>
				<p>Double click the row to begin editing.</p>
				<table id="dg" title="My Orders" style="width: 800px; height: 250px"
					toolbar="#toolbar" pagination="true" idField="id" rownumbers="true"
					fitColumns="true" singleSelect="true">
					<thead>
						<tr>
							<th field="id" width="50">Id</th>
							<th field="user_id" width="50"
								editor="{type:'combobox',options:{required:true, url: 'userIdList', valueField:'id',
    textField:'text'}}">Id
								of the customer</th>
							<th field="order_time" width="50">Time</th>
							<th field="orderitems" width="50">Order items</th>
							<th field="sum" width="50">Sum</th>
						</tr>
					</thead>
				</table>
				<div id="toolbar">
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-add" plain="true"
						onclick="javascript:$('#dg').edatagrid('addRow')">New</a> <a
						href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-remove" plain="true"
						onclick="javascript:$('#dg').edatagrid('destroyRow')">Destroy</a>
					<a href="javascript:void(0)" class="easyui-linkbutton"
						iconCls="icon-save" plain="true"
						onclick="javascript:$('#dg').edatagrid('saveRow')">Save</a> <span
						style="font-size: 12px; font-weight: bold">Note: No change
						if foreign key constrain fails!</span>
				</div>
				<script type="text/javascript">
					$(function() {
						$('#dg').edatagrid({
							url : 'orderAll',
							saveUrl : 'orderAdd',
							updateUrl : 'orderUpdate',
							destroyUrl : 'orderDelete'
						});
					});
				</script>
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