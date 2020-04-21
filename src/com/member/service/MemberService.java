package com.member.service;

import com.member.vo.MemberVO;
import java.io.PrintWriter;
import com.member.dao.MemberDAO;
import com.member.service.MemberService;


public class MemberService {
	private static MemberService service = new MemberService();
	public MemberDAO dao = MemberDAO.getInstance();
	
	private MemberService() {}
	public static MemberService getInstance() {
		return service;
	}
	
	public int memberLogin(String id, String pw) {
		 return dao.memberLogin(id,pw);
	}
	
	public int memberJoin(MemberVO member) {
		 return dao.memberJoin(member);
	}

	public int memberIDcheck(String id) {
		 return dao.memberIDcheck(id);
	}
	
	public MemberVO memberInfo(String id) {
		 return dao.memberInfo(id);
	}
	
	public int memberUpdate(MemberVO member) {
		 return dao.memberUpdate(member);
	}
	
	public String memberFindID(String name, String email) {
		 return dao.memberFindID(name, email);
	}
	
	public String memberFindPW(String id, String name) {
		 return dao.memberFindPW(id, name);
	}
}
