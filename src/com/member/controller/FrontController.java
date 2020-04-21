package com.member.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String charset = null;
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		// TODO Auto-generated method stub
		charset = sc.getInitParameter("charset");
		list = new HashMap<String, Controller>();
		list.put("/member/memberLogin.do", new MemberLoginController());
		list.put("/member/memberJoin.do", new MemberJoinController());
		list.put("/member/memberUpdate.do", new MemberUpdateController());
		list.put("/member/memberFindID.do", new MemberFindIDController());
		list.put("/member/memberFindPW.do", new MemberFindPWController());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(charset); // 클라이언트로부터 POST방식으로 전달된 질의 문자열을 인코딩 처리하는 메소드
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length()); // memberInsert.do 처리
		Controller subController = list.get(path); // MemberInsertController 객체
		subController.execute(request, response);
	}
}
