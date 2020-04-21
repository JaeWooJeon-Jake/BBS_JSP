<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bbs.vo.BbsVO" %>
    <%@ page import="com.bbs.dao.BbsDAO" %>
     <%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 업데이트</title>
<link rel="stylesheet" type="text/css" href="/BBS_JSP/css/writeform.css" />
</head>
<body>
 	<%@ include file="/header.jsp" %>
	<%
		if(userID == null) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href='../member/logIn.jsp'");
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
			script.println("loaction.href='/BBS_JSP/indxe.jsp'");
			script.println("</script>");
		}
	%>
	
	<div id="board_write">
        <h1><a href="/">자유게시판</a></h1>
        <h4>게시글 수정 양식</h4>
            <div id="write_area">
                <form action="bbsUpdate.ok?bbsIdx=<%= bbsIdx %>" method="post">
                    <div id="in_title">
                        <textarea name="bbsTitle" id="utitle" rows="1" cols="55" placeholder="제목" maxlength="100" required><%= bbs.getBbsTitle() %></textarea>
                    </div>
         <div class="wi_line"></div>
                    <div id="in_name">
                        <textarea name="userID" id="uname" rows="1" cols="55" placeholder="글쓴이" maxlength="100" readonly required><%= userID %></textarea>
                    </div>
                    <div class="wi_line"></div>
                    <div id="in_content">
                        <textarea name="bbsContent" id="content" placeholder="내용" required><%= bbs.getBbsContent() %></textarea>
                    </div>
                    <div class="bt_se">
                        <button type="submit">글 수정</button>
                    </div>
                </form>
            </div>
        </div>
</body>
</html>