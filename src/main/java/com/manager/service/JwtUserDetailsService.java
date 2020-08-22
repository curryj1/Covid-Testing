package com.manager.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.manager.model.users.Users;
import com.manager.repository.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	private static final Logger LOGGER = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Autowired
	UserService userService;

	@Autowired
	EmployeeService empService;

	@Autowired
	UserRepository repository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Users user = repository.findById(username).orElseThrow();

		UserDetailsImpl Ud = UserDetailsImpl.build(user);
		return Ud;
	}

//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		LOGGER.debug("Authenticating user with username={}");
//		Users user = new Users();
//		try {
//			user = userService.getUser(username).orElse(new Users());
//		} catch (NotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return UserDetailsImpl.build(user);
//	}

}
