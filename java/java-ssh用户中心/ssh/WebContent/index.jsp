<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Index Page</title>
</head>
<body>

	<table border="1" align="center">
		<tr>
			<td><a href="register.jsp">用户注册</a></td>
		</tr>
		<tr>
			<td><a href="load.jsp">用户登陆</a></td>
		</tr>
		<tr>
			<td><s:url action="List.action" var="List"></s:url> <a
				href='<s:property value="#List"/>'>用户列表</a></td>
		</tr>
		<tr>
			<td><a href="jstlDemo.jsp">JSTL Demo</a></td>
		</tr>
		<tr>
			<td><a href="listRegister.jsp">用户JSON注册</a></td>
		</tr>
	</table>
</body>
</html>