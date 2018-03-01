/**
 * 当浏览器窗口变化时元素位置和尺寸自适应
 */
$(document).ready(function(){ 
	$(".mainArea").height($(window).height()-80);
	var w=$(window).width();
	var changeWidth = 930;
	if(w < 1200) {
		$(".bodyArea").width(changeWidth);
		if(w < changeWidth) {
			$(".bodyArea").css("left",(changeWidth/2)+"px");
			$(".bodyArea").css("margin-left",(-changeWidth/2)+"px");
		} else {
			$(".bodyArea").css("left","50%");
			$(".bodyArea").css("margin-left",(-changeWidth/2)+"px");
		}
	} else {
		$(".bodyArea").width(1200);
		$(".bodyArea").css("left","50%");
		$(".bodyArea").css("margin-left","-600px");
	}
	window.onresize = function() {
		$(".mainArea").height($(window).height()-80);
		var w=$(window).width();
		var changeWidth = 930;
		if(w < 1200) {
			$(".bodyArea").width(changeWidth);
			if(w < changeWidth) {
				$(".bodyArea").css("left",(changeWidth/2)+"px");
				$(".bodyArea").css("margin-left",(-changeWidth/2)+"px");
			} else {
				$(".bodyArea").css("left","50%");
				$(".bodyArea").css("margin-left",(-changeWidth/2)+"px");
			}
		} else {
			$(".bodyArea").width(1200);
			$(".bodyArea").css("left","50%");
			$(".bodyArea").css("margin-left","-600px");
		}
	};
}); 

$(function () {
	//刷新商品列表
	$.ajax({
		type: "get",
		url: "/Horizon/product/GetProductListCtrl",
		data: {
			cid: "all",
			shopid: $(".shopID", window.opener.document).val(),
			currentPage: "1"
		},
		success: function(html) {
			$(".productList", window.opener.document).html(html);
		},
		error: function() {
			$(".productList", window.opener.document).html("<h2 align='center'><b>网页加载异常</b><h3>");
		}
	});
	
	// 编辑和删除按钮样式
	$("#editBtn").addClass("editBtn1");
	$("#delBtn").addClass("delBtn1");
	$("#editBtn").hover(
		function() {
			$("#editBtn").removeClass("editBtn1");
			$("#editBtn").addClass("editBtn2");
		},
		function() {
			$("#editBtn").removeClass("editBtn2");
			$("#editBtn").addClass("editBtn1");
		}
	);
	$("#delBtn").hover(
		function() {
			$("#delBtn").removeClass("delBtn1");
			$("#delBtn").addClass("delBtn2");
		},
		function() {
			$("#delBtn").removeClass("delBtn2");
			$("#delBtn").addClass("delBtn1");
		}
	);
});



function editForm() {
	var productName = $("#productName").val();
	var currPrice = $("#currPrice").val();
	var price = $("#price").val();
	var brand = $("#brand").val();
	var productNum = $("#productNum").val();
	var proDate = $("#proDate").val();
	var purDate = $("#purDate").val();
	var productDesc = $("#productDesc").val();
	var pid = $("#pid").val();
	var cid = $("#cid").val();
	
	if(!productName || !currPrice || !price || !brand || !productNum || !proDate || !purDate || !productDesc || !pid || !cid ) {
		alert("请将商品信息填写完整。");
		return false;
	}
	
	if(isNaN(currPrice) || isNaN(price)) {
		alert("定价、促销价必须是合法的小数！");
		return false;
	}
	
	$("#form").submit();
}

function deleteForm() {
	if(confirm("确实要删除本商品吗？")) {
		$.ajax({
			type: "get",
			url: "/Horizon/product/DelProductCtrl" + window.location.search,
			success: function(html) {
				alert("删除成功。");
			},
			error: function() {
				alert("删除失败。");
			}
		});
		
		//刷新商品列表
		$.ajax({
			type: "get",
			url: "/Horizon/product/GetProductListCtrl",
			data: {
				cid: "all",
				shopid: $(".shopID", window.opener.document).val(),
				currentPage: "1"
			},
			success: function(html) {
				$(".productList", window.opener.document).html(html);
				close();
			},
			error: function() {
				$(".productList", window.opener.document).html("<h2 align='center'><b>网页加载异常</b><h3>");
				close();
			}
		});
	}
}