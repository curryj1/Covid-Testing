package com.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manager.model.users.Employee;
import com.manager.repository.AppointmentRepository;
import com.manager.repository.EmployeeRepository;
import com.manager.repository.UserRepository;
import com.mangager.exception.AlreadyExistsException;
import com.mangager.exception.NotFoundException;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	UserRepository Lrepository;

	@Autowired
	AppointmentRepository Arepository;

	public List<Employee> getAllEmployees() { // finds all the Employee branches
		return repository.findAll();
	}

	public Optional<Employee> getEmployee(String email) { // finds Employee branch by id.

		Optional<Employee> e = repository.findById(email);

		return repository.findById(email);

	}

	public List<Employee> getemployees(Iterable<String> emails) {
		return repository.findAllById(emails);

	}

	public void AddEmployee(Employee t) throws AlreadyExistsException {

		Optional<Employee> e = repository.findById(t.getEmail());

		t.setPassword(new BCryptPasswordEncoder().encode(t.getPassword()));

		repository.save(t);

	}

	public Optional<Employee> UpdateEmployee(Employee t) throws NotFoundException {
		Optional<Employee> emp = repository.findById(t.getEmail());

		t.setPassword(new BCryptPasswordEncoder().encode(t.getPassword()));
		repository.save(t);

		return emp;

	}

	@Transactional
	public void DeleteEmployee(String email) throws NotFoundException {

		Optional<Employee> emp = repository.findById(email);
		if (emp.isEmpty())
			throw new NotFoundException("Cannot delete. No Employee with email: " + email);

		Arepository.DeleteAllbyEmail(emp.get());
		repository.deleteById(email);
	}

}
