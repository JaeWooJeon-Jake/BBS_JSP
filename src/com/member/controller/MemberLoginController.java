package com.member.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.member.controller.HttpUtil;
import com.member.service.MemberService;
import com.member.vo.MemberVO;

public class MemberLoginController implements Controller{
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 파라미터 추출
		String id = request.getParameter("userID");
		String pw = request.getParameter("userPassword");
		
		// Service 객체의 메소드 호출
		MemberService service = MemberService.getInstance();
		int rs = service.memberLogin(id,pw);
		
		MemberVO user = service.memberInfo(id);
		
		// memberLogin_action.jsp 페이지로 이동
		request.setAttribute("user", user);
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/member/memberLogin_action.jsp");
		
	}
}
