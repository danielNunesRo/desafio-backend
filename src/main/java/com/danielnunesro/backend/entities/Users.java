package com.danielnunesro.backend.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import com.danielnunesro.backend.dtos.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Users implements Serializable {
	

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	@Column(name = "last_name")
	private String lastname;
	
	@Column(unique = true)
	private String document;
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private BigDecimal balance;
	
	@Enumerated(EnumType.STRING)
	private UserType userType;
	
	public Users() {
		
	}

	public Users(Long id, String name, String lastname, String document, String email, String password,
			BigDecimal balance, UserType userType) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.document = document;
		this.email = email;
		this.password = password;
		this.balance = balance;
		this.userType = userType;
	}
	
	public Users(UserDTO user) {
		this.name = user.firstName();
		this.lastname = user.lastName();
		this.balance = user.balance();
		this.userType = user.userType();
		this.password = user.password();
		this.email = user.email();
		this.document = user.document();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
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

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public UserType getUserType() {
		return userType;
	}

	public void setUserType(UserType userType) {
		this.userType = userType;
	}
	
	
	
	
}
