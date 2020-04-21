<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
        <%@ page import="com.rep.dao.RepDAO"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 처리</title>
</head>
<body>
<%
			RepDAO repDAO = RepDAO.getInstance();
			repDAO.initIdx();
			int bbsIdx = Integer.parseInt(request.getParameter("bbsIdx"));
			int result = (int) request.getAttribute("rs");
			if (result == -1) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('댓글 쓰기에 실패했습니다.')");
				script.println("history.back()");
				script.println("</script>");
			} else {
				response.sendRedirect("bbsView.ok?bbsIdx=" + bbsIdx);	
			}
	%>
</body>
</html>