<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
    <%@ page import="com.wcl.OnlineFriend.model.member.ModifyMessage"%>
<jsp:useBean id="modifyMess" type="com.wcl.OnlineFriend.model.member.ModifyMessage" scope="request"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="<%=basePath%>">
<%@ include file="head_member.txt"%>
</head>
<body background="image/member.jpg">	
	<center>
		<font size=4 color=blue>
			<br><jsp:getProperty name="modifyMess" property="backNews"/>
		</font>
		<% if(modifyMess.isModifyRegisterMessageOk()){ %>
			<font size=3>
				<table>
					<tr><td>新的email：</td><td><jsp:getProperty name="modifyMess" property="newEmail"/></td></tr>
					<tr><td>新的电话：</td><td><jsp:getProperty name="modifyMess" property="newPhone"/></td></tr>
				</table>	
				<table>
					<tr><td>新的个人简介：</td></tr>
					<tr>
						<td><TextArea name="Message" rows="6" cols="30"><jsp:getProperty name="modifyMess" property="newMessage"/></TextArea></td>
					</tr>
				</table>
			</font>
		<%}
		else{%>
		<br><a href="member/modifyRegisterMess.jsp">点击这里，重新修改</a>
		<%} %>
	</center>
	

</body>
</html>