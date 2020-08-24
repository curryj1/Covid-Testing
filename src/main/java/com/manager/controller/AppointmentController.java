package com.manager.controller;

import java.util.List;
import java.util.Optional;

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

import com.manager.model.Appointment;
import com.manager.model.TestBranch;
import com.manager.model.enums.Result;
import com.manager.model.users.Employee;
import com.manager.model.users.Users;
import com.manager.service.AppointmentService;
import com.manager.service.EmployeeService;
import com.manager.service.TestBranchService;
import com.manager.service.UserService;
import com.mangager.exception.AlreadyExistsException;
import com.mangager.exception.NotFoundException;

@RestController
@RequestMapping(path = "/appointment")
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentController {

	@Autowired
	AppointmentService service;

	@Autowired
	TestBranchService tService;

	@Autowired
	EmployeeService eService;

	@Autowired
	UserService uService;

	@PostMapping(consumes = "application/json")
	public void CreateAppointment(@RequestBody Appointment a) throws AlreadyExistsException, NotFoundException {
		Optional<TestBranch> tb = tService.getByName(a.getCenter().getName());

		if (tb.isPresent())
			a.setCenter(tb.get());

		service.AddAppointment(a);
	}

	@GetMapping(path = "/all", produces = "application/json")
	public ResponseEntity<List<Appointment>> GetAllAppointment() {

		ResponseEntity<List<Appointment>> resp = new ResponseEntity<List<Appointment>>(service.getAllAppointment(),
				HttpStatus.OK);

		return resp;
	}

	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_CLERK')")
	@GetMapping(path = "center/{id}")
	public ResponseEntity<Optional<List<Appointment>>> GetAppointments(@PathVariable Integer id) {
		return new ResponseEntity<>(service.findByCenter(id), HttpStatus.OK);
	}

	@GetMapping(path = "/employee/{email}")
	public Optional<List<Appointment>> getAppointments(@PathVariable Employee email) {

		return service.findByEmail(email);
	}

	@GetMapping(path = "/{email}")
	public Optional<List<Appointment>> getAppointments(@PathVariable Users email) {
		return service.findByEmail(email);
	}

	@GetMapping(path = "/get/{id}", produces = "application/json")
	public Optional<Appointment> getAppointment(@PathVariable Integer id) throws NotFoundException {
		return service.getAppointment(id);
	}

	@PutMapping(path = "/update/{id}", consumes = "application/json")
	public void UpdateAppointment(@PathVariable int id, @RequestBody Appointment a) throws NotFoundException {
		a.setAppointment_id(id);

		Appointment app = service.getAppointment(id).orElse(new Appointment());

		if (a.getEmail() == null)
			a.setEmail(app.getEmail());
		if (a.getCenter() == null)
			a.setCenter(app.getCenter());
		if (a.getDate() == null)
			a.setDate(app.getDate());
		if (a.getTime() == null)
			a.setTime(app.getTime());
		if (a.getResult() == Result.None)
			a.setResult(app.getResult());

		service.UpdateAppointment(a);
	}

	@DeleteMapping(path = "/delete/{id}")
	public void DeleteAppointment(@PathVariable Integer id) throws NotFoundException {
		Appointment app = service.getAppointment(id).orElse(new Appointment());

		app.setResult(Result.Cancelled);

		service.UpdateAppointment(app);

	}

}
