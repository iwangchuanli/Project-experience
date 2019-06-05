<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JSTL Demo</title>
</head>
<body>
	<form action="jstlDemo.jsp" method="post">
		<input type="text" name="score" value="${param.score}" />
		<s:submit value="提交"></s:submit>
	</form>

	<hr>
	<!-- if控制标签 -->
	<c:if test="${param.score >= 90}" var="result">
		<c:out value="优秀！"></c:out>
	</c:if>
	<c:out value="${result}"></c:out>
	<hr>
	<!-- 基本核心标签 -->
	<c:set property="name" value="admin" var="name"></c:set>
	<c:out value="${name}"></c:out>
	<c:remove var="name" />
	<c:out value="${name}"></c:out>

	<hr>
	<!-- choose、when、otherwise -->
	<c:choose>
		<c:when test="${param.score<=100 && param.score>=60 }">
			<c:out value="及格"></c:out>
		</c:when>
		<c:when test="${param.score<60 && param.score>=0 }">
			<c:out value="不及格"></c:out>
		</c:when>
		<c:otherwise>
			<c:out value="请检查你的输入！"></c:out>
		</c:otherwise>
	</c:choose>
	<hr>
	<!-- ForEach() 部分遍历并带状态 -->
	<c:forEach var="fruit" items="${fruits}" begin="1" end="3"
		varStatus="fru">
		<c:out value="${fruit}——四个属性："></c:out>
		<br>
		<c:out value="index属性：${fru.index}"></c:out>
		<br>
		<c:out value="count属性：${fru.count}"></c:out>
		<br>
		<c:out value="first属性：${fru.first}"></c:out>
		<br>
		<c:out value="last属性：${fru.last}"></c:out>
		<br>
		<c:out value="----------"></c:out>
		<br>
	</c:forEach>
	<hr>
	<!-- import标签用法 -->
	<!-- 导入网络上的绝对路径 -->
	<c:catch var="error0">
		<c:import url="http://www.imooc.com"></c:import>
	</c:catch>
	<c:out value="${error0}"></c:out>
	<hr>
	<!-- 导入相对路径文件 -->
	<c:catch var="error1">
		<c:import url="Test.txt" charEncoding="gbk"></c:import>
	</c:catch>
	<c:out value="${error1}"></c:out>

<hr>
	<!-- var及scope用法 -->
	<c:catch var="error2">
		<c:import url="Test.txt" var="test" scope="session" charEncoding="gbk"></c:import>
	</c:catch>
	<c:out value="${error2}"></c:out>
	<br>
	<c:out value="import标签存储的test字符串变量值：${sessionScope.test}"></c:out>

	<!-- context属性用法 -->
	<c:catch var="error3">
		<c:import url="/index1.jsp" context="/Test1"></c:import>
	</c:catch>
	<c:out value="${error3}"></c:out>
	<br>


	<!-- url标签用法 -->
	<c:if test="${1<2}">
		<c:set var="partUrl">index.jsp</c:set>
	</c:if>
	<c:url value="http://localhost:8080/ssh/${partUrl}" var="newUrl"
		scope="session"></c:url>
	<a href="${newUrl}">New URL</a>



	<!-- JSTL函数使用 -->
	<c:out
		value="“Hello World”字符串中是否包含“Hello”？${fn:contains('Hello World','Hello')}"></c:out>
	<hr>
	<c:out
		value="“Hello World”字符串中是否包含“ABCD”？${fn:contains('Hello World','ABCD')}"></c:out>
	<hr>
	<c:out
		value="“Hello World”字符串中是否包含“hello”（忽略大小写）？${fn:containsIgnoreCase('Hello World','hello')}"></c:out>
	<hr>
	<c:out
		value="“Hello”是否在“Hello World”字符串开头？${fn:startsWith('Hello World','Hello')}"></c:out>
	<hr>
	<c:out
		value="“world”是否在“Hello World”字符串尾部？${fn:endsWith('Hello World','world')}"></c:out>


	<!-- 不会输出<book>标签 -->
	<book>书</book>
	<hr>
	<!-- 可输出标签但会出现转义问题 -->
	<c:out value="${fn:escapeXml('<book>书</book>')}"></c:out>
	<hr>
	<!-- 可输出标签且不会出现转义问题 -->
	<c:out value="<book>书</book>"></c:out>
	<hr>
	<!-- indexOf函数用法 -->
	<c:out
		value="“Hello World”字符串“llo”出现的index值：${fn:indexOf('Hello World','llo')}"></c:out>
</body>
</html>