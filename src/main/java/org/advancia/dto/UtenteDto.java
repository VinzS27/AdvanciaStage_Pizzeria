package org.advancia.dto;

import java.util.ArrayList;
import java.util.List;

import org.advancia.entity.Pizza;

public class UtenteDto {

	private long id;
	private String username;
	private String password;
	private List<Pizza> pizza = new ArrayList<>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<Pizza> getPizza() {
		return pizza;
	}

	public void setPizza(List<Pizza> pizza) {
		this.pizza = pizza;
	}
}
