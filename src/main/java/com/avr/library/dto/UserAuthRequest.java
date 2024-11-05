package com.avr.library.dto;

public class UserAuthRequest {

	private String username;

	private String password;

	@Override
	public String toString() {
		return "AuthRequest [username=" + username + ", password=" + password + "]";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAuthRequest() {
		super();
	}

	public UserAuthRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

}