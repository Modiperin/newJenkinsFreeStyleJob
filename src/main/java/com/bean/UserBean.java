package com.bean;

import javax.servlet.annotation.MultipartConfig;

import com.fasterxml.jackson.annotation.JsonIgnore;
@MultipartConfig(
		maxFileSize = 1048576,
		fileSizeThreshold = 1048576
		)

public class UserBean {
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@JsonIgnore
	private String userId;
	private String firstName;
	private String email;
	private String password;
	private String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
