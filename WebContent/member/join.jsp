<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="com.member.dao.MemberDAO" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="/BBS_JSP/css/loginform.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<script src="/BBS_JSP/js/script.js"></script>
<title>회원가입</title>
</head>
<body>
<%@ include file="/header.jsp" %>

<div class="register">
    <div class="form-header">
      <h1>회원 가입</h1>
    </div>
    <div class="form-content">
      <form method='post' action='memberJoin.do'>
        <div class="userid">
          <label for="username">아이디</label>
          <input type="text" id="userid" name="userID" required="required" class="check" onkeydown="inputIdChk()"/>
		  </div>
		  <div class="id_ck">
		  <input type="button" value="중복검사" onclick="checkid();"/>
		  <input type="hidden" value="0" name="chs" />
		  </div>
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input type="password" id="password" name="userPassword" required="required"/>
        </div>
        <div class="form-group">
          <label for="cpassword">비밀번호 확인</label>
          <input type="password" id="cpassword2" name="userPassword2" required="required"/>
        </div>
        <div class="form-group" style="text-align:center">
        <div class="btn-group" data-toggle="buttons">
          <label for="gender">성별</label>
          <label class="btn btn-primary active">
          	 <input type="radio" name="userGender" autocomplete="off" value="남자" checked>남자
          </label>
         <label class="btn btn-primary">
          <input type="radio" name="userGender" autocomplete="off" value="여자" >여자
        	</label>
        	</div>
        </div>
		<div class="form-group">
          <label for="name">이름</label>
          <input type="text" id="username" name="userName" required="required"/>
        </div>
        <div class="form-group">
          <label for="email">이메일 주소</label>
          <input type="email" id="email" name="userEmail" required="required"/>
        </div>
        <div class="form-group">
          <button type="submit">회원가입</button>
        </div>
      </form>
    </div>
  </div>
</div>
</body>
</html>