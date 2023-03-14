<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Load</title>
</head>
<body>
	<form action="Load.action" method="post">
		<p>
			用户名: <input type="text" name="userName"><br>
		<p>
			密码：<input type="text" name="password"><br>
		<p>
			<input type="submit" value="提交">
</body>
</html>