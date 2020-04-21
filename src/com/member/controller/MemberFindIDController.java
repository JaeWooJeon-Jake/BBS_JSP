package com.member.controller;

import java.io.*;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.controller.HttpUtil;
import com.member.service.MemberService;
import com.member.vo.MemberVO;

public class MemberFindIDController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String name = request.getParameter("userName");
		String email = request.getParameter("userEmail");
		
		// Service ��ü�� �޼ҵ� ȣ��
		MemberService service = MemberService.getInstance();
		String rs = service.memberFindID(name, email);
		
		// memberLogin_action.jsp �������� �̵�
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/member/memberFindID_action.jsp");
	}
}
