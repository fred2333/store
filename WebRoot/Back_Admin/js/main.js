/**
 * 管理界面左栏选项单击选中效果
 */
$(document).ready(function(){ 
	$(".itemStyle").click(function(e){
		$(".itemStyle").css({
			"border-right-width": "0px",
			"background-color": ""
		});
		$(e.target).css({
			"border-right-width": "6px",
			"background-color": "#354457"
		});
	});
});

/**
 * 左栏与iframe高度自动调整
 */
$(document).ready(function(){ 
	if($(window).height() < 600){
		$(".left").height(600);
		$("iframe").height(530);
	} else {
		$(".left").height($(window).height());
		$("iframe").height($(window).height()-70);
	}
	$(window).resize(function() {
		if($(window).height() < 600){
			$(".left").height(600);
			$("iframe").height(530);
		} else {
			$(".left").height($(window).height());
			$("iframe").height($(window).height()-70);
		}
	});
});

/**
 * 单击各项打开相应嵌套网页
 */
$(document).ready(function(){ 
	$(".itemStyle").click(function(e){
		var itemName = $(e.target).text();
		switch(itemName) {
		case "店铺开通审核":
			$("iframe").attr("src", "/Horizon/admin/ma_shop/ShopManagementCtrl?method=shopVerifyList&keyword=&currentPage=1");
			break;
		case "商品类别管理":
			$("iframe").attr("src", "/Horizon/admin/category_product/FindAllCategoryCtrl");
			break;
		case "顾客个人信息":
			$("iframe").attr("src", "/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getUserList&keyword=&currentPage=1");
			break;
		case "顾客交易记录":
			$("iframe").attr("src", "/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getTrRecordList&loginName=&currentPage=1");
			break;
		case "顾客问题反馈":
			$("iframe").attr("src", "/Horizon/admin/ma_customer/CustomerManagementCtrl?method=getIssueList&keyword=&currentPage=1");
			break;
		case "修改个人密码":
			$("iframe").attr("src", "/Horizon/Back_Admin/changePwd.jsp");
			break;
		}
	});
});
