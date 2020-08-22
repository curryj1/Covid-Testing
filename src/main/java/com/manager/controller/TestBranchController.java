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

import com.manager.model.TestBranch;
import com.manager.service.TestBranchService;
import com.mangager.exception.AlreadyExistsException;
import com.mangager.exception.NotFoundException;

@RestController
@RequestMapping(path = "/center")
@CrossOrigin(origins = "http://localhost:4200")
public class TestBranchController {

	@Autowired
	TestBranchService centerService;

	@GetMapping(path = "/all", produces = "application/json")
	public List<TestBranch> getAllBranches() {
		return centerService.getAllBranches();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(path = "/add", consumes = "application/json")
	public ResponseEntity<TestBranch> AddTestBranch(@RequestBody TestBranch t) throws AlreadyExistsException {

		System.out.println(t);
		centerService.AddBranch(t);

		return new ResponseEntity<>(t, HttpStatus.OK);

	}

	@GetMapping(path = "/{name}", produces = "application/json")
	public ResponseEntity<TestBranch> AddTestBranch(@PathVariable String name) throws NotFoundException {
		Optional<TestBranch> tb = centerService.getByName(name);
		if (tb.isEmpty())
			throw new NotFoundException("No center with the name" + name);

		return new ResponseEntity<>(tb.get(), HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(path = "update/{id}", consumes = "application/json")
	public void UpdateBranch(@PathVariable Integer id, @RequestBody TestBranch t) throws NotFoundException {
		t.setId(id);

		TestBranch e = centerService.getBranch(t.getId()).orElse(null); // retrieves current parameters

		if (t.getCity() == null)
			t.setCity(e.getCity());

		if (t.getName() == null)
			t.setName(e.getName());

		if (t.getPhoneNumber() == null)
			t.setPhoneNumber(e.getPhoneNumber());

		if (t.getState() == null)
			t.setState(e.getState());

		if (t.getStreetAddress() == null)
			t.setStreetAddress(e.getStreetAddress());
		if (t.getZipcode() == null)
			t.setZipcode(e.getZipcode());

		centerService.UpdateBranch(t);

	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping(path = "delete/{id}")
	public void DeleteBranch(@PathVariable Integer id) throws NotFoundException {

		centerService.DeleteBranch(id);
	}
}
