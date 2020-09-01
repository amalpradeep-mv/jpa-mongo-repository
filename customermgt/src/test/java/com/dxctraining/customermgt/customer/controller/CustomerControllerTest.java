package com.dxctraining.customermgt.customer.controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.customermgt.customer.controllers.CustomerController;
import com.dxctraining.customermgt.customer.dto.CreateCustomerRequest;
import com.dxctraining.customermgt.customer.dto.CustomerDto;
import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.exception.CustomerNotFoundException;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Import({ CustomerController.class })
@Transactional
public class CustomerControllerTest {

	@Autowired
	private CustomerController controller;

	@Autowired
	private EntityManager em;

	@Test
	public void testGetCustomerById_1() {
		Executable execute = () -> controller.getCustomer(43);
		Assertions.assertThrows(CustomerNotFoundException.class, execute);
	}

	@Test
	public void testGetCustomerById_2() {
		Customer customer = new Customer("John");
		customer = em.merge(customer);
		Integer id = customer.getId();
		CustomerDto result = controller.getCustomer(id);
		Assertions.assertEquals(id, result.getId());
		Assertions.assertEquals(customer.getName(), result.getName());

		TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
		List<Customer> list = query.getResultList();
		Customer storedCustomer = list.get(0);
		Assertions.assertEquals(customer.getName(), storedCustomer.getName());
		Assertions.assertEquals(result.getId(), storedCustomer.getId());

	}

/*	@Test
	public void testCreateCustomer() {
		CreateCustomerRequest customer=new CreateCustomerRequest("Krish");
		customer = em.merge(customer);
		CustomerDto result=controller.create(customer);
		Assertions.assertEquals(customer.getName(), result.getName());

		TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
		List<Customer> list = query.getResultList();
		Customer storedCustomer = list.get(0);
		Assertions.assertEquals(customer.getName(), storedCustomer.getName());
		Assertions.assertEquals(result.getId(), storedCustomer.getId());

	}
	*/
}
