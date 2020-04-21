package com.bbs.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.service.BbsService;
import com.bbs.vo.BbsVO;

public class BbsUpdateController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx =  Integer.parseInt(request.getParameter("bbsIdx"));
		String title = request.getParameter("bbsTitle");
		String userid = request.getParameter("userID");
		String content = request.getParameter("bbsContent");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		
		// VO ��ü�� ������ ���ε�
				BbsVO bbs = new BbsVO();
				bbs.setBbsIdx(idx);
				bbs.setBbsTitle(title);
				bbs.setUserID(userid);
				bbs.setBbsContent(content);
				bbs.setBbsDate(date);
		
		// Service ��ü�� �޼ҵ� ȣ��
		BbsService service = BbsService.getInstance();
		int rs = service.update(bbs);
	
		
		// bbsWrite_action.jsp �������� �̵�
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/bbs/updateAction.jsp");
	}
}
