package com.dxctraining.customermgt.customer.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.dxctraining.customermgt.customer.dto.CreateCustomerRequest;
import com.dxctraining.customermgt.customer.dto.CustomerDto;
import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.customer.service.ICustomerService;
import com.dxctraining.customermgt.customer.util.CustomerUtil;

@RestController
@RequestMapping("/customers")
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private CustomerUtil util;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public CustomerDto create(@RequestBody CreateCustomerRequest requestData) {
		Customer customer = new Customer();
		customer.setName(requestData.getName());
		customer = customerService.save(customer);
		CustomerDto response = util.customerDto(customer);
		return response;
	}

	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public CustomerDto getCustomer(@PathVariable("id") Integer id) {
		Customer customer = customerService.findById(id);
		CustomerDto response = util.customerDto(customer);
		return response;
	}

	

	@GetMapping("/allcustomers")
	@ResponseStatus(HttpStatus.CREATED)
	public List<CustomerDto> fetchAll() {
		List<Customer> list = customerService.findAll();
		List<CustomerDto> response = new ArrayList<>();
		for (Customer customer : list) {
			CustomerDto dto = util.customerDto(customer);
			response.add(dto);
		}
		return response;
	}
}
