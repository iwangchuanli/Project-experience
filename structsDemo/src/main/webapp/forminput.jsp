<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Hello Struts2</title>
</head>
<body>
	<form action="user.action" method="post">
		<p>
			用户名: <input type="text" name="userName"><br>
		<p>
			地址：<input type="text" name="address"><br>
		<p>
			电话：<input type="text" name="tel"><br>
		<p>
			邮箱：<input type="text" name="email"><br>
		<p>
			<input type="submit" value="提交">
</body>
</html>