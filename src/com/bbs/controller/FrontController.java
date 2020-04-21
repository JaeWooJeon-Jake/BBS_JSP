package com.bbs.controller;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class FrontController extends HttpServlet{
	private static final long serialVersionUID = 1L;
	String charset = null;
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig sc) throws ServletException {
		// TODO Auto-generated method stub
		charset = sc.getInitParameter("charset");
		list = new HashMap<String, Controller>();

		list.put("/bbs/bbsWrite.ok", new BbsWriteController());
		list.put("/bbs/bbsView.ok", new BbsViewController());
		list.put("/bbs/bbsUpdate.ok", new BbsUpdateController());
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding(charset); // Ŭ���̾�Ʈ�κ��� POST������� ���޵� ���� ���ڿ��� ���ڵ� ó���ϴ� �޼ҵ�
		String url = request.getRequestURI();
		String contextPath = request.getContextPath();
		String path = url.substring(contextPath.length()); 
		Controller subController = list.get(path); 
		subController.execute(request, response);
	}
}
