package com.code.model;

import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;
public class LoginForm {
	@NotEmpty
	@Size(min = 1, max = 50)
	private String username;
	@NotEmpty
	@Size(min = 1, max = 20)
	private String password;

	public void setUserName(String userName) {
		this.username = userName;
	}
	public String getUserName() {
		return username;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
}