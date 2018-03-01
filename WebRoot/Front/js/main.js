/**
 * 当浏览器窗口变化时元素位置自适应
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
 * 设置搜索类型切换样式
 */
$(document).ready(function(){
	// 搜索选择默认为“商品”
	$("#select2").css("background-color","#FF6600");
	$("#select2").css("color","white");
	$("#select1").css("background-color","white");
	$("#select1").css("color","black");
	// 搜索选择样式切换
	$("#select1").click(function() {
		$("#select1").css("background-color","#FF6600");
		$("#select1").css("color","white");
		$("#select2").css("background-color","white");
		$("#select2").css("color","black");
		$("#panduan").val("dianpu");
	});
	$("#select2").click(function() {
		$("#select2").css("background-color","#FF6600");
		$("#select2").css("color","white");
		$("#select1").css("background-color","white");
		$("#select1").css("color","black");
		$("#panduan").val("shangping");
	});
});

/**
 * 滚动时搜索框和列表切换悬浮样式
 */
$(document).ready(function(){
	$(document).scroll(function() {
		if($("#scrollFlag").offset().top-$("body").scrollTop() < 30) {
			if($(".searchArea").css("position")=="relative") {
				$(".searchArea").css("position", "fixed");
				$(".searchArea").css("top", "-10px");
				$(".searchArea").css("left", $(".bodyArea").css("left"));
				$(".searchArea").css("margin-left", -210);
				
				$(".list").css("position", "fixed");
				$(".list").css("top", "10px");
				$("#imgTitle").attr("src","/Horizon/Front/img/mallTitle_small.PNG");
				$("#imgTitle").attr("height","36");
				$("#imgTitle").attr("width","163");
				$(".mallTitle").height(40);
				
				$(".listContent").height($(window).height()-115);
				$(".listContent").css("overflow", "hidden");
			}
		} else {
			if($(".searchArea").css("position")=="fixed") {
				$(".searchArea").css("position", "relative");
				$(".searchArea").css("top", "");
				$(".searchArea").css("left", "");
				$(".searchArea").css("margin-left", "");
				
				$(".list").css("position", "relative");
				$(".list").css("top", "");
				$("#imgTitle").attr("src","/Horizon/Front/img/mallTitle.PNG");
				$("#imgTitle").attr("height","72");
				$("#imgTitle").attr("width","173");
				$(".mallTitle").height(100);
				
				$(".listContent").css("overflow", "visible");
				$(".listContent").css("height","");
			}
		}
	});
});

/**
 * 加载列表滚动条
 */
(function($){
    $(document).ready(function(){
        $(".listContent").mCustomScrollbar({
            theme: "minimal",
            advanced: { autoExpandHorizontalScroll: true }
        });
        $(".mCSB_dragger_bar").css({ "background-color": "#FF6600" });
    });
})(jQuery);

/**
 * 获取列表项
 */
$(document).ready(function() {
	var num = 0;
	$.ajax({
		type: "get",
		url: "/Horizon/CategoryList",
		success: function(html) {
			$(".listContent").html(html);
			num = $("#getParNum").val() + 1;
			//列表项滑动样式
			for(var i=1; i<num; i++) {
				(function(){
					var index = i;
					$("#l1_"+i).click(function(){
						for(var i2=1; i2<num; i2++) {
							if(i2 == index) {
								$("#l2_"+i2).slideToggle(200);
							} else {
								$("#l2_"+i2).slideUp(200);
							}
						}
					});
				})();
			}
			$(".listLayer2").click(function(){
				$(".listItem1").css({ "background-color": "#7fc6d2" });
			});
			$(".listLayer1").click(function(){
				$(".listItem1").css({ "background-color": "#7fc6d2" });
			});
			//二级分类单击效果
			$(".listLayer2,this").click(function(){
				$(".listLayer2").css({"border-left-width": "1px"});
				$(this).css({"border-left-width": "7px"});
			});
		},
		error: function() {
			$(".listContent").html("<h3 align='center'><font color='white'>Error</font><h3>");
		}
	});
});

/**
 * 排行榜按钮样式
 */
$(document).ready(function(){
	$(".listItem1").css({ "background-color": "#FF4646" });
	$(".listItem1").click(function(){
		url="/Horizon/ProductServlet?method=findrank",
		$(".zz").attr("src",url);
		$(".listItem1").css({ "background-color": "#FF4646" });
		$("div[id^='l2']").slideUp(200);
	});
});

/**
 * 显示排行榜列表
 */
$(document).ready(function(){ 
	url="/Horizon/ProductServlet?method=findrank",
		$(".zz").attr("src",url);
});

/**
 * 搜索商品
 */
$(document).ready(function(){ 
	$(".searchButton").click(function() {
		var aaa=$("input[name='inputBox']").val();
		var panduan=$("#panduan").val();
		if(panduan=="shangping")
		{url="/Horizon/ProductServlet?method=findByBname&productName="+aaa;
		$(".zz").attr("src",url);}
		else if(panduan=="dianpu"){
			url="/Horizon/ProductServlet?method=findShop&shopname="+aaa;
			$(".zz").attr("src",url);
		}
	});
	// 监听输入框的回车操作
	$("input[name='inputBox']").bind('keypress',function(event){  
	    if(event.keyCode == "13") $(".searchButton").click();  
	});
});
