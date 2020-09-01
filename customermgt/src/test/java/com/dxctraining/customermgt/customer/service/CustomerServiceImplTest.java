package com.dxctraining.customermgt.customer.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.customermgt.customer.entities.Customer;
import com.dxctraining.customermgt.exception.CustomerNotFoundException;
import com.dxctraining.customermgt.exception.InvalidArgumentException;

import junit.framework.Assert;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Import({ CustomerServiceImpl.class })
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceImplTest {

	@Autowired
	private ICustomerService service;

	@Autowired
	private EntityManager em;

	@Test
	public void testAdd_1() {
		Executable execute = () -> service.save(null);
		Assertions.assertThrows(InvalidArgumentException.class, execute);
	}

	@Test
	public void testAdd_2() {
		String name = "Krish";
		Customer customer = new Customer(name);
		customer = service.save(customer);
		TypedQuery<Customer> query = em.createQuery("from Customer", Customer.class);
		List<Customer> list = query.getResultList();
		Customer storedCustomer = list.get(0);
		Assertions.assertEquals(customer.getId(), storedCustomer.getId());
		Assertions.assertEquals(name, storedCustomer.getName());
	}
	
	@Test
	public void testFindById_1() {
		Executable execute = () -> service.findById(34);
		Assertions.assertThrows(CustomerNotFoundException.class, execute);
	}

	@Test
	public void testFindById_2() {
		Customer customer=new Customer("Raj");
		customer=em.merge(customer);
		Integer id=customer.getId();
		Customer result=service.findById(id);
		Assertions.assertEquals(id, result.getId());
		Assertions.assertEquals(customer.getName(),result.getName());
	}
}
