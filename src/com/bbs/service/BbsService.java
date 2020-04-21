package com.bbs.service;

import com.bbs.dao.BbsDAO;
import com.bbs.service.BbsService;
import java.io.PrintWriter;
import com.bbs.vo.BbsVO;

public class BbsService {
	private static BbsService service = new BbsService();
	public BbsDAO dao = BbsDAO.getInstance();
	
	private BbsService() {}
	public static BbsService getInstance() {
		return service;
	}
	
	public int bbsWrite(BbsVO bbs) {
		 return dao.bbsWrite(bbs);
	}
	
	public BbsVO getBbs(int bbsIdx) {
		 return dao.getBbs(bbsIdx);
	}
	
	public int update(BbsVO bbs) {
		 return dao.update(bbs);
	}
}
