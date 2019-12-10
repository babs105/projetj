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

package com.appavoc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.appavoc.model.Customer;
import com.appavoc.model.TimelineActivity;
import com.appavoc.model.User;
import com.appavoc.repository.CustomerRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.CustomerService;
import com.appavoc.service.TimelineActivityService;
import com.appavoc.service.UserService;

@Service
public class CustomerServiceImpl implements CustomerService {

	final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	TimelineActivityService timelineActivityService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	MongoOperations mongo;

	@Override
	public Customer createOrUpdateCustomer(Customer customer) {
		long customerNumber = customer.getCustomerNumber();
		Customer customerStored = getCustomerByCustomerNumber(customerNumber);
		if(customerNumber == 0 || customerStored == null){
			/*
			 * customer.setCustomerNumber(getNextSequence("customerNumber")); User user =
			 * new User(); user.setName(customer.getName());
			 * user.setUsername(customer.getUsername()); user.setEmail(customer.getEmail());
			 * user.setPassword(customer.getPassword());
			 * user.setConfirmPassword(customer.getConfirmPassword());
			 * user.setFirstName(customer.getFirstName());
			 * user.setLastName(customer.getLastName());
			 * 
			 * User userToCreate = userService.createUser(user);
			 * customer.setId(userToCreate.getId());
			 */
			
			
		}
		Customer customerCreated = customerRepository.save(customer);
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("Customer");
		timelineActivity.setAction("Create");
		timelineActivity.setUserId(customer.getUser().getId());
		timelineActivity.setElementNumber(customerCreated.getCustomerNumber());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return customer;
	}

	@Override
	public Customer getCustomerByCustomerNumber(long customerNumber) {
		Customer customer = customerRepository.findCustomerByCustomerNumber(customerNumber);
		return customer;
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customers = customerRepository.findAll();
		return customers;
	}

	@Override
	public void deleteCustomer(long customerNumber) {
		Customer customerToDelete = customerRepository.findCustomerByCustomerNumber(customerNumber);
		customerRepository.deleteCustomerByCustomerNumber(customerNumber);
		
		logger.info("DELETE SUCCESSFULL");
		
		
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(customerNumber);
		timelineActivity.setType("Customer");
		timelineActivity.setAction("Delete");
		
		timelineActivity.setUserId(customerToDelete.getUser().getId());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
	}

	@Override
	public List<Customer> searchCustomerByName(String critere) {
		Criteria criteriaV1 = Criteria.where("firstName").is(critere);
		Criteria criteriaV2 = Criteria.where("lastName").is(critere);
		Query query = new Query(new Criteria().orOperator(criteriaV1, criteriaV2));
		List<Customer> customers = mongo.find(query, Customer.class);
		return customers;
	}

	@Override
	public List<Customer> getMostActiveCustomer() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "lastConnexionTime"));
		List<Customer> customers = mongo.find(query, Customer.class);
		return customers;
	}


	@Override
	public List<Customer> getCustomersOfOffice(long officeNumber) {
		List<Customer> customers = customerRepository.findAllCustomerByOfficeNumber(officeNumber);
		return customers;
	}

	public long getNextSequence(String key) {
		// get sequence id
		Query query = new Query(Criteria.where("_id").is(key));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		SequenceId seqId = mongo.findAndModify(query, update, options, SequenceId.class);

		return seqId.getSeq();
	}

	@Override
	public List<Customer> getfilter(String critere, List<Customer> customers) {
		
		List<Customer> customer = new ArrayList<Customer>();;
		for(int i = 0; i < customers.size(); i++) {
			if(customers.get(i).getUser().getPerson().getLocation().getAddress().getCity().equals(critere)) {
				 customer.add(customers.get(i));
			}
			if(customers.get(i).getUser().getPerson().getLocation().getAddress().getCountry().equals(critere)) {
				 customer.add(customers.get(i));
			}
			if(customers.get(i).getUser().getPerson().getLocation().getAddress().getDepartment().equals(critere)) {
				 customer.add(customers.get(i));
			}
			if(customers.get(i).getUser().getPerson().getLocation().getAddress().getRegion().equals(critere)) {
				 customer.add(customers.get(i));
			}
			if(customers.get(i).getUser().getPerson().getLocation().getAddress().getVillage().equals(critere)) {
				 customer.add(customers.get(i));
			}
		}
		return customer;
	}

	@Override
	public Optional<Customer> get(String id) {
		return this.customerRepository.findById(id);
	}

}
