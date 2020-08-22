package com.manager.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.manager.model.enums.State;

@Entity
@Table(name = "TestingCenters")
public class TestBranch implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7802215186264446589L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;
	@Column
	private String phoneNumber;
	@Column
	private String name;
	@Column
	@Enumerated(EnumType.ORDINAL)
	private State state;
	@Column
	private String streetAddress;
	@Column
	private String zipcode;
	@Column
	private String city;

	public TestBranch() {
		super();
	}

	public TestBranch(int id, String phoneNumber, String name, State state, String streetAddress, String zipcode,
			String city) {
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.name = name;
		this.state = state;
		this.streetAddress = streetAddress;
		this.zipcode = zipcode;
		this.city = city;
	}

	public TestBranch(String name) {
		super();
		this.name = name;
	}

	public TestBranch(int id) { // This is only used to construct center by id for employee
								// So it should be safe to assume a test branch constructed here is in
								// the repository
								// so use the testBranch Services.

		this(id, "", "", State.AK, "", "", "");

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String toString() {
		return "TestBranch [id=" + id + ", phoneNumber=" + phoneNumber + ", name=" + name + ", state=" + state
				+ ", streetAddress=" + streetAddress + ", zipcode=" + zipcode + ", city=" + city + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestBranch other = (TestBranch) obj;
		if (id != other.id)
			return false;
		return true;
	}

	// Each test branch does not know its appointments but appointments know test
	// branch for now

}
