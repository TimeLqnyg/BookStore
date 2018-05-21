<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改分类</title>
    

  </head>
  
  <body style="text-align:center;">
  	<h3>修改${category.name}分类</h3>
  	<form action="${pageContext.request.contextPath }/manager/UpdateCategoryServlet?method=updateCategory" method="post">
	  	<table border="1" width="50%" align="center">
	  		<tr>
	  			<td>分类编号</td>
	  			<td>分类名称</td>
	  			<td>分类描述</td>
	  		</tr>
	  		<tr>
	  			<td>
	  				<input type="text" name="category_id" value="${category.id }" readonly="readonly" />
	  			</td>
	  			<td>
	  				<input type="text" name="category_name" value="${category.name }" />
	  			</td>
	  			<td>
	  				<textarea rows="5" cols="40" name="category_description" >${category.description }</textarea>
	  			</td>
	  		</tr>
	  		<tr>
	  			<td colspan="3">
	  				<input type="submit" value="修改"/>
	  			</td>
	  		</tr>
	  	</table>
  	</form>
  </body>
</html>
