<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>添加图书</title>
   
  </head>
  
  <body>
  							<!-- multipart/form-data	不对字符编码。 在使用包含文件上传控件的表单时，必须使用该值 -->
	<form action="${pageContext.request.contextPath }/manager/BookServlet?method=add" method="post" enctype="multipart/form-data">
		<table frame="border" width="50%"><!-- frame 属性规定外侧边框的哪个部分是可见的。 -->
			<tr>
				<td>图书名称</td>
				<td>
					<input type="text" name="name"/>
				</td>
			</tr>
			<tr>
				<td>作者</td>
				<td>
					<input type="text" name="author"/>
				</td>
			</tr>
			<tr>
				<td>售价</td>
				<td>
					<input type="text" name="price"/>
				</td>
			</tr>
			<tr>
				<td>图片</td>
				<td>
					<input type="file" name="file"/>
				</td>
			</tr>
			<tr>
				<td>图书描述</td>
				<td>
					<textarea rows="5" cols="40" name="description"></textarea>
				</td>
			</tr>
			<tr>
				<td>所属分类</td>
				<td>
					<select name="category_id">
						<c:forEach var="c" items="${categories }">
							<option value="${c.id }">${c.name }</option>   <!-- 而 value 属性中的值是表单被提交时被发送到服务器的值 -->
						</c:forEach>
					</select>
				</td>
			</tr>
			<tr>
    		<td>
    			<input type="reset" value="清空">
    		</td>
    		<td>
    			<input type="submit" value="提交">
    		</td>
    	</tr>
		</table>
	</form>

  </body>
</html>
