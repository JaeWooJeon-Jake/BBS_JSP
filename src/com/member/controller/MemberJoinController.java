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
			script.println("alert('비밀번호가 일치하지 않습니다.')");
			script.println("history.back()");
			script.println("</script>");
		} else {
		
		// VO 객체에 데이터 바인딩
		MemberVO member = new MemberVO();
		member.setUserID(id);
		member.setUserPassword(pw);
		member.setUserName(name);
		member.setUserGender(gender);
		member.setUserEmail(email);
		member.setUserDate(date);
		
		// Service 객체의 메소드 호출
		MemberService service = MemberService.getInstance();
		int rs = service.memberJoin(member);
		
		// memberLogin_action.jsp 페이지로 이동
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/member/memberJoin_action.jsp");
		}
	}
}
