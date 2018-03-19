$(function() {
	$("button.bs-delete").click(
		function(){
			var isbn = $(this).data("bid");
			var item = $(this).parent().parent();
			bootbox.confirm({
					buttons : {
						confirm : {
							label : 'Delete'
						},
						cancel : {
							label : 'Cancel'
						}
					},
					message : 'Serious?',
					callback : function(result) {
						if (result) {
							$.getJSON('deleteFromShoppingCart',
								{
									userId : userId,
									isbn : isbn,
								},
								function(json) {
									if (json.error=="undefined") {
										bootbox.alert({
											message : '未知错误！',
											callback : function() {
												location.reload();
											}
										});
									} else {
										bootbox.alert({
											message : '成功删除！',
											callback : function() {
												item.remove();
											}
										});
									}
								}
							)
						}
					}
			});
		}
	);
	$("button.bs-change").click(
		function() {
			var isbn = $(this).data("bid");
			var num = $(this).parent().parent().children()[2];
			var checkbox = $(this).parent().children()[2];
			bootbox.prompt({
				title : "Change number",
				inputType : 'number',
				onEscape : true,
				callback : function(result) {
					if (result <= 0) {
						bootbox.alert('无效输入！');
					} else {
						$.getJSON("changeItemNumber", {
							userId : userId,
							isbn : isbn,
							number : result
						}, function(json) {
							if (json.error == "undefined") {
								bootbox.alert({
									message : '未知错误！',
									callback : function() {
										location.reload();
									}
								});
							} else {
								bootbox.alert({
									message : '成功修改！',
									callback : function() {
										$(num).text(result);
										$(checkbox).data("num", result);
									}
								});
							}
						});
					}
				}
			});
		}
	);
	$("button.bs-place").click(
		function() {
			var checkedItems = [];
			$("[name='checkbox']").each(
				function() {
					if (this.checked) {
						var isbn = $(this).data("bid");
						var cnt = $(this).data("num");
						checkedItems.push(isbn);
					}
				}
			);
			var jsonstr = JSON.stringify(checkedItems);
			console.log(jsonstr);
			$.getJSON("purchase",
			{	
				userId : userId,
				json : jsonstr
			},
			function(data){
				if (data.error == "stock") {
					bootbox.alert({
						message : '部分书籍库存不足，请至订单页面查看详细信息！',
						callback : function() {
							location.reload();
						}
					});
				} else {
					bootbox.alert({
						message : '成功下单！',
						callback : function() {
							location.reload();
						}
					});
				}
			});
		}
	)
})