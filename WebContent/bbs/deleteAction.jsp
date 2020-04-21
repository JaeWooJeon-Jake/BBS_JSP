<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bbs.dao.BbsDAO"%>
<%@ page import="com.bbs.vo.BbsVO"%>
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
			String userID = null;
			if (session.getAttribute("userID") != null) {
				userID = (String) session.getAttribute("userID");
			}
			if(userID == null) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('로그인을 하세요.')");
				script.println("location.href='login.jsp'");
				script.println("</script>");
			}
			
			int bbsIdx = 0;
			if(request.getParameter("bbsIdx") != null) {
				bbsIdx = Integer.parseInt(request.getParameter("bbsIdx"));
			}
			if(bbsIdx == 0) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('유효하지 않은 글입니다.')");
				script.println("loaction.href='/BBS_JSP/indxe.jsp'");
				script.println("</script>");
			}
			
			BbsDAO bbsDAO = BbsDAO.getInstance();
			BbsVO bbs = bbsDAO.getBbs(bbsIdx);
			
			if(!userID.equals(bbs.getUserID())) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('권한이 없습니다.')");
				script.println("loaction.href='/BBS_JSP/index.jsp'");
				script.println("</script>");
			} else {
				int result = bbsDAO.delete(bbsIdx);
				bbsDAO.initIdx();
				if (result == -1) {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("alert('글삭제에 실패했습니다.')");
					script.println("history.back()");
					script.println("</script>");
				} else {
					PrintWriter script = response.getWriter();
					script.println("<script>");
					script.println("location.href='/BBS_JSP/bbs/list.jsp'");
					script.println("</script>");
				}
			}
%>
</body>
</html>