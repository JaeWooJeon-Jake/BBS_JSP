<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC model2 게시판</title>
<link rel="stylesheet" type="text/css" href="/BBS_JSP/css/main.css">
</head>
<body>

	<%@ include file="header.jsp" %><br><br>
	
	<div class="left"><br>
	<h1>웹 사이트 소개</h1><br>
		<h3>JSP와 Servlet만을 이용하여 MVC Model2 방식으로 구현한 웹사이트 입니다.
		최대한 여러가지 기능들을 추가해 보았습니다.</h3><br>
		<p>-- 구현한 기능 --</p>
		<p>1. 회원가입 및 로그인/로그아웃</p>
		<p>2. 게시글 작성 및 삭제</p>
		<p>3. 리스트 및 페이지 구현</p>
		<p>4. 댓글 작성 및 수정/삭제</p>
		<p>5. 파일 업로드</p>
		<p>6. ID/PW 찾기</p>
		<p>7. 검색 구현</p>
		<a href="https://github.com/JaeWooJeon-Jake/BBS_JSP.git" style="position:relative; left:30px;">게시판 소스 파일 보기</a>
	</div>
	
	<div class="right-top"><br>
	<h1>MVC model2 구현 방식</h1>
	<img src="/BBS_JSP/img/MVC_model2_proc.png" alt="MVC_model2" style="width:800px; height:300px; position:relative; left:70px;" />
	</div>
	
	<div class="right-bottom"><br>
	<h1>사용 언어 : JSP, Servlet</h1><br>
	<div class="lang_img">
	<img src="/BBS_JSP/img/java.PNG" alt="JSP&Servlet" />
	<img src="/BBS_JSP/img/lang1.png" alt="html" />
	<img src="/BBS_JSP/img/lang3.PNG" alt="css" />
	<img src="/BBS_JSP/img/lang2.png" alt="js" />
	</div>
	</div>

</body>
</html>