package com.manager.model.users;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.manager.model.TestBranch;
import com.manager.model.enums.ERole;

@Entity
@Table(name = "Employee2")
public class Employee extends Users {

	@Id
	private String email;

	@ManyToOne
	@JoinColumn(name = "tb_id", nullable = false)
	private TestBranch center; // (unidirectional OneToMany relationship)

	public Employee(String email, String password, String firstName, String lastName, ERole role, TestBranch center) {
		super(email, password, firstName, lastName, role);
		this.center = center;
		// TODO Auto-generated constructor stub
	}

	public Employee(String email, TestBranch center) {
		super();
		this.email = email;
		this.center = center;
	}

	public Employee(String email) {
		super();
		this.email = email;
	}

	public Employee() {
		super();
	}

	@Override
	public String getEmail() {
		return email;
	}

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	public TestBranch getCenter() {
		return center;
	}

	public void setCenter(TestBranch center) {
		this.center = center;
	}

	@Override
	public String toString() {
		return "Employee [email=" + email + ", center=" + center + super.toString() + "]";
	}

}
