/**
 * 当浏览器窗口变化时元素位置和尺寸自适应
 */
$(document).ready(function(){ 
	$(".mainArea").height($(window).height()-80);
	var w=$(window).width();
	var changeWidth = 940;
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
		var changeWidth = 940;
		if(w < 1200) {
			$(".bodyArea").width(changeWidth);
			$(".searchArea").css({"margin-left": "10px"});
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
			$(".searchArea").css({"margin-left": "100px"});
		}
	};
}); 

/**
 * 加载默认嵌套页面
 */
$(document).ready(function(){
	$.ajax({
		type: "get",
		url: "/Horizon/shopInfo/GetShopInfoCtrl",
		success: function(html) {
			$(".mainArea").html(html);
		},
		error: function() {
			$(".mainArea").html("<h2 align='center'><b>网页加载异常</b><h3>");
		}
	});
}); 

/**
 * 标签单击事件
 */
$(document).ready(function(){ 
	$("#tab1").click(function() {
		$.ajax({
			type: "get",
			url: "/Horizon/shopInfo/GetShopInfoCtrl",
			success: function(html) {
				$(".mainArea").html(html);
			},
			error: function() {
				$(".mainArea").html("<h2 align='center'><b>网页加载异常</b><h3>");
			}
		});
	});
	$("#tab2").click(function() {
		$.ajax({
			type: "get",
			url: "/Horizon/Back_Shop/ma_product/proMain.jsp",
			success: function(html) {
				$(".mainArea").html(html);
			},
			error: function() {
				$(".mainArea").html("<h2 align='center'><b>网页加载异常</b><h3>");
			}
		});
	});
	$("#tab3").click(function() {
		$(".mainArea").html("<iframe src='/Horizon/order/OrderManagementCtrl?method=findAll&shopid="+ $(".shopID").val() + "'></iframe>");
		$("iframe").height($(".mainArea").height());
		$(window).resize(function() {
			$("iframe").height($(".mainArea").height());
		});
	});
}); 
