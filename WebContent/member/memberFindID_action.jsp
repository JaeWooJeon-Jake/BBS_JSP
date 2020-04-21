<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.member.dao.MemberDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>아이디 찾기</title>
<style type="text/css">
h3 {
	position:relative;
	left: 40%;
}

a {
	position:relative;
	left: 50%;
}
</style>
</head>
<body>
	<%@ include file="/header.jsp" %><br><br>

	<%
	String result = (String)request.getAttribute("rs");
	if (result == null) {
	%>
	<h3>회원님의 정보를 찾을수 없습니다.</h3>
	<%
	} else {
	%>
	<h3>회원님의 아이디는 <%= result %> 입니다.</h3>
	<%
	}
	%>
	
	<a href="/BBS_JSP/index.jsp">홈으로</a>
</body>
</html>