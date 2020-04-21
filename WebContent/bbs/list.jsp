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
<style type="text/css">
	a, a:hover{
	color: #000000;
	text-decoration: none;
}
</style>
</head>
<body>
 <%@ include file="/header.jsp" %>
 <%
 	int pageNumber = 1;
 	if(request.getParameter("pageNumber") != null) {
 		pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
 	}
 %>
 <div class = "title"><h2 align="center">자유 게시판</h2></div>
	<div class="table">
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
			ArrayList<BbsVO> list = bbs.getList(pageNumber);
			for(int i=0; i<list.size(); i++) {
			int repCount = rep.getCount(list.get(i).getBbsIdx());
		%>
			<tr>
				<th scope="col"><%= list.get(i).getBbsIdx() %></th>
				<td><a href="bbsView.ok?bbsIdx=<%= list.get(i).getBbsIdx() %>" ><%= list.get(i).getBbsTitle().replaceAll(" ", "&nbsp;")
				.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("\n", "<br>") %>
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
		
		<!-- 페이징 구현 -->
		<%
		final int ROWSIZE = 10; // 한페이지에 보일 게시물 수 final int BLOCK = 5; 
        final int BLOCK = 5; // 아래에 보일 페이지 최대개수 1~5 / 6~10 / 11~15 식으로 5개로 고정
		final int bbsCount = bbs.getCount();
        
        int start = (pageNumber*ROWSIZE) - (ROWSIZE-1); // 해당페이지에서 시작번호(step2) 
        int end = (pageNumber*ROWSIZE); // 해당페이지에서 끝번호(step2) 
        
        int allPage = 0; // 전체 페이지수
        int block_startPage = ((pageNumber-1)/BLOCK*BLOCK)+1; // 시작블럭숫자 (1~5페이지일경우 1, 6~10일경우 6) 
        int block_endPage = ((pageNumber-1)/BLOCK*BLOCK)+BLOCK; // 끝 블럭 숫자 (1~5일 경우 5, 6~10일경우 10)
        
        int endPage = (int)Math.ceil((bbsCount-1)/10)+1; // 끝 페이지
		int prev_page = pageNumber -1; // 이전 페이지
		int next_page = pageNumber +1; // 다음 페이지
	
		%> 
		 
<nav class="page" aria-label="Page navigation example">
  <ul class="pagination">
     <li class="page-item">
     <%
     	if(prev_page <= 0) {
     %>
      <a class="page-link" href="list.jsp?pageNumber=1" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
      <%
     	} else {
      %>
      <a class="page-link" href="list.jsp?pageNumber=<%= prev_page%>" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
      <%
     	}
      %>
    </li>	
		<%	if(block_endPage > endPage) { // 엔드 페이지가 블록 페이지 보다 작을 경우
			for(int i=block_startPage; i<= endPage; i++){				
		%>			
				<li class="page-item">
				<a class="page-link" href="list.jsp?pageNumber=<%=i %>">
				<%=i %></a>
				</li>	
		<%
			}
		}else {
			for(int i=block_startPage; i<= block_endPage; i++){	// 엔드 페이지가 블록 페이지와 같을 경우
		%>
			<li class="page-item">
				<a class="page-link" href="list.jsp?pageNumber=<%=i %>">
				<%=i %></a>
				</li>	
		<%
			}
		}
		%>
		
		<%
			if(next_page > endPage) {
		%>
		  <li class="page-item">
      <a class="page-link" href="list.jsp?pageNumber=<%= endPage%>" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    	</li>
    	<%
			} else {
    	%>	
    	<li class="page-item">
      <a class="page-link" href="list.jsp?pageNumber=<%= next_page%>" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    	</li>
    	<%
			}
    	%>
	</ul>
</nav>		
     <button type="button" class="btn btn-primary" id="write" onclick="location.href='/BBS_JSP/bbs/write.jsp'">글쓰기</button>
</body>
</html>