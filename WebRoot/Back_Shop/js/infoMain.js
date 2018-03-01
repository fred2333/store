/**
 * 初始化信息页面
 */
$(function(){ 
	// 初始化按钮事件
	function ex(e) {  // 单击确定
		$.ajax({
			type: "POST",
			url: "/Horizon/shopInfo/UpdateShopInfoCtrl",
			dataType: "JSON",
			data: {
				shopid: $("input.shopID").val(),
				sellerid: null,
				shopName: $("input[name='shopName']").val(),
				busi: $("input[name='busi']").val(),
				descr: $("textarea[name='desc']").val(),
				pro_src: $("input[name='proSrc']:checked").val(),
				phy_store: $("#phy").attr('checked'),
				fac_rep: $("#fac").attr('checked'),
				sellerName: $("input[name='seller']").val(),
				telNumber: $("input[name='telNumber']").val(),
				address: $("input[name='address']").val(),
				postcode: $("input[name='postCode']").val()
			},
			success: function(html) {
				$(".mainArea").html(html);
			},
			error: function() {
				$(".mainArea").html("<h2 align='center'><b>网页加载异常</b><h3>");
			}
		});

		$("#btn2").css("display","none");
		$("#btn1").html("<b>修改信息</b>");
		$("input[type='text']").css({border:"none"});
		$("textarea[name='desc']").css({border:"none"});
		$("input[type='text']").attr("readonly","readonly");
		$("textarea[name='desc']").attr("readonly","readonly");
		$(".modifyTr").css({display:"none"});
		$(".showTr").css({display:""});
		$("#btn1").unbind("click",ex);
	}
	$("#btn2").css("display","none");
	$("#btn1").click(function(e) {  // 单击修改信息
		$("#btn2").css("display","block");
		$("#btn1").html("<b>确定</b>");
		$("input[type='text']").css({border:""});
		$("textarea[name='desc']").css({border:""});
		$("input[type='text']").attr("readonly","");
		$("textarea[name='desc']").attr("readonly","");
		$(".modifyTr").css({display:""});
		$(".showTr").css({display:"none"});
		$("#btn1").click(ex);
	});
	$("#btn2").click(function(e) {  // 单击取消
		$("#btn2").css("display","none");
		$("#btn1").html("<b>修改信息</b>");
		$("input[type='text']").css({border:"none"});
		$("textarea[name='desc']").css({border:"none"});
		$("input[type='text']").attr("readonly","readonly");
		$("textarea[name='desc']").attr("readonly","readonly");
		$(".modifyTr").css({display:"none"});
		$(".showTr").css({display:""});
		$("#btn1").unbind("click",ex);
		
		$("input[name='proSrc'][value="+ $("#pro_srcVal").val() +"]").attr("checked",true);
		$("input[name='phy'][value="+ $("#phy_storeVal").val() +"]").attr("checked",true);
		$("input[name='fac'][value="+ $("#fac_repVal").val() +"]").attr("checked",true);
	});
	
	//初始化信息内容样式
	$("input[type='text']").css({border:"none"});
	$("textarea[name='desc']").css({border:"none"});
	$("input[type='text']").attr("readonly","readonly");
	$("textarea[name='desc']").attr("readonly","readonly");
	$(".modifyTr").css({display:"none"});
	$(".showTr").css({display:""});
	 
	$("input[name='proSrc'][value="+ $("#pro_srcVal").val() +"]").attr("checked",true);
	$("input[name='phy'][value="+ $("#phy_storeVal").val() +"]").attr("checked",true);
	$("input[name='fac'][value="+ $("#fac_repVal").val() +"]").attr("checked",true);
}) ;