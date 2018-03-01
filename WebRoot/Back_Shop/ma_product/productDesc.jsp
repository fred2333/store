<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>${product.productName}</title>
    
    <link rel="stylesheet" type="text/css" href="/Horizon/Back_Shop/css/productDesc.css" >
	<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
    <script type="text/javascript" src="/Horizon/Back_Shop/js/productDesc.js"></script>
    
	<script type="text/javascript">
	$(function() {
		$("#box").attr("checked", false);
		$("#formDiv").css("display", "none");
		$("#show").css("display", "");	
		
		// 操作和显示切换
		$("#box").click(function() {
			if($(this).attr("checked")) {
				$("#show").css("display", "none");
				$("#formDiv").css("display", "");
			} else {
				$("#formDiv").css("display", "none");
				$("#show").css("display", "");		
			}
		});
	});
	
	function loadChildren() {
		/*
		1. 获取pid
		2. 发出异步请求，功能之：
		  3. 得到一个数组
		  4. 获取cid元素(<select>)，把内部的<option>全部删除
		  5. 添加一个头（<option>请选择2级分类</option>）
		  6. 循环数组，把数组中每个对象转换成一个<option>添加到cid中
		*/
		// 1. 获取pid
		var pid = $("#pid").val();
		// 2. 发送异步请求
		$.ajax({
			async:true,
			cache:false,
			url:"/Horizon/category_product/FindChildCategoryCtrl",
			data:{pid:pid},
			type:"POST",
			dataType:"json",
			success:function(arr) {
				// 3. 得到cid，删除它的内容
				$("#cid").empty();//删除元素的子元素
				$("#cid").append($("<option>====请选择2级分类====</option>"));//4.添加头
				// 5. 循环遍历数组，把每个对象转换成<option>添加到cid中
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
  <div class="topBar"><iframe frameborder="0" scrolling="no" src="<c:url value='/Back_Shop/top.jsp'/>" style="width: 100%;" id="top" name="top"></iframe></div>
  
  <div class="bodyArea">
	<div class="mallTitle">
	  <img id="imgTitle" src="/Horizon/Back_Shop/img/mallTitle.png" alt="Horizon店铺管理">
	</div>
	<div align="left" class="tabBar">
	  <div class="shopName">商品详情</div>
	</div>
    <div class="mainArea">
	      <input type="checkbox" id="box"><label for="box">编辑或删除</label>
	    <br/>
	    <br/>
	  <div id="show">
	    <div class="sm">${product.productName }</div>
	    <img align="top" src="${product.image_w }" class="tp"/>
	    <div id="Product" style="float:left;">
		    <ul class="ulStyle">
		    	<li class="liStyle" >商品编号：${product.productid }</li>
		    	<li class="liStyle" >促销价：<span class="price_n">&yen;${product.currPrice }</span></li>
		    	<li class="liStyle" >定价：<span style="text-decoration:line-through;">&yen;${product.price }</span></li>
		    </ul>
			<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
			<table class="tab">
				<tr>
					<td colspan="3">商品品牌：${product.brand }</td>
				</tr>
				<tr>
					<td colspan="3">库存数量：${product.productNum }</td>
				</tr>
				<tr>
					<td colspan="3">生产日期：${product.proDate }</td>
				</tr>
				<tr>
					<td colspan="3">进货日期：${product.purDate }</td>
				</tr>
				<tr>
					<td colspan="3">商品说明：${product.productDesc }</td>
				</tr>
			</table>
		</div>
	  </div>
	  
	  <div id='formDiv'>
	   <div class="sm">&nbsp;</div>
	   <form action="/Horizon/product/UpdateProductCtrl" method="post" id="form">
	   	<input type="hidden" name="productid" value="${product.productid }"/>
	   <img align="top" src="${product.image_w }" class="tp"/>
	    <div style="float:left;">
		    <ul class="ulStyle">
		    	<li class="liStyle" >商品编号：${product.productid }</li>
		    	<li class="liStyle" >商品名：　<input id="productName" type="text" name="productName" value="${product.productName }" style="width:500px;"/></li>
		    	<li class="liStyle" >促销价：<input id="currPrice" type="text" name="currPrice" value="${product.currPrice }" style="width:50px;"/></li>
		    	<li class="liStyle" >定价：　<input id="price" type="text" name="price" value="${product.price }" style="width:50px;"/>
		    </ul>
			<hr style="margin-left: 50px; height: 1px; color: #dcdcdc"/>
			<table class="tab">
				<tr>
					<td colspan="3">
						商品品牌：　　<input type="text" id="brand" name="brand" value="${product.brand }" style="width:300px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						库存数量：　<input type="text" name="productNum" id="productNum" value="${product.productNum }" style="width:60px;"/>
					</td>
				</tr>
				<tr>
					<td colspan="3">生产日期：<input type="text" id="proDate" name="proDate" value="${product.proDate }" style="width:100px;"/></td>
				</tr>
				<tr>
					<td colspan="3">进货日期：<input type="text" id="purDate" name="purDate" value="${product.purDate }" style="width:100px;"/></td>
				</tr>
				<tr>
					<td>
						一级分类：<select name="pid" id="pid" onchange="loadChildren()">
							<option value="">==请选择1级分类==</option>
	<c:forEach items="${parents }" var="parent">
	  <option value="${parent.cid }" <c:if test="${product.category.parent.cid eq parent.cid }">selected="selected"</c:if>>${parent.cname }</option>
	</c:forEach>
						</select>
					</td>
					<td>
						二级分类：<select name="cid" id="cid">
							<option value="">==请选择2级分类==</option>
	<c:forEach items="${children }" var="child">
	  <option value="${child.cid }" <c:if test="${product.category.cid eq child.cid }">selected="selected"</c:if>>${child.cname }</option>
	</c:forEach>
						</select>
					</td>
					<tr>
						<td colspan="4">商品说明：　　<textarea name="productDesc" id="productDesc" style="margin: 0px; width: 602px; height: 148px;">${product.productDesc }</textarea></td>
					</tr>
				<tr>
					<td colspan="2">
						<input onclick="editForm()" type="button" name="method" id="editBtn" class="btn" value="编　　辑">
						<input onclick="deleteForm()" type="button" name="method" id="delBtn" class="btn" value="删　　除">
					</td>
					<td></td>
				</tr>
			</table>
		</div>
	   </form>
	  </div>
    </div>
  </div>
  </body>
</html>
