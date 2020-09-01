package com.dxctraining.customermgt.customer.service;

import java.util.List;

import com.dxctraining.customermgt.customer.entities.Customer;

public interface ICustomerService {

	Customer save(Customer customer);

	Customer findById(Integer id);

	void remove(Integer id);

	List<Customer> findAll();

	List<Customer> findByName(String name);

}
