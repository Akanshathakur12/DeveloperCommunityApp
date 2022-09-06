package com.devcom.dto;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

	public class UserDTO {
		
		@SuppressWarnings("unused")
		private int userId;
		
		@Email
		private String userName;
		
		@NotEmpty(message = "password is required")
		private String password;
		
		@NotEmpty(message = "role is required")
		private String role;

		public UserDTO(@Email String userName, @NotEmpty(message = "password is required") String password,
				@NotEmpty(message = "role is required") String role) {
			super();
			this.userName = userName;
			this.password = password;
			this.role = role;
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


