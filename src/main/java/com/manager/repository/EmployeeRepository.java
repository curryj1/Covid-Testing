package com.manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.manager.model.TestBranch;
import com.manager.model.users.Employee;

@Repository
@CrossOrigin("http://localhost:4200")
public interface EmployeeRepository extends JpaRepository<Employee, String> {

	@Modifying
	@Query(value = "DELETE FROM Employee2 emp where emp.tb_id=:center", nativeQuery = true)
	public void DeleteAllByCenter(@Param("center") TestBranch center);
}
