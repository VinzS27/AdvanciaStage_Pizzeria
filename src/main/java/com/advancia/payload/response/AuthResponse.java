package com.advancia.payload.response;

import com.advancia.model.Utente;

public class AuthResponse {
	private String message;
	private Boolean status;
	private Utente user;

	public AuthResponse(String message, Boolean status, Utente user) {
		this.message = message;
		this.status = status;
		this.user = user;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Utente getUser() {
		return user;
	}

	public void setUser(Utente user) {
		this.user = user;
	}
}
