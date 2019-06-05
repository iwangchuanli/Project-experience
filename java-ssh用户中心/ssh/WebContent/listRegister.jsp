<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPEhtml PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户JSON注册</title>


</head>
<body>
	<div>
		UserList:<br>
		<c:forEach var="i" begin="1" end="5">
			<c:out value="${i}"></c:out>
		</c:forEach>
		<table border="1">
			<tr>
				<td>用户名</td>
				<td>密 码</td>
				<td>提交</td>
			</tr>
			<tbody id="input">
			
			</tbody>
			
		</table>
		
	</div>


	<script type="text/javascript">
		var text = '{ "users":['
				+ '{"userName":"test001","password":"test001"},'
				+ '{"userName":"test002","password":"test002"},'
				+ '{"userName":"test003","password":"test003"},'
				+ '{"userName":"test004","password":"test004"},'
				+ '{"userName":"test005","password":"test005"} ]}';
		var obj = JSON.parse(text);
		var str = "";
		for (i in obj.users) {
			str += "<tr><td><input type=\"text\" name=\"userName\" value=\"" ;
			str += obj.users[i].userName;
			str +="\" /></td><td><input type=\"text\" name=\"password\" value=\"";
			str += obj.users[i].password;
			str +="\" /></td><td><input type=\"submit\" value=\"提交\"></td></tr>";
		}
		document.getElementById("input").innerHTML = str;
	</script>
</body>
</html>