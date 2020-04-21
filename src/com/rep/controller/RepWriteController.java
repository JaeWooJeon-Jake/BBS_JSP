package com.rep.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rep.controller.Controller;
import com.rep.vo.RepVO;
import com.rep.controller.HttpUtil;
import com.rep.service.RepService;


public class RepWriteController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int repIdx = 0;
		int bbsIdx = Integer.parseInt(request.getParameter("bbsIdx"));
		String id = request.getParameter("userID");
		String name = request.getParameter("userName");
		String repContent = request.getParameter("repContent");
		Timestamp repDate = new Timestamp(System.currentTimeMillis());
		String tmp = "null";
		
		if( id.equals(tmp) ) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('로그인을 하세요.')");
			script.println("location.href='../member/logIn.jsp'");
			script.println("</script>");
		} else {

		// VO 객체에 데이터 바인딩
		RepVO rep = new RepVO();
		rep.setRepIdx(repIdx);
		rep.setBbsIdx(bbsIdx);
		rep.setUserID(id);
		rep.setUserName(name);
		rep.setRepContent(repContent);
		rep.setRepDate(repDate);
		
		// Service 객체의 메소드 호출
		RepService service = RepService.getInstance();
		int rs = service.repWrite(rep);
		
		// memberLogin_action.jsp 페이지로 이동
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/bbs/repWriteAction.jsp");
		}
	}
}
