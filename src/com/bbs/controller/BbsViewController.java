package com.bbs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.service.BbsService;
import com.bbs.vo.BbsVO;

public class BbsViewController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("bbsIdx"));
		
				// Service 객체의 메소드 호출
				BbsService service = BbsService.getInstance();
				BbsVO rs = service.getBbs(idx);
				
				// view.jsp 페이지로 이동
				request.setAttribute("rs", rs);
				HttpUtil.forward(request, response, "/bbs/view.jsp");
		}
}
