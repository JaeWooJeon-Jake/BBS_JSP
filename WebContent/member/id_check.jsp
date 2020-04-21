<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<%@ page import="com.member.dao.MemberDAO"%>
<%@ page import="com.member.service.MemberService" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="/BBS_JSP/js/script.js"></script>
<title>아이디 중복 확인</title>
</head>
<body>
	<%
	String id = request.getParameter("userid");
	MemberService service = MemberService.getInstance();
	int result = service.memberIDcheck(id);
	if (result == 1) {
	%>
	<div style='font-family: "malgun gothic"; color : red;'><h3><%=id%></h3>은 중복된 아이디입니다.<div>
	<%
	} else {
	%>
	<div style='font-family: "malgun gothic"';><h3><%=id%></h3>는 사용 가능한 아이디입니다.</div>
	<%
	}
	%>
	<button value="닫기" onclick="window.close()">닫기</button>
</body>
</html>