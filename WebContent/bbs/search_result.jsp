<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bbs.dao.BbsDAO" %>
    <%@ page import="com.bbs.vo.BbsVO" %>
    <%@ page import="com.rep.dao.RepDAO" %>
    <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;" charset=UTF-8">
<title>게시판 메인 페이지</title>
</head>
<body>
 <%@ include file="/header.jsp" %>
 
 <%
 	String catgo = request.getParameter("catgo");
	String search = request.getParameter("search");
 %>
 <div class="title"><h1 align="center"><%= catgo %>에서 '<%= search %>'검색결과</h1></div>

	<div class="table" id="board_area">
	<table class="table">
	  <thead>
		<tr>
		  <th scope="col">번호</th>
		  <th scope="col">제목</th>
		  <th scope="col">작성자</th>
		  <th scope="col">날짜</th>
		  <th scope="col">조회수</th>
		</tr>
	  </thead>
		<tbody>
		<%
			RepDAO rep = RepDAO.getInstance();
			BbsDAO bbs = BbsDAO.getInstance();
		
			ArrayList<BbsVO> list = bbs.getSearchList(catgo, search);
			for(int i=0; i<list.size(); i++) {
			int repCount = rep.getCount(list.get(i).getBbsIdx()); // 댓글 갯수
		%>
			<tr>
				<th scope="col"><%= list.get(i).getBbsIdx() %></th>
				<td><a href="bbsView.ok?bbsIdx=<%= list.get(i).getBbsIdx() %>" ><%= list.get(i).getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %>
						<span class="re_ct">[<%= repCount %>]</span></a></td>
				<td><%= list.get(i).getUserName() %></td>
				<td><%= list.get(i).getBbsDate()%></td>		
				<td><%= list.get(i).getBbsHit() %></td>		
			</tr>
		<%
			}
		%>		
		</tbody>
        </table>
		</div>
	<h4 style="margin-top:30px; margin-left: 75%;"><a href="/BBS_JSP/index.jsp">홈으로</a></h4>	
</body>
</html>