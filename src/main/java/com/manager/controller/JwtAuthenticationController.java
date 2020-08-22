package com.manager.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.manager.config.JwtTokenUtil;
import com.manager.model.JwtResponse;
import com.manager.model.users.Employee;
import com.manager.model.users.Patient;
import com.manager.repository.UserRepository;
import com.manager.service.JwtUserDetailsService;
import com.manager.service.TestBranchService;
import com.manager.service.UserDetailsImpl;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;

	@Autowired
	private TestBranchService centerService;

	@Autowired
	UserRepository userRepository;

	@PostMapping(path = "/user/signin")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody Patient credentials) throws Exception {

		authenticate(credentials.getEmail(), credentials.getPassword());

		UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(credentials.getEmail());

		String token = jwtTokenUtil.generateToken(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), userDetails.getFirstName(),
				userDetails.getLastName(), roles));
	}

	@PostMapping(path = "/employee/signin")
	public ResponseEntity<?> createEAuthenticationToken(@RequestBody Employee credentials) throws Exception {

		authenticate(credentials.getEmail(), credentials.getPassword());

		UserDetailsImpl userDetails = (UserDetailsImpl) userDetailsService.loadUserByUsername(credentials.getEmail());

		String token = jwtTokenUtil.generateToken(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(token, userDetails.getUsername(), userDetails.getFirstName(),
				userDetails.getLastName(), roles, userDetails.getCenter()));
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
