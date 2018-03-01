/**
 * 隐藏弹出框
 */
$(document).ready(function(){
	$(".pop_up").css("display","none");
});

/**
 * 单击各按钮和链接后相应动作
 */
$(document).ready(function(){
	$(".AddOneLevel").click(function(){
		$(".pop_up").css("display","block");
		$("#popTitle").text("添加一级分类");
		$("#selectCate").css("display","none");
		$("#caName").val("");
		$("#desc").val("");
	});
	$(".AddTwoLevel,this").click(function(){
		$(".pop_up").css("display","block");
		$("#popTitle").text("添加二级分类");
		$("#selectCate").css("display","none");
		$("#currentID").val($(this).attr("id"));
		$("#caName").val("");
		$("#desc").val("");
	});
	$(".modify1,this").click(function(){
		$(".pop_up").css("display","block");
		$("#popTitle").text("修改一级分类");
		$("#selectCate").css("display","none");
		$("#caName").val($(this).parents("tr").children("td:nth-child(2)").text());
		$("#desc").val($(this).parents("tr").children("td:nth-child(3)").text());
		$("#currentID").val($(this).parents("tr").attr("id"));
	});
	$(".modify2,this").click(function(){
		$(".pop_up").css("display","block");
		$("#popTitle").text("修改二级分类");
		$("#selectCate").css("display","block");
		$("#caName").val($(this).parents("tr").children("td:nth-child(2)").text());
		$("#desc").val($(this).parents("tr").children("td:nth-child(3)").text());
		var vpid=$(this).parents("tr").children("td:nth-child(1)").attr("id");
		$("option[value="+vpid+"]").attr("selected",true);
		$("#currentID").val($(this).parents("tr").attr("id"));
	});
	$(".del1,this").click(function(){
		var cname=$(this).parents("tr").children("td:nth-child(2)").text();
		if(confirm("是否删除一级分类 "+cname+" ？")) {
			$.ajax({
				type: "POST",
				dataType: "json",
				data: {id: $(this).parents("tr").attr("id")},
				url: "/Horizon/admin/category_product/DelParentCategoryCtrl",
				success: function(data) {
					if(eval(data).msg=="ok") {
						location.reload(true);
					} else {
						alert("该分类下还有二级分类，不能删除。");
					}
				},
				error: function() {
					alert("操作异常，无法删除。");
				}
			});
		}
	});
	$(".del2,this").click(function(){
		var cname=$(this).parents("tr").children("td:nth-child(2)").text();
		if(confirm("是否删除二级分类 "+cname+" ？")) {
			$.ajax({
				type: "POST",
				dataType: "json",
				data: {id: $(this).parents("tr").attr("id")},
				url: "/Horizon/admin/category_product/DelChildCategoryCtrl",
				success: function(data) {
					if(eval(data).msg=="ok") {
						location.reload(true);
					} else {
						alert("该二级分类下还有商品，不能删除。");
					}
				},
				error: function() {
					alert("操作异常，无法删除。");
				}
			});
		}
	});
	$("#ok").click(function(){
		if($("#caName").val() == "" || $("#desc").val() == "") {
			alert("请将信息填写完整。");
		} else{
			switch($("#popTitle").text()) {
			case "添加一级分类":
				var url = "/Horizon/admin/category_product/AddParentCategoryCtrl";
				break;
			case "添加二级分类":
				var url = "/Horizon/admin/category_product/AddChildCategoryCtrl";
				break;
			case "修改一级分类":
				var url = "/Horizon/admin/category_product/EditParentCategoryCtrl";
				break;
			case "修改二级分类":
				var url = "/Horizon/admin/category_product/EditChildCategoryCtrl";
				break;
			}
			
			$.ajax({
				type: "POST",
				dataType: "json",
				data: {
						id: $("#currentID").val(),
						pid: $("#pid").val(),
						caName: $("#caName").val(),
						desc: $("#desc").val()
					},
				url: url,
				success: function() {
					location.reload(true);
				},
				error: function() {
					alert("数据提交异常。");
				}
			});
		}
	});
	$("#cancel").click(function(){
		$(".pop_up").css("display","none");
	});
});
