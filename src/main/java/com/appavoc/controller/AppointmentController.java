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

package com.appavoc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appavoc.model.Appointment;
import com.appavoc.service.AppointmentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appointment")
public class AppointmentController {

	@Autowired
	private AppointmentService appointmentService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Assistant"})
	public Appointment createOrUpdateAppointment(@RequestBody @Valid Appointment  appointment) {
		return appointmentService.createOrUpdateAppointment(appointment);
	}

	@RequestMapping(value = "/getAppointmentByAppointmentNumber/{appointmentNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Appointment getAppointmentByAppointmentNumber(@PathVariable long appointmentNumber) {
		return appointmentService.getAppointmentByAppointNumber(appointmentNumber);
	}

	@RequestMapping(value = "/cancel/{appointmentNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Assistant"})
	public void deleteAppointment(@PathVariable long appointmentNumber) {
		appointmentService.cancelAppointment(appointmentNumber);
	}

	@RequestMapping(value = "/getAllAppointment", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Appointment> getAllAppointment() {
		return appointmentService.getAllAppointment();
	}

	@RequestMapping(value = "/search/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Assistant"})
	public List<Appointment> searchAppointment(@PathVariable String critere) {
		return appointmentService.searchAppointment(critere);
	}

	@RequestMapping(value = "/getAppointmentByCustomer/{customerNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Customer","Role_Assistant"})
	public List<Appointment> getAppointmentsByCustomerNumber(@PathVariable long customerNumber) {
		return appointmentService.getAppointmentByCustomerNumber(customerNumber);
	}

	@RequestMapping(value = "/getAppointmentByLawyer/{lawyerNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Assistant"})
	public List<Appointment> getAppointmentsByLawyerNumber(@PathVariable long lawyerNumber) {
		return appointmentService.getAppointmentByLawyerNumber(lawyerNumber);
	}

	@RequestMapping(value = "/currentlyAppoint", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Appointment> getCurrentlyAppointment() {
		return appointmentService.getCurrentlyAppointment();
	}

	@RequestMapping(value = "/getAppointOfMonth/{month}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Assistant"})
	public List<Appointment> getAppointmentOfMonth(@PathVariable int month) {
		return appointmentService.getAppointmentOfNMonth(month);
	}

	@RequestMapping(value = "/appointDay/{date}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Assistant"})
	public List<Appointment> getAppointmentOfDay(@PathVariable String date) {
		return appointmentService.getAppointmentOfDay(date);
	}

}
