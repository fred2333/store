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
	<script type="text/javascript" src="/Horizon/jQuery/jquery1.42.min.js"></script>
	<script type="text/javascript">
		function submitInfo() {
			var allow = 1;
			$("input[type='text']").each(function () {
                if ($(this).val() == "") {
                    $("#msg").html("<font color='red'>请将申请信息填写完整。<font>");
                    allow = 0;
                }
            });
            if(allow == 1) {
            	$("form").submit();
            }
		}
	</script>
  </head>
  
  <body>
  <h2 align="center"><font color="#FF6600"><b>开店申请信息</b></font></h2>
  <form action="/Horizon/shop/SetUpShopCtrl" method="post">
  <table width="492" height="331" border="0" align="center">
    <tr>
      <th height="30" colspan="2" scope="col"><span id="msg"></span></th>
    </tr>
    <tr>
      <td width="146" align="right">真实姓名：</td>
      <td width="330"><input name="name" type="text" size="50"></td>
    </tr>
    <tr>
      <td align="right">身份证号码：</td>
      <td><input name="idcardNum" type="text" size="50"></td>
    </tr>
    <tr>
      <td align="right">常用地址：</td>
      <td><input name="address" type="text" size="50"></td>
    </tr>
    <tr>
      <td align="right">联系电话：</td>
      <td><input name="phone" type="text" size="50"></td>
    </tr>
    <tr>
      <td align="right">开户银行：</td>
      <td><input name="bank" type="text" size="50"></td>
    </tr>
    <tr>
      <td align="right">银行卡号：</td>
      <td><input name="bankcardNum" type="text" size="50"></td>
    </tr>
    <tr>
      <td colspan="2" align="center"><input type="button" name="Submit" value="提交" onClick="submitInfo()">
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="reset" type="reset" id="reset" value="重置"></td>
    </tr>
  </table>
  </form>
  </body>
</html>
