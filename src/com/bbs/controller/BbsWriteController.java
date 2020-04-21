package com.bbs.controller;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bbs.controller.HttpUtil;
import com.bbs.vo.BbsVO;
import com.bbs.service.BbsService;

import java.io.File;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

public class BbsWriteController implements Controller{
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String directory = request.getRealPath("/upload/");
		int maxSize = 1024 * 1024 * 100;
		String encoding = "UTF-8";
		MultipartRequest multi
		= new MultipartRequest(request, directory, maxSize, encoding,
				new DefaultFileRenamePolicy());
		
		
//		int idx = 0;
//		String title = request.getParameter("bbsTitle");
//		String userid = request.getParameter("userID");
//		String content = request.getParameter("bbsContent");
//		Timestamp date = new Timestamp(System.currentTimeMillis());
//		int hit = 0;
		
		int idx = 0;
		String title = multi.getParameter("bbsTitle");
		String userid = multi.getParameter("userID");
		String content = multi.getParameter("bbsContent");
		Timestamp date = new Timestamp(System.currentTimeMillis());
		int hit = 0;
		String file = multi.getFilesystemName("file");
		String username = multi.getParameter("userName");
		
		// VO 객체에 데이터 바인딩
		BbsVO bbs = new BbsVO();
		bbs.setBbsIdx(idx);
		bbs.setBbsTitle(title);
		bbs.setUserID(userid);
		bbs.setBbsContent(content);
		bbs.setBbsDate(date);
		bbs.setBbsHit(hit);
		bbs.setBbsFile(file);
		bbs.setUserName(username);
		
		// Service 객체의 메소드 호출
		BbsService service = BbsService.getInstance();
		int rs = service.bbsWrite(bbs);
	
		
		// bbsWrite_action.jsp 페이지로 이동
		request.setAttribute("rs", rs);
		HttpUtil.forward(request, response, "/bbs/writeAction.jsp");
	}
}
