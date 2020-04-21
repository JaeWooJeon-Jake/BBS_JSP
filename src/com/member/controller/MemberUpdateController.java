package com.member.controller;

import java.io.*;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.controller.HttpUtil;
import com.member.service.MemberService;
import com.member.vo.MemberVO;

public class MemberUpdateController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("userID");
		String pw = request.getParameter("userPassword");
		String name = request.getParameter("userName");
		String email = request.getParameter("userEmail");
		String gender = request.getParameter("userGender");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		// VO ��ü�� ������ ���ε�
		MemberVO member = new MemberVO();
		member.setUserID(id);
		member.setUserPassword(pw);
		member.setUserName(name);
		member.setUserGender(gender);
		member.setUserEmail(email);
		member.setUserDate(date);
		
		// Service ��ü�� �޼ҵ� ȣ��
		MemberService service = MemberService.getInstance();
		int rs = service.memberUpdate(member);
		
		// memberLogin_action.jsp �������� �̵�
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/member/memberUpdate_action.jsp");
	}
}
