<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
<link rel="stylesheet" type="text/css" href="/Horizon/Back_Shop/css/addProduct.css">
<link rel="stylesheet" type="text/css" href="/Horizon/jQuery/jquery.datepick.css">
<script type="text/javascript" src="/Horizon/jQuery/jquery.datepick.js"></script>
<script type="text/javascript" src="/Horizon/jQuery/jquery.datepick-zh-CN.js"></script>
<script type="text/javascript" src="/Horizon/jQuery/jquery.form.js"></script>
<script type="text/javascript">
$(function () {
	$("input").click(function() {
		$("#msg").text("");
	});
	$("input[name='shopid']").val($(".shopID").val());
	$("#proDate").datepick({dateFormat:"yy-mm-dd"});
	$("#purDate").datepick({dateFormat:"yy-mm-dd"});
	
	$("#btn").addClass("btn1");
	$("#btn").hover(
		function() {
			$("#btn").removeClass("btn1");
			$("#btn").addClass("btn2");
		},
		function() {
			$("#btn").removeClass("btn2");
			$("#btn").addClass("btn1");
		}
	);
	
	$("#btn").click(function() {
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
		var image_w = $("#image_w").val();
		var image_b = $("#image_b").val();
		
		if(!productName || !currPrice || !price || !brand || !productNum || !proDate || !purDate || !productDesc || !pid || !cid || !image_w || !image_b) {
			alert("请将商品信息填写完整。");
			return false;
		}
		
		if(isNaN(currPrice) || isNaN(price)) {
			alert("定价、促销价必须是合法的小数！");
			return false;
		}
		$("#proForm").ajaxSubmit({
			success: function(data) {
		    	$("#msg").text(data);
			}
		});
	});
	
	$("#return").click(function() {
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
});

// 用于加载二级分类
// 得到一个数组
// 获取cid元素，把内部的<option>全部删除
// 添加一个头（<option>请选择2级分类</option>）
// 循环数组，把数组中每个对象转换成一个<option>添加到cid中
function loadChildren() {
	var pid = $("#pid").val();
	$.ajax({
		async:true,
		cache:false,
		url:"/Horizon/category_product/FindChildCategoryCtrl",
		data:{pid:pid},
		type:"POST",
		dataType:"json",
		success:function(arr) {
			$("#cid").empty();//清空下拉列表的子元素
			$("#cid").append($("<option>====请选择2级分类====</option>"));// 添加第一项
			
			// 循环遍历数组，把每个对象转换成<option>添加到cid中
			for(var i = 0; i < arr.length; i++) {
				var option = $("<option>").val(arr[i].cid).text(arr[i].cname);
				$("#cid").append(option);
			}
		}
	});
}

</script>
  </head>
  
  <body>
  <div class="addProMain" align="left">
   <p style="font-size: 16px; font-weight: 900; color: red;" id="msg"></p>
   <form action="/Horizon/product/AddProductCtrl" method="post" enctype="multipart/form-data" id="proForm">
    <div>
	    <ul class="addProUl">
	    	<li class="addProLi">商品名：　<input id="productName" type="text" name="productName"  style="width:500px;"/></li>
	    	<li class="addProLi">大图： 　
	    	  <input id="image_w" type="file" name="image_w"/>350*350像素大小</li>
	    	<li class="addProLi">小图： 　
	    	  <input id="image_b" type="file" name="image_b"/>200*200像素大小</li>
	    	<li class="addProLi">定&nbsp;价：&nbsp;
	    	  <input id="price" type="text" name="price" style="width:60px;"/>	元</li>
	    	<li class="addProLi">促销价：　
	    	  <input id="currPrice" type="text" name="currPrice" style="width:60px;"/>	元</li>
	    </ul>
		<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
		<table>
			<tr>
				<td align="right" valign="middle">
					商品品牌：</td>
			    <td colspan="2"><input type="text" id="brand" name="brand" style="width:300px;"/></td>
			    <td width="280">&nbsp;</td>
			    <td width="5">&nbsp;</td>
			</tr>
			<tr>
				<td align="right" valign="middle">
					库存数量：</td>
			    <td colspan="2"><input type="text" name="productNum" id="productNum" style="width:60px;"/>
件 </td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			</tr>
			<tr>
				<td align="right" valign="middle">生产日期：</td>
			    <td colspan="2"><input type="text" id="proDate" name="proDate" style="width:100px;"/></td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			</tr>
			<tr>
				<td align="right" valign="middle">进货日期：</td>
			    <td colspan="2"><input type="text" id="purDate" name="purDate" style="width:100px;"/></td>
			    <td>&nbsp;</td>
			    <td>&nbsp;</td>
			</tr>
			<tr>
				<td width="74" align="right" valign="middle">
					一级分类：				</td>
				<td width="190"><select name="pid" id="pid" onChange="loadChildren()">
                  <option value="">====请选择1级分类====</option>
                  <c:forEach items="${parents }" var="parent">
                    <option value="${parent.cid }">${parent.cname }</option>
                  </c:forEach>
                </select></td>
				<td width="107" align="right" valign="middle">二级分类：</td>
<td><select name="cid" id="cid">
						<option value="">====请选择2级分类====</option>
					</select>				</td>
				<td></td>
			</tr>
			<tr>
				<td colspan="4">商品说明：　　<textarea name="productDesc" id="productDesc" style="margin: 0px; width: 602px; height: 148px;"></textarea></td>
			</tr>
			<tr>
				<td colspan="4">
					<input type="button" id="btn" class="btn" value="添     加" style="float:left;background-color: #ff4f00;">
					<input type="button" id="return"  class="btn" value="返     回" style="margin-left: 30px;background-color: #ff9600;">
					<input type="reset" id="reset"  class="btn" value="清     空" style="margin-left: 30px;background-color: #ff9600;">
				</td>
			</tr>
	  </table>
	</div>
	<input type="hidden" name="shopid" >
   </form>
  </div>

  </body>
</html>
