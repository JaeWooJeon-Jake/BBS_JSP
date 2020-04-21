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
<title>댓글 삭제</title>
</head>
<body>
	<%			
			RepDAO repDAO = RepDAO.getInstance();
			RepVO rep = (RepVO) request.getAttribute("rep");

				int result = (int) request.getAttribute("rs");
				repDAO.initIdx();
				
				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글삭제에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글삭제 성공했습니다.')");
					response.sendRedirect("bbsView.ok?bbsIdx=" + rep.getBbsIdx());	
					script.println("</script>");
				}
%>
</body>
</html>