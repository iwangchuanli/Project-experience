<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>UserList</title>
</head>
<body>
	<table border="1">
		<tr>
			<td>id</td>
			<td>UserName</td>
			<td>password</td>
		</tr>
		<s:iterator value="#userList" status="bcs">
			<tr>
				<td><s:property value="id"></s:property></td>
				<td><s:property value="name"></s:property></td>
				<td><s:property value="pwd"></s:property></td>
				<td><s:property value="sex"></s:property></td>
				<td><s:property value="age"></s:property></td>
				<td><s:property value="hobbies"></s:property></td>
			</tr>
		</s:iterator>
		<s:if test="userList.size()==0">
			<tr>
				<td colspan="7">没有查找到数据</td>
			</tr>
		</s:if>
	</table>
</body>
</html>