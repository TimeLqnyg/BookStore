<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>前台首页</title>
    
  </head>
  

    <frameset rows="34%,*">
    	<frame src="${pageContext.request.contextPath }/client/head.jsp" name="head"/>
    	<frame src="${pageContext.request.contextPath }/client/IndexServlet?method=getAll" name="body"/>
    </frameset>

</html>
