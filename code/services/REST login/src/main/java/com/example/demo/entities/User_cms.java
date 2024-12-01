package com.example.demo.entities;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User_cms implements Serializable{


	private static final long serialVersionUID = 1L;

	@Id
	private String id;
	 
	private String first_name;
	private String last_name;
	private String login;
	private String password;
	 
	 
	
	public User_cms() {}
	
	public User_cms(String id, String nom, String prenom, String login, String password) {
		super();
		this.id = id;
		this.first_name = nom;
		this.last_name = prenom;
		this.login = login;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + first_name + ", prenom=" + last_name + ", login=" + login + ", password=" + password
				+ "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNom() {
		return first_name;
	}

	public void setNom(String nom) {
		this.first_name = nom;
	}

	public String getPrenom() {
		return last_name;
	}

	public void setPrenom(String prenom) {
		this.last_name = prenom;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	
	
	
	 
	 
	 

	
}
