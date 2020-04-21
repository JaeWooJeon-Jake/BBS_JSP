<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.bbs.vo.BbsVO" %>
    <%@ page import="com.bbs.dao.BbsDAO" %>
    <%@ page import="com.member.dao.MemberDAO" %>
    <%@ page import="java.io.PrintWriter" %>
    <%@ page import="java.io.File" %>
    <%@ page import="com.rep.vo.RepVO" %>
    <%@ page import="com.rep.dao.RepDAO" %>
        <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글 수정</title>
<link rel="stylesheet" type="text/css" href="/BBS_JSP/css/view.css">
</head>
<body>
	 <%@ include file="/header.jsp" %>
	
	<%
		int bbsIdx = 0;
		if(request.getParameter("bbsIdx") != null) {
			bbsIdx = Integer.parseInt(request.getParameter("bbsIdx"));
		}
		if(bbsIdx == 0) {
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('유효하지 않은 글입니다.')");
			script.println("loaction.href='/BBS_JSP/index.jsp'");
			script.println("</script>");
		}
		BbsDAO bbsDAO = BbsDAO.getInstance();
		bbsDAO.updateHit(bbsIdx);
		BbsVO bbs = (BbsVO) bbsDAO.getBbs(bbsIdx);
	%>
	
	<table class="view_table" align=center>
        <tr>
                <td colspan="4" class="view_title"><%= bbs.getBbsTitle().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;")
                .replaceAll(">", "&gt;").replaceAll("\n", "<br>") %></td>
        </tr>
        <tr>
				 <td class="view_id">작성자</td>
                <td class="view_id2"><%= bbs.getUserName() %></td>
				   <td class="view_hit">조회수</td>
                <td class="view_hit2"><%= bbs.getBbsHit() +1 %></td>
        </tr>
		<tr>
                <td colspan="4" class="view_content" valign="top">
                <%= bbs.getBbsContent().replaceAll(" ", "&nbsp;").replaceAll("<", "&lt;").replaceAll(">", "&gt;")
                .replaceAll("\n", "<br>") %>
               </td>
        </tr>
        </table>

		<div class="view_btn">
                <button class="view_btn1" onclick="location.href='list.jsp'">목록으로</button>
                <%
                	if(userID != null && userID.equals(bbs.getUserID())) {
                %>
                <button class="view_btn1" onclick="location.href='update.jsp?bbsIdx=<%= bbsIdx %>'">수정</button>
				<button class="view_btn1" onclick="location.href='deleteAction.jsp?bbsIdx=<%= bbsIdx %>'">삭제</button>
				
        		<%
                	}
        		%>
        </div>
	
		<div class="file"> 	
		파일 :
		<%
		if(bbs.getBbsFile() == null) {
		%>
			<p>파일없음</p>
		<% 
		} else {
		out.write("<a href=\"" + request.getContextPath() + "/downloadAction?file=" + 
				java.net.URLEncoder.encode(bbs.getBbsFile(), "UTF-8") + "\">" + bbs.getBbsFile() + "</a><br>");
		%>
		<%
		}
		%>
		</div>

<!--- 댓글 불러오기 -->
<div class="reply_view">
	<h3>댓글목록</h3>
	<%
	RepDAO repDAO = RepDAO.getInstance();
	int repCount = repDAO.getCount(bbsIdx);
	ArrayList<RepVO> list = repDAO.getList(bbsIdx);
	for(int i=0; i<list.size(); i++) {
	%>
	<div class="dap_lo">
		<div><input type="hidden" name="repIdx" value="<%= list.get(i).getRepIdx() %>" /></div>
		<div><input type="hidden" name="bbsIdx" value="<%= list.get(i).getBbsIdx() %>" /></div>
			<div><b><%= list.get(i).getUserName()%></b></div>
			<div class="dap_to comt_edit"><%= list.get(i).getRepContent() %></div>
			<div class="rep_me dap_to"><%= list.get(i).getRepDate()%></div>
			<div class="rep_me rep_menu">
				<a class="dat_edit_bt" href="repUpdate.re?repIdx=<%= list.get(i).getRepIdx() %>">수정</a>
				<a class="dat_delete_bt" href='repDelete.re?repIdx=<%= list.get(i).getRepIdx() %>&&bbsIdx=<%= list.get(i).getBbsIdx() %>
				&&userID=<%= userID %>' >삭제</a>
			</div>
		</div>
		<%
			}
		%>
</div><!--- 댓글 불러오기 끝 -->

<!--- 댓글 입력 폼 -->

<%
	int repIdx = Integer.parseInt(request.getParameter("repIdx"));
	RepVO rep = repDAO.getRep(repIdx);
	if(!userID.equals(rep.getUserID())) {
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('권한이 없습니다.')");
		script.println("history.back()");
		script.println("</script>");
	}
%>
	<div class="co_form">
	<form method="post" class="reply_form" action="repUpdate.re">
			<input type="hidden" name="repIdx" class="bno" value="<%= repIdx %>">
			<input type="hidden" name="bbsIdx" class="bno" value="<%= bbsIdx %>">
			<input type="hidden" name="userID" id="dat_user" class="dat_user" value="<%= session.getAttribute("userID") %>">
			<input type="hidden" name="userName" id="dat_user" class="dat_user" value="<%= session.getAttribute("userName")  %>">
			<div>
				<textarea name="repContent" class="reply_content" id="re_content" autofocus><%= rep.getRepContent() %></textarea>
				<button type="submit" id="rep_bt" class="re_bt">댓글 수정</button>
			</div>
			</form>
	</div>

</body>
</html>