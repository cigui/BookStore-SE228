<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>

<%@ include file="../layouts/headerForCrud.jsp"%>
			<s:if test="#session.isAdmin">
			<div class="my_block">
				<h2>CRUD of Books</h2>
				<p>Double click the row to begin editing.</p>
				<table id="dg" title="My Books" style="width: 800px; height: 250px"
					toolbar="#toolbar" pagination="true" idField="id" rownumbers="true"
					fitColumns="true" singleSelect="true">
					<thead>
						<tr>
							<th field="id" width="57"
								editor="{type:'validatebox',options:{required:true}}">ISBN</th>
							<th field="book_name" width="80"
								editor="{type:'validatebox',options:{required:true}}">Title</th>
							<th field="book_author" width="50"
								editor="{type:'validatebox',options:{required:true}}">Author</th>
							<th field="book_price" width="50"
								editor="{type:'validatebox',options:{required:true}}">Price</th>
							<th field="book_publisher" width="50"
								editor="{type:'validatebox',options:{required:true}}">Publisher</th>
							<th field="book_category" width="50"
								editor="{type:'validatebox',options:{required:false}}">Category</th>
							<th field="inventory" width="50"
								editor="{type:'validatebox',options:{required:true}}">Inventory</th>
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
							url : 'bookAll',
							saveUrl : 'bookAdd',
							updateUrl : 'bookUpdate',
							destroyUrl : 'bookDelete'
						});
					});
				</script>

				<div class="uploadCover">
					<h2>上传封面</h2>
					<div id="form" method="post" enctype="multipart/form-data">
						<div>
							<label for="ISBN">书本的ISBN：</label> <input class="easyui-combobox"
								id="isbn" name="isbn"
								data-options="url: 'bookIsbnList', valueField:'id',
    							textField:'text'" />
						</div>
						<div>
							<label for="uploadCover">封面：</label> <input type="file"
								id="uploadCover" name="file" />
						</div>
						<button class="easyui-linkbutton" id="sub">上传</button>
					</div>
				</div>
			</div>
		</div>
	</body>
	</s:if>

<script>
	$(document).ready(function() {
		$('#sub').click(function() {
			var url = "bookUploadCover"
			var formData = new FormData();
			formData.append("file", $("#uploadCover")[0].files[0]);
			formData.append("isbn", $('#isbn').val());
			$.ajax({
				url : url,
				type : 'POST',
				data : formData,
				processData : false,
				contentType : false,
				success : function(data) {
					$.messager.alert('Success!','Successfully upload!', 'info');
				}
			});
		});
	});
</script>

	<s:else>
		<%
			response.sendRedirect("index");
		%>
	</s:else>

</html>