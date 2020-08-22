package com.manager.model.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.manager.model.enums.ERole;

@Entity(name = "Users2")
@Inheritance(strategy = InheritanceType.JOINED)
public class Users {

	@Id
	private String email;

	@Column(nullable = false)
	private String password;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	private ERole role;

	public Users() {
		super();
	}

	public Users(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	public Users(String email, String password, String firstName, String lastName, ERole role) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.role = role;
	}

	public Users(String email) {
		super();
		this.email = email;
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

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public ERole getRole() {
		return role;
	}

	public void setRole(ERole role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", role=" + role;
	}

}
