package com.devcom.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name="response")
public class Response {
	
	public Response() {
		super();
	}
	public Response(String answer, Date respDate,int respId) {
		super();
		this.answer=answer;
		this.respDate=respDate;
		this.respId=respId;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int respId;
	
	private String answer;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date respDate;
	
	@OneToOne(targetEntity = Developer.class, cascade = CascadeType.MERGE)
	@JoinColumn(name="rd_fk")
	private Developer developer;
	
	@ManyToOne(cascade =CascadeType.MERGE)
	@JoinColumn(name="rf_fk")
	private Feed feed;
	
	public Feed getFeed() {
		return feed;
	}
	public void setFeed(Feed feed) {
		this.feed = feed;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	public int getRespId() {
		return respId;
	}
	public void setRespId(int respId) {
		this.respId = respId;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Date getRespDate() {
		return respDate;
	}
	public void setRespDate(Date respDate) {
		this.respDate = respDate;
	}
	
	
}