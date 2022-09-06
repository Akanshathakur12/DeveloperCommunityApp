package com.devcom.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

@Entity
@Table(name = "UserTable", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User {
		
	public User() {
		super();
		
	}

	public User(String userName, String password, String role) {
		super();
		this.userName= userName;
		this.password=password;
		this.role=role;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int userId;
	
	@Email
	String userName;
		
	String password;
		
	String role;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
