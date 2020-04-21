package com.member.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.member.vo.MemberVO;
import com.member.dao.MemberDAO;

public class MemberDAO {
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		return dao;
	}

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public Connection connect() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC", "root", "root");
		} catch (Exception e) {
			System.out.println("오류 발생 : " + e);
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("오류 발생 : " + e);
			}
		}
		close(conn, ps);
	}
	
	public void close(Connection conn, PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				System.out.println("오류 발생 : " + e);
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("오류 발생 : " + e);
			}
		}
	}
	
	public int memberLogin(String id, String pw) {
	
		String SQL = "select userPassword from user where userID = ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(pw))
					return 1; // 로그인 성공
			 else 
				return 0; // 비밀번호 불일치
			}
			return -1; // 아이디가 없음
		} catch (Exception e) {
			System.out.println("오류 발생 : " + e);
		} finally {
			close(conn, pstmt, rs);
		}
		return -2; // 데이터베이스 오류
	}
	
	public int memberJoin(MemberVO member) {
		String SQL = "insert into user values(?, ?, ?, ?, ?, ?)";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getUserID() );
			pstmt.setString(2, member.getUserPassword());
			pstmt.setString(3, member.getUserName());
			pstmt.setString(4, member.getUserGender());
			pstmt.setString(5, member.getUserEmail());
			pstmt.setTimestamp(6, member.getUserDate());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return -1;
	}
	
	public int memberIDcheck(String id) {
		String SQL = "select userID from user where userID=?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				if(rs.getString(1).equals(id))
					return 1; // 중복 아이디
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return -1; // 아이디 찾지 못함
	}
	
	public MemberVO memberInfo(String id) {
		String SQL = "select * from user where userID= ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				MemberVO member = new MemberVO();
				member.setUserID(rs.getString(1));
				member.setUserPassword(rs.getString(2));
				member.setUserName(rs.getString(3));
				member.setUserGender(rs.getString(4));
				member.setUserEmail(rs.getString(5));
				member.setUserDate(rs.getTimestamp(6));		
				return member;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return null; // 아이디 찾지 못함
	}
	
	public int memberUpdate(MemberVO member) {
		String SQL = "update user set userPassword = ?, userName = ?, userGender = ?, userEmail = ?, userDate = ? where userID = ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, member.getUserPassword());
			pstmt.setString(2, member.getUserName());
			pstmt.setString(3, member.getUserGender());
			pstmt.setString(4, member.getUserEmail());
			pstmt.setTimestamp(5, member.getUserDate());
			pstmt.setString(6, member.getUserID() );
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return -1;
	}
	
	public String memberFindID(String name, String email) {
		String SQL = "select userID from user where userName= ? and userEmail = ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return null; // 아이디 찾지 못함
	}
	
	public String memberFindPW(String id, String name) {
		String SQL = "select userPassword from user where userID= ? and userName = ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return null; // 아이디 찾지 못함
	}
}
