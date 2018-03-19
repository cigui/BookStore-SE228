<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="../layouts/headerForCrud.jsp"%>
			<s:if test="#session.isAdmin">
			<div class="my_block">
				<h2>CRUD of Users</h2>
				<p>Double click the row to begin editing.</p>
				<table id="dg" title="My Users" style="width: 800px; height: 250px"
					toolbar="#toolbar" pagination="true" idField="id" rownumbers="true"
					fitColumns="true" singleSelect="true">
					<thead>
						<tr>
							<th field="id" width="50">Id</th>
							<th field="usrName" width="50"
								editor="{type:'validatebox',options:{required:true}}">User
								Name</th>
							<th field="pwd" width="50"
								editor="{type:'validatebox',options:{required:true}}">Password</th>
							<th field="phone" width="50"
								editor="{type:'validatebox',options:{required:true}}">Phone</th>
							<th field="email" width="50"
								editor="{type:'validatebox',options:{required:true,validType:'email'}}">Email</th>
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
						onclick="javascript:$('#dg').edatagrid('saveRow')">Save</a>
				</div>
				<script type="text/javascript">
					$(function() {
						$('#dg').edatagrid({
							url : 'userAll',
							saveUrl : 'userAdd',
							updateUrl : 'userUpdate',
							destroyUrl : 'userDelete'
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