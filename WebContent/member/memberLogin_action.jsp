<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.member.dao.MemberDAO" %>
<%@ page import="com.member.vo.MemberVO" %>
<%@ page import="java.io.PrintWriter" %>
<% request.setCharacterEncoding("UTF-8"); %>


<jsp:useBean id="member" class="com.member.vo.MemberVO" scope="page" />
<jsp:setProperty name="member" property="userID" />
<jsp:setProperty name="member" property="userPassword" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인 처리</title>
</head>
<body>
<%
	String userID = null;
	if(session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	if(userID != null) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('이미 로그인 되어있습니다.')");
		script.println("location.href='/BBS_JSP/index.jsp'");
		script.println("</script>");
	}
	MemberVO user = (MemberVO)request.getAttribute("user"); // service.memberInfo 값 받아옴
	int result = (int)request.getAttribute("rs"); // service.memberLogin 값 받아옴
	if(result == 1) {
		session.setAttribute("userID", user.getUserID());
		session.setAttribute("userName", user.getUserName());
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href = '/BBS_JSP/index.jsp'");
		script.println("</script>");
	} else if(result == 0) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('비밀번호가 틀립니다.')");
		script.println("history.back()");
		script.println("</script>");
	} else if(result == -1) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('존재하지 않는 아이디입니다.')");
		script.println("history.back()");
		script.println("</script>");
	} else if(result == -2) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('데이터베이스 오류가 발생했습니다.')");
		script.println("history.back()");
		script.println("</script>");
	}
	%> 
</body>
</html>