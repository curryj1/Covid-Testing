package com.manager.model;

import java.sql.Date;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.manager.model.enums.Result;
import com.manager.model.users.Users;

@Entity
@Table(name = "Appointment2")
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointment_id;

	@ManyToOne
	@JoinColumn(name = "email", nullable = true)
	private Users email;

	@ManyToOne
	@JoinColumn(name = "tb_id", nullable = false)
	private TestBranch center;

	@Column
	private Result result = Result.None;

	@Column
	private Date date;

	@Column
	private LocalTime time;

	public Appointment() {
		super();
	}

	public Appointment(Users email, TestBranch center, Result result, Date date, LocalTime time) {
		super();
		this.email = email;
		this.center = center;
		this.result = result;
		this.date = date;
		this.time = time;
	}

	public Appointment(Users email, TestBranch center, Date date, LocalTime time) {
		this(email, center, Result.None, date, time);

	}

	public int getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(int appointment_id) {
		this.appointment_id = appointment_id;
	}

	public Users getEmail() {
		return email;
	}

	public void setEmail(Users email) {
		this.email = email;
	}

	public TestBranch getCenter() {
		return center;
	}

	public void setCenter(TestBranch center) {
		this.center = center;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "Appointment [appointment_id=" + appointment_id + ", email=" + email + ", center=" + center + ", result="
				+ result + ", date=" + date + ", time=" + time + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + appointment_id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Appointment other = (Appointment) obj;
		if (appointment_id != other.appointment_id)
			return false;
		return true;
	}

}
