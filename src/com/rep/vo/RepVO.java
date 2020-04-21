package com.rep.vo;

import java.sql.Timestamp;

public class RepVO {
	private int repIdx;
	private int bbsIdx;
	private String userID;
	private String userName;
	private String repContent;
	private Timestamp repDate;
	
	public int getRepIdx() {
		return repIdx;
	}
	public void setRepIdx(int repIdx) {
		this.repIdx = repIdx;
	}
	public int getBbsIdx() {
		return bbsIdx;
	}
	public void setBbsIdx(int bbsIdx) {
		this.bbsIdx = bbsIdx;
	}
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRepContent() {
		return repContent;
	}
	public void setRepContent(String repContent) {
		this.repContent = repContent;
	}
	public Timestamp getRepDate() {
		return repDate;
	}
	public void setRepDate(Timestamp repDate) {
		this.repDate = repDate;
	}	
}
