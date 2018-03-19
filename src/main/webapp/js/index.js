$(function() {
	$("button.bk-button").click(
			function() {
				var id = $(this).data("bid");
				if (cur == id) {
					cur = 0;
					$("#info").fadeOut();
					$("#cover").fadeOut();
					return;
				}
				$.getJSON("bookInfo", {
					ISBN : id
				}, function(json) {
					cur = id;
					$("#info").text(
							"ISBN: " + json.ISBN + "\n" + "Title: "
									+ json.title + "\n" + "Author: "
									+ json.author + "\n" + "Price: "
									+ json.price + "\n" + "Publisher: "
									+ json.publisher + "\n" + "Category: "
									+ json.category + "\n" + "Inventory: "
									+ json.inventory + "\n");
					$("#info").fadeIn();
				});
				$("#cover").attr('src','getCover?isbn='+id);
				$("#cover").fadeIn();
			});

	$('#dataTables').DataTable({
		responsive : true,
		searching:false
	});

	$("button.bk-purchase").click(function() {
		var bid = $(this).data("bid");
		if (!logined) {
			bootbox.alert('请先登录！');
		}
		else {
			bootbox.prompt({
				title : "立即购买",
				inputType : 'number',
				onEscape : true,
				callback : function(result) {
					if (result <= 0) {
						bootbox.alert('无效输入！');
					} else {
						$.getJSON("placeOrder", {
							userId : userId,
							bookId : bid,
							number : result
						}, function(json) {
							if (json.error == "stock") {
								bootbox.alert({
									message : '库存不足！',
									callback : function() {
										location.reload();
									}
								})
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
				}
			});
		}
	});
	
	$("button.bk-sc").click(function() {
		var bid = $(this).data("bid");
		if (!logined) {
			bootbox.alert('请先登录！');
		}
		else {
			bootbox.prompt({
				title : "加入购物车",
				inputType : 'number',
				onEscape : true,
				callback : function(result) {
					if (result <= 0) {
						bootbox.alert('无效输入！');
					} else {
						$.getJSON("addIntoShoppingCart", {
							userId : userId,
							bookId : bid,
							number : result
						}, function(json) {
							if (json.error == "undefined") {
								bootbox.alert({
									message : '购物车中已有该书籍！',
									callback : function() {
										location.reload();
									}
								});
							} else {
								bootbox.alert({
									message : '成功加入购物车！',
									callback : function() {
										location.reload();
									}
								});
							}
						});
					}
				}
			});
		}
	});
});
