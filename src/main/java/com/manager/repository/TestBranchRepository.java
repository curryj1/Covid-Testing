package com.manager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.manager.model.TestBranch;

@Repository
@CrossOrigin("http://localhost:4200")
public interface TestBranchRepository extends JpaRepository<TestBranch, Integer> {

	public Optional<TestBranch> findByName(String name);

}
