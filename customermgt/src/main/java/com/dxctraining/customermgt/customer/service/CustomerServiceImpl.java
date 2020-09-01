package com.dxctraining.customermgt.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.customermgt.customer.dao.*;
import com.dxctraining.customermgt.customer.entities.*;
import com.dxctraining.customermgt.exception.*;

@Transactional
@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerDao dao;

	@Override
	public Customer save(Customer customer) {
		validate(customer);
		customer = dao.save(customer);
		return customer;
	}

	@Override
	public Customer findById(Integer id) {
		Optional<Customer> optional = dao.findById(id);
		if (!optional.isPresent()) {
			throw new CustomerNotFoundException("customer not found for id=" + id);
		}
		Customer customer = optional.get();
		return customer;
	}

	@Override
	public void remove(Integer id) {
		dao.deleteById(id);
	}

	@Override
	public List<Customer> findAll() {
		List<Customer> list = dao.findAll();
		return list;
	}

	@Override
	public List<Customer> findByName(String name) {
		List<Customer> list = dao.findByName(name);
		return list;
	}

	
	public void validate(Object arg) {
		if (arg == null) {
			throw new InvalidArgumentException("argument is null");
		}
	}
}