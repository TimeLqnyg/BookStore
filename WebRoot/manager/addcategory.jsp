<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加分类</title>
    
  </head>
  	<form action="${pageContext.request.contextPath }/manager/CategoryServlet?method=add" method="post">
  		分类名称:<input type="text" name="name"/> <br/> <br/>
   		分类描述:<textarea rows="5" cols="40" name="description"></textarea><br/> <br/>
  		<input  type="submit" value="添加"/>
  	</form>
  <body>
		
  </body>
</html>
