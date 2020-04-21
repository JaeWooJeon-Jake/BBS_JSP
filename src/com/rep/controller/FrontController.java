package com.rep.controller;

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

		list.put("/bbs/repWrite.re", new RepWriteController());
		list.put("/bbs/repDelete.re", new RepDeleteController());
		list.put("/bbs/repUpdate.re", new RepUpdateController());

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
