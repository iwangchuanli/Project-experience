<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="head.txt"%>
</head>
<body background="image/welcome.jpg">
	<center>
		<h1><font size=5 color=red>欢迎您的到来！</font></h1>
		<img src="image/smile.jpg" width=300 height=200></img>
	</center>
</body>
</html>