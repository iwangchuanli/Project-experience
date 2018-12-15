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
		<p>
		网站：Quark网络交流平台
		<br>
		&nbsp;&nbsp;Quark(夸克)是一种参与强相互作用的基本粒子，也是构成物质的基本单元。
		<br>通过大家的加入交流，共同分享知识，共建网络平台。
		<br>
		开发：王传礼&nbsp;21506031058
		</p>
		<img src="image/smile.jpg" width=300 height=200></img>
	</center>
</body>
</html>