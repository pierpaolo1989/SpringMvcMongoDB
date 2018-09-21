package com.jcg.springmvc.mongo;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id,nome,cognome,password,username;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String id, String nome, String cognome, String password, String username) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.password = password;
		this.username = username;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
