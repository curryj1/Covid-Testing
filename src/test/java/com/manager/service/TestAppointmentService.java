package com.manager.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.manager.model.Appointment;
import com.mangager.exception.NotFoundException;

@SpringBootTest
public class TestAppointmentService {

	@Autowired
	AppointmentService appointmentService;

	@Autowired
	EntityManager em;

	@Test
	public void getAllAppointments() {

		List<Appointment> apps = em.createQuery("SELECT a FROM Appointment a").getResultList();

		assertEquals(apps, appointmentService.getAllAppointment());

	}

	@Test
	public void getAppointment() throws NotFoundException {
		int id = 32;

		Object app = em.createQuery("SELECT a FROM Appointment a WHERE a.appointment_id=:id").setParameter("id", id)
				.getSingleResult();

		assertEquals(app, appointmentService.getAppointment(id).get());
	}

}
