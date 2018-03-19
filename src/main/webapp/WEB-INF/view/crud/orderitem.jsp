<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="../layouts/headerForCrud.jsp"%>
			<s:if test="#session.isAdmin">
			<div class="my_block">
				<h2>CRUD of Order Items</h2>
				<p>Double click the row to begin editing.</p>
				<table id="dg" title="My Books" style="width: 800px; height: 250px"
					toolbar="#toolbar" pagination="true" idField="id" rownumbers="true"
					fitColumns="true" singleSelect="true">
					<thead>
						<tr>
							<th field="id" width="20"">Id</th>
							<th field="order_id" width="80"
								editor="{type:'combobox',options:{required:true, url: 'orderIdList', valueField:'id',
    							textField:'text'}}">Id
								of the Order</th>
							<th field="book_ISBN" width="57"
								editor="{type:'combobox',options:{required:true, url: 'bookIsbnList', valueField:'id',
    							textField:'text'}}">ISBN
								of the Book</th>
							<th field="number" width="50"
								editor="{type:'validatebox',options:{required:true}}">Number</th>
							<th field="unit_price" width="50">Unit price</th>
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
							url : 'itemAll',
							saveUrl : 'itemAdd',
							updateUrl : 'itemUpdate',
							destroyUrl : 'itemDelete'
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