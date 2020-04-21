<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
      <%@ page import="com.member.dao.MemberDAO" %>
    <%@ page import="com.member.vo.MemberVO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 정보</title>
</head>
<body>
	 <%@ include file="/header.jsp" %>
	 
	  <div class = "title"><h2 align="center">회원 정보</h2></div>
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
			MemberDAO dao = MemberDAO.getInstance();
			MemberVO member = dao.memberInfo(userID);
			%>
			<tr>
				<th scope="col">아이디: <%= member.getUserID()  %></th>
				<td>비밀번호: <%= member.getUserPassword()  %></td>
				<td>이름: <%= member.getUserName()  %></td>
				<td>성별: <%= member.getUserGender() %></td>		
				<td>이메일: <%= member.getUserEmail() %></td>		
				<td>생성 날짜: <%= member.getUserDate()  %></td>		
			</tr>
		</tbody>
        </table>
        </div>
        <button type="button" class="btn btn-primary" id="memberInfo" onclick="location.href='/BBS_JSP/member/memberUpdate.jsp'" >회원 정보 수정하기</button>
        
        
</body>
</html>