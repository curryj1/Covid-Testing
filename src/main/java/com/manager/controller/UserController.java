package com.manager.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.model.enums.ERole;
import com.manager.model.users.Patient;
import com.manager.model.users.Users;
import com.manager.service.MailService;
import com.manager.service.UserService;
import com.mangager.exception.AlreadyExistsException;
import com.mangager.exception.NotFoundException;

@RestController
@RequestMapping(path = "/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService centerService;

	@Autowired
	MailService mailService;

	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<List<Users>> getAllUsers() {
		return new ResponseEntity<>(centerService.getAllUsers(), HttpStatus.OK);
	}

	@PostMapping(path = "/signup", consumes = "application/json")
	public void AddUsers(@RequestBody Patient t) throws AlreadyExistsException {
		Optional<Users> user = centerService.getUser(t.getEmail());

		if (!user.isEmpty())
			throw new AlreadyExistsException("User with email: " + t.getEmail() + "already exists");

		centerService.AddUser(t);

	}

	@GetMapping(path = "get/{email}", produces = "application/json")
	public Optional<Users> FindEmployee(@PathVariable String email) throws NotFoundException {

		Optional<Users> e = centerService.getUser(email);
		if (e.isEmpty()) {
			throw new NotFoundException("Could not find user with email: " + email);
		}
		return centerService.getUser(email);
	}

	@PutMapping(path = "update/{email}", consumes = "application/json")
	public void UpdateUsers(@PathVariable String email, @RequestBody Patient t) throws NotFoundException {
		t.setEmail(email);

		Optional<Users> user = centerService.getUser(t.getEmail()); // retrieves current parameters

		if (user.isEmpty())
			throw new NotFoundException("No user with email: " + t.getEmail());
		else {
			if (t.getFirstName() == null)
				t.setFirstName(user.get().getFirstName());

			if (t.getLastName() == null)
				t.setLastName(user.get().getLastName());

			if (t.getPassword() == null)
				t.setPassword(new BCryptPasswordEncoder().encode(user.get().getPassword()));
			if (t.getRole() == null)
				t.setRole(ERole.USER);
		}

//		MailMessage mail = new MailMessage();
//		mail.setEmailAddress(t.getEmail());
//		mail.setSubject("Registration");
//		mail.setBodyText("Your update has been successful");
//		mailService.sendEmail(mail);

		centerService.UpdateUser(t);

	}

	@DeleteMapping(path = "delete/{email}")
	public void DeleteBranch(@PathVariable String email) throws NotFoundException {
		centerService.DeleteUser(email);
	}
}
