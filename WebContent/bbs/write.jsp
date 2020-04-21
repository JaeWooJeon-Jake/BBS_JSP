<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.PrintWriter"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/BBS_JSP/css/writeform.css" />
<title>게시판 글쓰기 양식</title>
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
%>
<div id="board_write">
        <h1><a href="/">자유게시판</a></h1>
        <h4>글을 작성하는 공간입니다.</h4>
            <div id="write_area">
                <form action="bbsWrite.ok" method="post" enctype="multipart/form-data">
                    <div id="in_title">
                        <textarea name="bbsTitle" id="utitle" rows="1" cols="55" placeholder="제목" maxlength="100" required></textarea>
                    </div>
         <div class="wi_line"></div>
                    <div id="in_name">
                        <textarea name="userName" id="uname" rows="1" cols="55" placeholder="글쓴이" maxlength="100" readonly required><%= userName %></textarea>
                    </div>
                    <div id="in_name">
                        <input type="hidden" name="userID" value="<%= userID%>"/>
                    </div>
                    <div class="wi_line"></div>
                    <div id="in_content">
                        <textarea name="bbsContent" id="content" placeholder="내용" required></textarea>
                    </div>
                    <div id="in_file">
                        <input type="file" name="file" />
                    </div>
                    <div class="bt_se">
                        <button type="submit">글 작성</button>
                    </div>
                </form>
            </div>
        </div>
</body>
</html>