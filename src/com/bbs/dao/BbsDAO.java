package com.bbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.bbs.vo.BbsVO;
import com.bbs.dao.BbsDAO;

public class BbsDAO {
	private static BbsDAO dao = new BbsDAO();
	private BbsDAO() {}
	public static BbsDAO getInstance() {
		return dao;
	}
	
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	private int total = 100;
	
	public Connection connect() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/BBS?serverTimezone=UTC", "root", "root");
		} catch (Exception e) {
			System.out.println("���� �߻� : " + e);
		}
		return conn;
	}
	
	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (Exception e) {
				System.out.println("���� �߻� : " + e);
			}
		}
		close(conn, ps);
	}
	
	public void close(Connection conn, PreparedStatement ps) {
		if(ps != null) {
			try {
				ps.close();
			} catch (Exception e) {
				System.out.println("���� �߻� : " + e);
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (Exception e) {
				System.out.println("���� �߻� : " + e);
			}
		}
	}
	
	public int getCount() {
		String SQL = "select count(*) from board";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				 return total = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return -1;
	}
	
	public int bbsWrite(BbsVO bbs) {
		String SQL = "INSERT INTO board VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbs.getBbsIdx()); // �Խñ� ��ȣ
			pstmt.setString(2, bbs.getBbsTitle());
			pstmt.setString(3, bbs.getUserID());
			pstmt.setString(4, bbs.getBbsContent());
			pstmt.setTimestamp(5, bbs.getBbsDate()); // �Խñ� ��¥
			pstmt.setInt(6, bbs.getBbsHit()); // �Խñ� ��ȸ��
			pstmt.setString(7, bbs.getBbsFile()); // ���� ���ε�
			pstmt.setString(8, bbs.getUserName());
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return -1; // �����ͺ��̽� ����
	} 
	
	
	// list �������� ������ ����Ʈ ó��
	public ArrayList<BbsVO> getList(int pageNumber) {
		String SQL = "select * from board where bbsIdx <= ? order by bbsIdx desc limit 10";
		ArrayList<BbsVO> list = new ArrayList<BbsVO>();
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, total - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				BbsVO bbs = new BbsVO();
				bbs.setBbsIdx(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsContent(rs.getString(4));
				bbs.setBbsDate(rs.getTimestamp(5));
				bbs.setBbsHit(rs.getInt(6));
				bbs.setUserName(rs.getString(8));
				list.add(bbs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return list; // �����ͺ��̽� ����
	}
	
	public boolean nextPage(int pageNumber) {
		String SQL = "select * from board where bbsIdx <= ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, total - (pageNumber - 1) * 10);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return false; // �����ͺ��̽� ����
	}
	
	// view ���� ������ ������ ó��
	public BbsVO getBbs(int bbsIdx) {
		String SQL = "select * from board where bbsIdx = ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsIdx);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				BbsVO bbs = new BbsVO();
				bbs.setBbsIdx(rs.getInt(1));
				bbs.setBbsTitle(rs.getString(2));
				bbs.setUserID(rs.getString(3));
				bbs.setBbsContent(rs.getString(4));
				bbs.setBbsDate(rs.getTimestamp(5));
				bbs.setBbsHit(rs.getInt(6));
				bbs.setBbsFile(rs.getString(7));
				bbs.setUserName(rs.getString(8));
				return bbs;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		return null; // �����ͺ��̽� ����
	}
	
	// �Խñ� ������Ʈ
	public int update(BbsVO bbs) {
		String SQL = "update board set bbsTitle=?, userID=?, bbsContent=?, bbsDate=? where bbsIdx= ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setString(1, bbs.getBbsTitle());
			pstmt.setString(2, bbs.getUserID());
			pstmt.setString(3, bbs.getBbsContent());
			pstmt.setTimestamp(4, bbs.getBbsDate()); // �Խñ� ��¥
			pstmt.setInt(5, bbs.getBbsIdx()); // �Խñ� ��ȣ
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return -1; // �����ͺ��̽� ����
	}
	
	public int delete(int bbsIdx) {
		String SQL = "delete from board where bbsIdx = ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, bbsIdx);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return -1; // �����ͺ��̽� ����
	}
	
	// ��ȸ�� ����
	public int updateHit(int idx) {
		String SQL = "update board set bbsHit= bbsHit+1 where bbsIdx = ?";
		try {
			conn = connect();
			pstmt = conn.prepareStatement(SQL);
			pstmt.setInt(1, idx);
			return pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt);
		}
		return -1; // �����ͺ��̽� ����
	}
	
	// db �Խñ� ��ȣ ������
	public void initIdx() {
		String SQL1 = "set @cnt =0";
		String SQL2 = "update board set board.bbsIdx = @cnt:=@cnt+1";
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

	// list �������� ������ ����Ʈ ó��
		public ArrayList<BbsVO> getSearchList(String catgo, String search) {
			
			ArrayList<BbsVO> list = new ArrayList<BbsVO>();
			try {
				if(catgo.equals("bbsTitle")) {
				String SQL = "select * from board where bbsTitle like ? order by bbsIdx desc";
				conn = connect();
				pstmt = conn.prepareStatement(SQL);
				pstmt.setString(1, "%" + search + "%");
				} else if(catgo.equals("userName")) {
					String SQL = "select * from board where userName like ? order by bbsIdx desc";
					conn = connect();
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, "%" + search + "%");
				} else if(catgo.equals("bbsContent")) {
					String SQL = "select * from board where bbsContent like ? order by bbsIdx desc";
					conn = connect();
					pstmt = conn.prepareStatement(SQL);
					pstmt.setString(1, "%" + search + "%");
				}
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BbsVO bbs = new BbsVO();
					bbs.setBbsIdx(rs.getInt(1));
					bbs.setBbsTitle(rs.getString(2));
					bbs.setUserID(rs.getString(3));
					bbs.setBbsContent(rs.getString(4));
					bbs.setBbsDate(rs.getTimestamp(5));
					bbs.setBbsHit(rs.getInt(6));
					bbs.setUserName(rs.getString(8));
					list.add(bbs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				close(conn, pstmt, rs);
			}
			return list; // �����ͺ��̽� ����
		}
}
