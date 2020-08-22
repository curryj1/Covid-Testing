package com.manager.model;

import java.util.List;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String email;
	private String firstName;
	private String lastName;
	private List<String> roles;
	private TestBranch center;

	public JwtResponse(String token, String email, String firstName, String lastName, List<String> roles) {
		super();
		this.token = token;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
	}

	public JwtResponse(String token, String email, String firstName, String lastName, List<String> roles,
			TestBranch center) {
		super();
		this.token = token;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.roles = roles;
		this.center = center;
	}

	public String getAccessToken() {
		return token;
	}

	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}

	public String getTokenType() {
		return type;
	}

	public void setTokenType(String tokenType) {
		this.type = tokenType;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getRoles() {
		return roles;
	}

	public TestBranch getCenter() {
		return center;
	}

	public void setCenter(TestBranch center) {
		this.center = center;
	}

}
