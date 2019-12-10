/** 
 * @interface AppointmentService 
 */

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

package com.appavoc.service;

import java.util.List;

import com.appavoc.model.Appointment;

public interface AppointmentService {

	Appointment createOrUpdateAppointment(Appointment appointment);

	Appointment getAppointmentByAppointNumber(long appointmentNumber);
	
	/* methode pour afficher les rendez-vous d'un avocat ou d'un client*/
	List<Appointment> getAppointmentByLawyerNumber(long lawyerNumber);
	
	/* methode pour afficher les rendez-vous d'un client */
	List<Appointment> getAppointmentByCustomerNumber(long customerNumber);

	List<Appointment> getAllAppointment();

	void cancelAppointment(long appointmentNumber);
	
	List<Appointment> searchAppointment(String critere);
	
	/* methode pour afficher les rendez-vous d'aujourd'hui */
	List<Appointment> getCurrentlyAppointment();
	
	/* methode pour afficher les rendez-vous d'une date donnée */
	List<Appointment> getAppointmentOfDay(String date);
	
	/* methode pour afficher les rendez-vous du mois */
	List<Appointment> getAppointmentOfNMonth(int month);

}  
