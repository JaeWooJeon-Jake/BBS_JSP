package com.member.controller;

import java.io.*;
import java.sql.Timestamp;

import javax.servlet.*;
import javax.servlet.http.*;

import com.member.controller.HttpUtil;
import com.member.service.MemberService;
import com.member.vo.MemberVO;

public class MemberJoinController implements Controller {
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("userID");
		String pw = request.getParameter("userPassword");
		String pw2 = request.getParameter("userPassword2");
		String name = request.getParameter("userName");
		String email = request.getParameter("userEmail");
		String gender = request.getParameter("userGender");
		Timestamp date = new Timestamp(System.currentTimeMillis());

		if(!pw.equals(pw2)) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('��й�ȣ�� ��ġ���� �ʽ��ϴ�.')");
			script.println("history.back()");
			script.println("</script>");
		} else {
		
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
		int rs = service.memberJoin(member);
		
		// memberLogin_action.jsp �������� �̵�
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/member/memberJoin_action.jsp");
		}
	}
}
