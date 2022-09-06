package com.devcom.entity;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="developers")
public class Developer {
	public Developer() {
		super();
	}
	public Developer(int devId, String name, String email, String skillLevel, Date memberSince) {
		super();
		this.devId=devId;
		this.name=name;
		this.email=email;
		this.skillLevel=skillLevel;
		this.memberSince=memberSince;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int devId;
	private String name;
	private String email;
	private String skillLevel;
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date memberSince;
	
	@OneToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name="ud_fk")
	private User user;
	
	private int totalFeeds;
	private int reputation;
	private boolean isVerified;
	private boolean isBlocked;
 
	public int getDevId() {
		return devId;
	}
	public void setDevId(int devId) {
		this.devId = devId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSkillLevel() {
		return skillLevel;
	}
	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}
	public Date getMemberSince() {
		return memberSince;
	}
	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}
	public int getTotalFeeds() {
		return totalFeeds;
	}
	public void setTotalFeeds(int totalFeeds) {
		this.totalFeeds = totalFeeds;
	}
	public int getReputation() {
		return reputation;
	}
	public void setReputation(int reputation) {
		this.reputation = reputation;
	}
	public boolean isVerified() {
		return isVerified;
	}
	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}
	public boolean isBlocked() {
		return isBlocked;
	}
	public void setBlocked(boolean isBlocked) {
		this.isBlocked = isBlocked;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
