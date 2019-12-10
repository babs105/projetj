/**
 * *
o	 * Copyright (c) 2019 KhydmaShore Solutions Inc.
o	 *
o	 * All rights reserved.
o	 *
o	 *****************************************************************************
 */

/**
 * @author ZEYNAB
 *
 */

package com.appavoc.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.appavoc.model.Appointment;

public interface AppointmentRepository extends MongoRepository<Appointment, String> {

	Appointment findAppointmentByAppointmentNumber(long appointmentNumber);

	void deleteAppointmentByAppointmentNumber(long appointmentNumber);

	List<Appointment> findAppointmentByCustomerNumber(long customerNumber);

	List<Appointment> findAppointmentByLawyerNumber(long lawyerNumber);

	List<Appointment> findAppointmentByAppointmentDate(Date date);
	
}
