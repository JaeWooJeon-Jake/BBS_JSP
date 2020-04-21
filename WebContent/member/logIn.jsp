<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="../css/loginform.css">
<title>Login</title>
</head>
<body>
	<%@ include file="/header.jsp" %>

	<div class="form">
  <div class="form-toggle"></div>
  <div class="form-panel one">
    <div class="form-header">
      <h1>로그인</h1>
    </div>
    <div class="form-content">
      <form method = "POST" action = "memberLogin.do">
        <div class="form-group">
          <label for="username">아이디</label>
          <input type="text" id="username" name="userID" required="required"/>
        </div>
        <div class="form-group">
          <label for="password">비밀번호</label>
          <input type="password" id="password" name="userPassword" required="required"/>
        </div>
        <div class="form-group">
          <label class="form-remember">
            <input type="checkbox"/>ID 기억하기
          </label><a href="memberFind.jsp" class="form-recovery">아이디/비밀번호 찾기</a>
        </div>
        <div class="form-group">
          <button type="submit">로그인</button>
        </div>
		<p align="center"><a href="join.jsp" class="form-recovery">회원가입</a><p>
      </form>
    </div>
  </div>
</body>
</html>