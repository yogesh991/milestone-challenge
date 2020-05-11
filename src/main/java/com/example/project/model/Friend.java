package com.example.project.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Friend {
	
	@Id
	private int friendId;
	private String friendName;
	private String friendMail;
	private double individualAmount;
	private String balanceStatus;
	
	public double getIndividualAmount() {
		return individualAmount;
	}
	public void setIndividualAmount(double individualAmount) {
		this.individualAmount = individualAmount;
	}
	public String getBalanceStatus() {
		return balanceStatus;
	}
	public void setBalanceStatus(String balanceStatus) {
		this.balanceStatus = balanceStatus;
	}
	public int getFriendId() {
		return friendId;
	}
	public void setFriendId(int friendId) {
		this.friendId = friendId;
	}
	public String getFriendName() {
		return friendName;
	}
	public void setFriendName(String friendName) {
		this.friendName = friendName;
	}
	public String getFriendMail() {
		return friendMail;
	}
	public void setFriendMail(String friendMail) {
		this.friendMail = friendMail;
	}

	public Friend() {
		super();
	}
	
	
	
	
}
