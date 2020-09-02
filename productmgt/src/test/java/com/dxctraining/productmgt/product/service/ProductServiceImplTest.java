package com.dxctraining.productmgt.product.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.dxctraining.productmgt.exception.ProductNotFoundException;
import com.dxctraining.productmgt.exception.InvalidArgumentException;
import com.dxctraining.productmgt.product.entities.Product;
import com.dxctraining.productmgt.product.service.ProductServiceImpl;
import com.dxctraining.productmgt.product.service.IProductService;

@DataMongoTest
@Import({ ProductServiceMongoImpl.class })
public class ProductServiceImplTest {

	@Autowired
	private IProductService service;

	@Autowired
	private MongoTemplate mongo;

	
	@Test
	public void testAdd_2() {
		String name = "Krish";
		Product customer = new Product(name,1);
		customer = service.save(customer);
		String id=customer.getId();
		Product fetched=mongo.findById(id, Product.class);
		Assertions.assertEquals(customer.getId(), fetched.getId());
		Assertions.assertEquals(name, fetched.getName());
	}

	@Test
	public void testFindById_1() {
		Executable execute = () -> service.findById(null);
		Assertions.assertThrows(ProductNotFoundException.class, execute);
	}

	@Test
	public void testFindById_2() {
		Product customer = new Product("Raj",2);
		customer = mongo.save(customer);
		String id = customer.getId();
		Product result = service.findById(id);
		Assertions.assertEquals(id, result.getId());
		Assertions.assertEquals(customer.getName(), result.getName());
	}
}
