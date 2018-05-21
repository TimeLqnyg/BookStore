<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册表单</title>
  </head>
  
  <body style="text-align:center;font-size:20px">
  <form action="${pageContext.request.contextPath }/client/RegisterServlet" method="post">
   用户名：<input type="text" name="username"/><br/>
   密	  码：<input type="password" name="password"/><br/>
   姓	  名：<input type="password" name="name"/><br/>
   电     话：<input type="text" name="phone"/><br/>
   手     机：<input type="text" name="cellphone"/><br/>
   邮     箱：<input type="text" name="email"/><br/>
   住     址：<input type="text" name="address"/><br/>
   城     市：<input type="text" name="city"/><br/>
   邮     编：<input type="text" name="post"/><br/>
   <br/>
   <input type="submit" value="注册" />
  </form>
  </body>
</html>
