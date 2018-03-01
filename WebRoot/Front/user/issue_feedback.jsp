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
			if ($("#email").val() == "" || $("#i_title").val() == "" || $("textarea").val() == "") {
                $("#msg").html("<font color='red'>E-Mail,问题标题和内容均不能为空。<font>");
            } else {
            	$("form").submit();
            }
		}
	</script>
  </head>
  
  <body>
  <h2 align="center"><font color="#FF6600"><b>问题反馈</b></font></h2>
  <form action="/Horizon/IssueFeedbackCtrl" method="post">
  <table width="545" border="0" align="center">
    <tr>
      <th height="43" colspan="2" align="left" scope="col"><span id="msg">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请输入问题反馈的相应信息，然后单击提交</span></th>
    </tr>
    <tr>
      <td height="36" align="right">&nbsp;&nbsp;&nbsp;接收管理员回复的E-Mail：&nbsp;&nbsp;&nbsp;</td>
      <td height="36" align="left"><input id="email" name="email" type="text" size="50" style="font-size:15px"></td>
    </tr>
    <tr>
      <td width="206" height="36" align="right">问题标题：&nbsp;&nbsp;&nbsp;</td>
      <td width="329"><input id="i_title" name="i_title" type="text" size="50" style="font-size:15px"></td>
    </tr>
    <tr>
      <td height="29" colspan="2" align="left" valign="top">&nbsp;&nbsp;&nbsp;问题内容</td>
    </tr>
    <tr>
      <td height="85" colspan="2" align="center" valign="top"><textarea style="font-size:15px" name="i_text" cols="80" rows="8"></textarea></td>
    </tr>
    <tr>
      <td height="50" colspan="2" align="center"><input type="button" name="Submit" value="提交" onClick="submitInfo()">
	  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <input name="reset" type="reset" id="reset" value="重置"></td>
    </tr>
  </table>
  </form>
  </body>
</html>
