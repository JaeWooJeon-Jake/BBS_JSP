package com.rep.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rep.service.RepService;
import com.rep.vo.RepVO;


public class RepUpdateController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int repIdx = Integer.parseInt(request.getParameter("repIdx"));
		int bbsIdx = Integer.parseInt(request.getParameter("bbsIdx"));
		String repContent = request.getParameter("repContent");
		Timestamp repDate = new Timestamp(System.currentTimeMillis());
		String userID = request.getParameter("userID");
		String tmp = "null";
		
		if( userID.equals(tmp) ) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('�α����� �ϼ���.')");
			script.println("location.href='../member/logIn.jsp'");
			script.println("</script>");
		
		} else if(bbsIdx == 0) {
				PrintWriter script = response.getWriter();
				script.println("<script>");
				script.println("alert('��ȿ���� ���� ���Դϴ�.')");
				script.println("loaction.href='/BBS_JSP/indxe.jsp'");
				script.println("</script>");
				
		} else {
		RepVO rep = new RepVO();
		rep.setRepIdx(repIdx);
		rep.setRepContent(repContent);
		rep.setRepDate(repDate);
				
		// Service ��ü�� �޼ҵ� ȣ��
		RepService service = RepService.getInstance();
		int rs = service.repUpdate(rep);
		
		// memberLogin_action.jsp �������� �̵�
		request.setAttribute("repIdx", repIdx);
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/bbs/repUpdateAction.jsp");
		}
	}
}
