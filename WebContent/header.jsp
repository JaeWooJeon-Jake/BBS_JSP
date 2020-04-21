<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width-device-width" initial-scale="1">
<link rel="stylesheet" type="text/css" href="/BBS_JSP/css/style.css">
<link rel="stylesheet" href="/BBS_JSP/css/mobile.css" media="screen and (min-width: 128px) and (max-width: 1024px)">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<title>Insert title here</title>
</head>
<body>
	<%
		String userID = null;
		String userName = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
		userName = (String) session.getAttribute("userName");
	}	
	%>

	<div class="logo">
		<a href="/BBS_JSP/index.jsp"><img src="/BBS_JSP/img/logo.png"
			width="100" height="50" alt="logo"></img></a>
	</div>
	<%
		if(userID == null) {
	%>
		<div id="user">
		<button type="button" class="btn btn-primary" onclick="location.href='/BBS_JSP/member/logIn.jsp'">로그인</button>
		<button type="button" class="btn btn-primary"onclick="location.href='/BBS_JSP/member/join.jsp'">
		회원가입</button></div>
	<%
		} else {
	%>
	<div id="user"><font size><%= userName %></font>님 안녕하세요
		<button type="button" class="btn btn-primary" id="login" onclick="location.href='/BBS_JSP/member/memberLogout.jsp'">로그아웃</button>
		<button type="button" class="btn btn-primary"onclick="location.href='/BBS_JSP/member/memberInfo.jsp'">
		마이페이지</button></div>
		</br>
	<%
		}
	%>
		
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="navbar-brand">JSP 게시판 구현</div>
	
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav mr-auto">
				<li class="nav-item active"><a class="nav-link"
					href="/BBS_JSP/index.jsp">메인 <span class="sr-only">(current)</span></a>
				</li>
				<li class="nav-item"><a class="nav-link"
					href="/BBS_JSP/bbs/list.jsp">게시판</a></li>
			</ul>
			<div class="search">
				<form class="form-inline" id="search"
					action="/BBS_JSP/bbs/search_result.jsp" method="get">
					<select name="catgo" class="form-control mr-sm-2">
						<option value="bbsTitle">제목</option>
						<option value="userName">작성자</option>
						<option value="bbsContent">내용</option>
					</select> <input class="form-control mr-sm-2" type="search"
						placeholder="Search" aria-label="Search" name="search">
					<button class="btn btn-outline-success my-2 my-sm-0" type="submit">검색</button>
				</form>
			</div>
	</nav>

</body>
</html>