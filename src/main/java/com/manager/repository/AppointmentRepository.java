package com.manager.repository;

import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.manager.model.Appointment;
import com.manager.model.TestBranch;
import com.manager.model.users.Users;

@Repository
@CrossOrigin("http://localhost:4200")
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
	public Optional<List<Appointment>> findByEmail(Users email);

	public Optional<List<Appointment>> findByCenter(TestBranch center);

	@Query(value = "SELECT * FROM Appointment2 app where app.date=:date and app.time=:time", nativeQuery = true)
	public ArrayList<Appointment> findByDateAndTime(@Param("date") Date date, @Param("time") LocalTime time);

	@Modifying
	@Query(value = "DELETE FROM Appointment2 app where app.tb_id=:center", nativeQuery = true)
	public void DeleteAllByCenter(@Param("center") TestBranch center);

	@Modifying
	@Query(value = "DELETE FROM Appointment2 app where app.email=:email", nativeQuery = true)
	public void DeleteAllbyEmail(@Param("email") Users email);

}
