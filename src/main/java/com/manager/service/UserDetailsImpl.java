package com.manager.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.manager.model.TestBranch;
import com.manager.model.users.Employee;
import com.manager.model.users.Users;

//GOTTA FIX THIS CLASS FOR USING
public class UserDetailsImpl implements UserDetails {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6920265510332536065L;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsImpl.class);

	private String email;

	@JsonIgnore
	private String password;

	private String firstName;

	private String lastName;

	private TestBranch center;

	private Collection<? extends GrantedAuthority> authorities;

	public UserDetailsImpl(String email, String password, String firstName, String lastName,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.authorities = authorities;
	}

	public UserDetailsImpl(String email, String password, String firstName, String lastName, TestBranch center,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.center = center;
		this.authorities = authorities;
	}

	public static UserDetailsImpl build(Users user) {
		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());

		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(authority);

		if (user instanceof Employee) {
			TestBranch center = ((Employee) user).getCenter();
			System.out.println(
					"**********************************\n" + "Center is: " + center + "*************************");
			return new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(),
					center, authorities);
		}
		LOGGER.debug("Building User details");

		return new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(),
				authorities);
	}

//	public static UserDetailsImpl Ebuild(Employee user) {
//		SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().name());
//
//		ArrayList<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//		authorities.add(authority);
//		System.out.println("*********************************");
//
//		LOGGER.debug("Building Employee details");
//
//		return new UserDetailsImpl(user.getEmail(), user.getPassword(), user.getFirstName(), user.getLastName(),
//				user.getCenter(), authorities);
//	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public TestBranch getCenter() {
		return center;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authorities == null) ? 0 : authorities.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
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
		UserDetailsImpl other = (UserDetailsImpl) obj;
		return Objects.equals(email, other.email);

	}

}
