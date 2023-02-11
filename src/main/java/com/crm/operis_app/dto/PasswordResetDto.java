package com.crm.operis_app.dto;

import javax.validation.constraints.NotEmpty;

public class PasswordResetDto {
	@NotEmpty
	String newPass;
	@NotEmpty
	String token;


	public String getNewPass() {
		return newPass;
	}
	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
