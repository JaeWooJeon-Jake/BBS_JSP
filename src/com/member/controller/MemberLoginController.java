package com.member.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import com.member.controller.HttpUtil;
import com.member.service.MemberService;
import com.member.vo.MemberVO;

public class MemberLoginController implements Controller{
public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// �Ķ���� ����
		String id = request.getParameter("userID");
		String pw = request.getParameter("userPassword");
		
		// Service ��ü�� �޼ҵ� ȣ��
		MemberService service = MemberService.getInstance();
		int rs = service.memberLogin(id,pw);
		
		MemberVO user = service.memberInfo(id);
		
		// memberLogin_action.jsp �������� �̵�
		request.setAttribute("user", user);
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/member/memberLogin_action.jsp");
		
	}
}
