package com.scai.socialproject.alpha.socialnetworkalpha.dto;

public class User {
	private String idUser;
	private String email;
	private String pass;
	
	public User() {}

	public User(String idUser, String email, String pass) {
		super();
		this.idUser = idUser;
		this.email = email;
		this.pass = pass;
	}

	public String getIdUser() {
		return idUser;
	}

	public void setIdUser(String idUser) {
		this.idUser = idUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", email=" + email + ", pass=" + pass + "]";
	}
}
