package com.manager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.manager.model.TestBranch;
import com.manager.repository.AppointmentRepository;
import com.manager.repository.EmployeeRepository;
import com.manager.repository.TestBranchRepository;
import com.mangager.exception.AlreadyExistsException;
import com.mangager.exception.NotFoundException;

@Service
public class TestBranchService {

	@Autowired
	TestBranchRepository repository;
	@Autowired
	EmployeeRepository eRepository;
	@Autowired
	AppointmentRepository arepository;

	public List<TestBranch> getAllBranches() { // finds all the test branches
		return repository.findAll();
	}

	public Optional<TestBranch> getBranch(int id) throws NotFoundException {
		Optional<TestBranch> tb = repository.findById(id);
		if (tb.isEmpty())
			throw new NotFoundException("Cannot find Branch. No Branch with id: " + id);

		return repository.findById(id);

	}

	public Optional<TestBranch> getByName(String name) throws NotFoundException {

		Optional<TestBranch> tb = repository.findByName(name);

		if (tb.isEmpty())
			throw new NotFoundException("No Branch with the name" + name);

		return tb;
	}

	public List<TestBranch> getBranches(Iterable<Integer> ids) {

		return repository.findAllById(ids);

	}

	public void AddBranch(TestBranch t) throws AlreadyExistsException {
		Optional<TestBranch> tb = repository.findById(t.getId());
		if (!tb.isEmpty())
			throw new AlreadyExistsException("Cannot add branch. Branch with id: " + t.getId() + " already exists");
		repository.save(t);

	}

	public void UpdateBranch(TestBranch t) throws NotFoundException {
		Optional<TestBranch> tb = repository.findById(t.getId());
		if (tb.isEmpty())
			throw new NotFoundException("Cannot update branch. Branch with id: " + t.getId() + " does not exist");
		repository.save(t);
	}

	@Transactional
	public void DeleteBranch(Integer id) throws NotFoundException {

		Optional<TestBranch> tb = repository.findById(id);
		if (tb.isEmpty())
			throw new NotFoundException("Cannot delete. No Branch with id: " + id);

		arepository.DeleteAllByCenter(tb.get());
		eRepository.DeleteAllByCenter(tb.get());

		System.out.println("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
		repository.deleteById(id);
	}

}
