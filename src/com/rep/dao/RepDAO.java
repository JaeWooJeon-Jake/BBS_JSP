package com.rep.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bbs.dao.BbsDAO;
import com.bbs.vo.BbsVO;
import com.rep.vo.RepVO;

public class RepDAO {
	private static RepDAO dao = new RepDAO();
	private RepDAO() {}
	public static RepDAO getInstance() {
		return dao;
	}
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int total = 0;
	
	public Connection connect() {
		
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
	
	// db에 저장된 댓글 총 갯수
	public int getCount(int bbsIdx) {
		String SQL = "select count(*) from reply where bbsIdx = ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsIdx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 return total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int repWrite(RepVO rep) {
		String SQL = "insert into reply values(?, ?, ?, ?, ?, ?)";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, rep.getRepIdx()); // 댓글 번호
			pstmt.setInt(2, rep.getBbsIdx()); // 게시글 번호
			pstmt.setString(3, rep.getUserID());
			pstmt.setString(4, rep.getUserName());
			pstmt.setString(5, rep.getRepContent()); // 게시글 날짜
			pstmt.setTimestamp(6, rep.getRepDate()); // 이용 가능성
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return -1;
	}
	
	// db 게시글 번호 재정렬
		public void initIdx() {
			String SQL1 = "set @cnt =0";
			String SQL2 = "update reply set reply.repIdx = @cnt:=@cnt+1";
			PreparedStatement pstmt1;
			PreparedStatement pstmt2;
			try {
				conn = connect();		
				pstmt1 = conn.prepareStatement(SQL1);
				pstmt1.executeUpdate();
				pstmt2 = conn.prepareStatement(SQL2);
				pstmt2.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt);
			}
		}
		
		// 댓글 리스트
		public ArrayList<RepVO> getList(int bbsIdx) {
			String SQL = "select * from reply where bbsIdx = ? order by repIdx desc limit 10";
			ArrayList<RepVO> list = new ArrayList<RepVO>();
			try {
				conn = connect();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, bbsIdx);
				rs = pstmt.executeQuery();
				while(rs.next()) {
					RepVO rep = new RepVO();
					rep.setRepIdx(rs.getInt(1));
					rep.setBbsIdx(rs.getInt(2));
					rep.setUserID(rs.getString(3));
					rep.setUserName(rs.getString(4));
					rep.setRepContent(rs.getString(5));
					rep.setRepDate(rs.getTimestamp(6));
					list.add(rep);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
			return list; // 데이터베이스 오류
		}
		
		public int repDelete(int repIdx) {
			String SQL = "delete from reply where repIdx = ?";
			try {
				conn = connect();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, repIdx);
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt);
			}
			return -1; // 데이터베이스 오류
		}
		
		// view 에서 보여줄 페이지 처리
		public RepVO getRep(int repIdx) {
			String SQL = "select * from reply where repIdx = ?";
			try {
				conn = connect();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setInt(1, repIdx);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					RepVO rep = new RepVO();
					rep.setRepIdx(rs.getInt(1));
					rep.setBbsIdx(rs.getInt(2));
					rep.setUserID(rs.getString(3));
					rep.setUserName(rs.getString(4));
					rep.setRepContent(rs.getString(5));
					rep.setRepDate(rs.getTimestamp(6));
					return rep;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
			return null; // 데이터베이스 오류
		}
		
		// 댓글 업데이트
		public int repUpdate(RepVO rep) {
			String SQL = "update reply set repContent=?, repDate=? where repIdx= ?";
			try {
				conn = connect();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, rep.getRepContent());
				pstmt.setTimestamp(2, rep.getRepDate()); // 게시글 날짜
				pstmt.setInt(3, rep.getRepIdx()); // 게시글 번호
				return pstmt.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt);
			}
			return -1; // 데이터베이스 오류
		}
}
