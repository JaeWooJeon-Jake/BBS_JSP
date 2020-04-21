<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.rep.dao.RepDAO"%>
<%@ page import="com.rep.vo.RepVO"%>
<%@ page import="java.io.PrintWriter"%>
<%
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	int repIdx = (int) request.getAttribute("repIdx");
	RepDAO repDAO = RepDAO.getInstance();
	RepVO rep = repDAO.getRep(repIdx);
	
				int result = (int) request.getAttribute("rs");
				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글수정에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					response.sendRedirect("bbsView.ok?bbsIdx=" + rep.getBbsIdx());	
				}

	%>
</body>
</html>