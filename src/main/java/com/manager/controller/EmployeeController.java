package com.manager.controller;

import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.manager.model.MailMessage;
import com.manager.model.TestBranch;
import com.manager.model.enums.ERole;
import com.manager.model.users.Employee;
import com.manager.service.EmployeeService;
import com.manager.service.MailService;
import com.manager.service.TestBranchService;
import com.mangager.exception.AlreadyExistsException;
import com.mangager.exception.NotFoundException;

@RestController
@RequestMapping(path = "/employee")
@CrossOrigin(origins = "http://localhost:4200")
public class EmployeeController {

	static Logger log = Logger.getLogger(EmployeeController.class.getName());
	@Autowired
	EmployeeService centerService;

	@Autowired
	TestBranchService BranchService;

	@Autowired
	MailService mailService;

	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<List<Employee>> getAllBranches() {
		return new ResponseEntity<>(centerService.getAllEmployees(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_MANAGER')")
	@PostMapping(path = "/signup", consumes = "application/json")
	public void AddEmployee(@RequestBody Employee t) throws AlreadyExistsException, NotFoundException {

		Optional<Employee> emp = centerService.getEmployee(t.getEmail());
		if (!emp.isEmpty()) {
			AlreadyExistsException e = new AlreadyExistsException(
					"Cannot Add. Employee already exists with email: " + t.getEmail());

			log.debug("failed to register employee", e);
			throw e;
		}
		Optional<TestBranch> tb = BranchService.getByName(t.getCenter().getName());

		if (tb.isPresent())
			t.setCenter(tb.get());
		MailMessage mail = new MailMessage();
		mail.setEmailAddress("donotreply.batch@gmail.com ");
		mail.setSubject("Registration");
		mail.setBodyText("Your Registration has been successful");
		mailService.sendEmail(mail);

		centerService.AddEmployee(t);

	}

	@PostMapping(path = "/signup/{role}", consumes = "application/json")
	public ResponseEntity<Employee> createManager(@RequestBody Employee emp, @PathVariable ERole role)
			throws AlreadyExistsException, NotFoundException {

		Optional<Employee> e = centerService.getEmployee(emp.getEmail());
		if (!e.isEmpty())
			throw new AlreadyExistsException("Cannot Add. Employee already exists with email: " + emp.getEmail());

		Optional<TestBranch> tb = BranchService.getByName(emp.getCenter().getName());

		if (tb.isPresent())
			emp.setCenter(tb.get());

		emp.setRole(role);
		centerService.AddEmployee(emp);

		return new ResponseEntity<>(emp, HttpStatus.OK);
	}

	@GetMapping(path = "get/{email}", produces = "application/json")
	public Optional<Employee> FindEmployee(@PathVariable String email) throws NotFoundException {

		Optional<Employee> e = centerService.getEmployee(email);
		if (e.isEmpty()) {
			throw new NotFoundException("Could not find employee with email: " + email);
		}
		return centerService.getEmployee(email);
	}

	@PutMapping(path = "update/{email}", consumes = "application/json")
	public void UpdateEmployee(@PathVariable String email, @RequestBody Employee t) throws NotFoundException {
		t.setEmail(email);

		System.out.println(t.getEmail());

		Employee e = centerService.getEmployee(t.getEmail()).orElseThrow(); // retrieves current parameters

		if (t.getCenter() == null)
			t.setCenter(e.getCenter());

		if (t.getFirstName() == null)
			t.setFirstName(e.getFirstName());

		if (t.getLastName() == null)
			t.setLastName(e.getLastName());

		if (t.getPassword() == null)
			t.setPassword(e.getPassword());

		if (t.getRole() == null)
			t.setRole(e.getRole());
		t.setCenter(BranchService.getBranch(t.getCenter().getId()).orElse(null));

		centerService.UpdateEmployee(t);
//		MailMessage mail = new MailMessage();
//		mail.setEmailAddress(t.getEmail());
//		mail.setSubject("Registration");
//		mail.setBodyText("Your Update has been successful");
//		mailService.sendEmail(mail);

	}

	@DeleteMapping(path = "delete/{email}")
	public ResponseEntity<String> DeleteEmployee(@PathVariable String email) throws NotFoundException {
		centerService.DeleteEmployee(email);

		return new ResponseEntity<>(email, HttpStatus.OK);
	}

}
