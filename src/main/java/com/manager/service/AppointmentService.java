package com.manager.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manager.model.Appointment;
import com.manager.model.TestBranch;
import com.manager.model.enums.Result;
import com.manager.model.users.Employee;
import com.manager.model.users.Users;
import com.manager.repository.AppointmentRepository;
import com.manager.repository.EmployeeRepository;
import com.manager.repository.TestBranchRepository;
import com.mangager.exception.AlreadyExistsException;
import com.mangager.exception.NotFoundException;

@Service
public class AppointmentService {

	@Autowired
	AppointmentRepository repository;

	@Autowired
	TestBranchRepository cRepository;

	@Autowired
	EmployeeRepository eRepository;

	public List<Appointment> getAllAppointment() {

		return repository.findAll();
	}

	@Transactional
	public void DeleteAllByCenter(TestBranch center) {
		repository.DeleteAllByCenter(center);
	}

	public Optional<Appointment> getAppointment(Integer id) throws NotFoundException { // finds Appointment branch by
																						// id.
		// Later fix to account for if it doesn't exist
		// create exception for Appointment already exists.

		Optional<Appointment> e = repository.findById(id);

		if (e.isEmpty()) {
			throw new NotFoundException("Could not find Appointment with id: " + id);
		}
		return repository.findById(id);

	}

	public Optional<List<Appointment>> findByEmail(Employee email) {

		return repository.findByEmail(email);

	}

	public Optional<List<Appointment>> findByEmail(Users email) {

		return repository.findByEmail(email);

	}

	public Optional<List<Appointment>> findByCenter(Integer id) {

		TestBranch center = cRepository.findById(id).orElseThrow();
		return repository.findByCenter(center);
	}

	public List<Appointment> getAppointments(Iterable<Integer> ids) {
		return repository.findAllById(ids);

	}

	public boolean isAvailable(Appointment app) {

		ArrayList<Appointment> apps = repository.findByDateAndTime(app.getDate(), app.getTime());

		System.out.println(apps.size());
		boolean filled = false; // true if there is another appointment at this time
		if (apps.isEmpty())
			return true;

		for (Appointment a : apps) {
			if (a.getResult() == Result.Cancelled) {
				filled = false;
				System.out.println("Shoudl not print");

			} else if (a.getCenter().equals(app.getCenter())) {
				filled = true;
				System.out.println("Why not print");
			} else {
				filled = false;
				System.out.println(a.getCenter() + "Do you work" + app.getCenter());
				break;
			}

		}
		if (filled)
			return false;

		System.out.println("is this going through?");
		return true;
	}

	public void AddAppointment(Appointment t) throws AlreadyExistsException {

		System.out.println(t);

		if (!isAvailable(t)) {// throw exception if Appointment already exists
			throw new AlreadyExistsException(
					"Appointment with date " + t.getDate() + " and time " + t.getTime() + " already exists.");
		}

		repository.save(t);
	}

	public void UpdateAppointment(Appointment t) throws NotFoundException {
		Optional<Appointment> emp = repository.findById(t.getAppointment_id());
		if (emp.isEmpty())
			throw new NotFoundException("Cannot Update. No Appointment with id: " + t.getAppointment_id());
		repository.save(t);
	}

	public void DeleteAppointment(Integer id) throws NotFoundException {

		Optional<Appointment> app = repository.findById(id);
		if (app.isEmpty())
			throw new NotFoundException("Cannot delete. No Appointment with id: " + id);

		Optional<TestBranch> tb = cRepository.findById(id);
		if (tb.isPresent()) {
			repository.DeleteAllByCenter(tb.get());
			eRepository.DeleteAllByCenter(tb.get());
		}

		repository.deleteById(id);
	}

}
