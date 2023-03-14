<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="com.pojo.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
	.td td{
		width: 100px;
	}
	.table{
		text-align: center;
		margin: 0 auto;
	}
</style>
</head>
<body>
<%
	User user = ((User)request.getAttribute("var"));
%>
 
	<table class="table">
		<tr class="td">
			<td>ID</td>
			<td>用户名</td>
			<td>密码</td>
			<td style="width: 200px">公司</td>
			<td>年龄</td>
			<td>性别</td>
		</tr>
<%if(user!=null){%>
		<tr class="td">
			<td><%=user.getId()%></td>
			<td><%=user.getUsername()%></td>
			<td><%=user.getPassword()%></td>
			<td><%=user.getCompany()%></td>
			<td><%=user.getAge()%></td>
			<td><%=user.getSex()==1?"男":"女"%></td>
		</tr>
	<%}else{ %>
		<tr class="td">
			<td style="color: red;">暂无相关数据</td>
		</tr>
<%} %>
	</table>
 
</body>
</html>
