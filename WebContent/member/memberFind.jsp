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
	 
	 <br><br>
	 <div class = "title"><h2 align="center">아이디/비밀번호 찾기</h2></div>
	  <div class="find">
	<form method="post" action="memberFindID.do">
        <fieldset>
          <legend>아이디 찾기</legend>
            <table>
              <tr>
                <td>이름 </td>
                <td><input type="text" size="35" name="userName" placeholder="이름" required></td>
              </tr>
              <tr>
                <td>이메일 </td>
                <td><input type="text" size="35" name="userEmail" placeholder="이메일" required/>
              </tr>
            </table>
          <input type="submit" value="아이디 찾기" />
      </fieldset>
    </form>
  </div><br><br><br><br>
  <div class="find">
      <form method="post" action="memberFindPW.do">
        <fieldset>
          <legend>비밀번호 찾기</legend>
            <table>
              <tr>
                <td>아이디</td>
                <td><input type="text" size="35" name="userID" placeholder="아이디" required></td>
				</tr>
				<tr>
				<td>이름</td>
				<td><input type="text" size="35" name="userName" placeholder="이름" required></td>
				</tr>
            </table>
          <input type="submit" value="비밀번호 찾기" />
      </fieldset>
    </form>
  </div>

        
</body>
</html>