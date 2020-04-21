package com.rep.service;

import com.rep.dao.RepDAO;
import com.rep.service.RepService;
import com.rep.vo.RepVO;

public class RepService {
	private static RepService service = new RepService();
	public RepDAO dao = RepDAO.getInstance();
	
	private RepService() {}
	public static RepService getInstance() {
		return service;
	}
	
	public int repWrite(RepVO rep) {
		return dao.repWrite(rep);
	}
	
	public int repDelete(int repIdx) {
		return dao.repDelete(repIdx);
	}
	
	public int repUpdate(RepVO rep) {
		return dao.repUpdate(rep);
	}
}
