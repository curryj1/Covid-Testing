package com.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.manager.model.users.Patient;
import com.manager.model.users.Users;
import com.manager.repository.UserRepository;
import com.mangager.exception.NotFoundException;

@Service
public class UserService {
	@Autowired
	UserRepository repository;

	public List<Users> getAllUsers() { // finds all the test Users
		return repository.findAll();
	}

	public Optional<Users> getUser(String email) { // finds test User by email.

		return repository.findById(email);

	}

	public List<Users> getUsers(Iterable<String> emails) {
		return repository.findAllById(emails);

	}

	public void AddUser(Users t) {

		t.setPassword(new BCryptPasswordEncoder().encode(t.getPassword()));

		repository.save(t);

	}

	public void UpdateUser(Patient t) throws NotFoundException {

		System.out.println("HAAAAAAAAAAAAW" + t);
		repository.save(t);
	}

	public void DeleteUser(String email) throws NotFoundException {
		Optional<Users> user = repository.findById(email);
		if (user.isEmpty())
			throw new NotFoundException("No user with email: " + email);

		repository.deleteById(email);
	}
}
