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
import com.rep.dao.RepDAO;

public class RepDeleteController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		int repIdx = Integer.parseInt(request.getParameter("repIdx"));
		int bbsIdx = Integer.parseInt(request.getParameter("bbsIdx"));
		String userID = request.getParameter("userID");
		String tmp = "null";
		
		System.out.println("repIdx : " + repIdx);
		System.out.println("bbsIdx : " + bbsIdx);
		System.out.println("userID : " + userID);
		
		// getRep �� ����
		RepDAO repDAO = RepDAO.getInstance();
		RepVO rep = repDAO.getRep(repIdx);
		
		if(repIdx == 0) {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('��ȿ���� ���� ���Դϴ�.')");
			script.println("location.href='/BBS_JSP/index.jsp'");
			script.println("</script>");
		
		} else if(!userID.equals(rep.getUserID())){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('������ �����ϴ�.')");
			script.println("history.back()");
			//response.sendRedirect("bbsView.ok?bbsIdx=" + rep.getBbsIdx());	
			script.println("</script>");

		} else {
		// Service ��ü�� �޼ҵ� ȣ��
		RepService service = RepService.getInstance();
		int rs = service.repDelete(repIdx); 

		// repDeleteAction.jsp �������� �̵�
		request.setAttribute("rs", rs);
		request.setAttribute("rep", rep);
		HttpUtil.forward(request, response, "/bbs/repDeleteAction.jsp");
		}
	}
}

