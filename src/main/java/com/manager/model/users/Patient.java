package com.manager.model.users;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.manager.model.enums.ERole;

@Entity
@Table(name = "Patients")
public class Patient extends Users implements Serializable {

	private static final long serialVersionUID = -5114183883356272994L;

	@Id
	private String email;

	private String firstName;

	private String lastName;

	private String password;

	final private ERole role = ERole.USER;

	public Patient() {
		super();
	}

	public Patient(String email, String firstName, String lastName, String password) {
		super(email, firstName, lastName, password, ERole.USER);

	}

	public Patient(String email) {
		this.email = email;
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getFirstName() {
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public ERole getRole() {
		return role;
	}

	@Override
	public String toString() {
		return "Users [email=" + this.getEmail() + ", firstName=" + firstName + ", lastName=" + lastName + ", password="
				+ password + ", role=" + role + "]";
	}

}
