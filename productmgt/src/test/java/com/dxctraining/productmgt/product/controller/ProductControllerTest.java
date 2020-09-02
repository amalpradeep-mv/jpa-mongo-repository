package com.dxctraining.productmgt.product.controller;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.transaction.annotation.Transactional;

import com.dxctraining.productmgt.exception.ProductNotFoundException;
import com.dxctraining.productmgt.product.controllers.ProductController;
import com.dxctraining.productmgt.product.dto.ProductDto;
import com.dxctraining.productmgt.product.entities.Product;

@DataMongoTest
@Import({ ProductController.class })
@Transactional
public class ProductControllerTest {

	@Autowired
	private ProductController controller;

	@Autowired
	private MongoTemplate mongo;

	@Test
	public void testGetCustomerById_1() {
		Executable execute = () -> controller.getProduct(null);
		Assertions.assertThrows(ProductNotFoundException.class, execute);
	}

	@Test
	public void testGetCustomerById_2() {
		Product customer = new Product("John",1);
		customer = mongo.save(customer);
		String id = customer.getId();
		ProductDto result = controller.getProduct(id);
		Assertions.assertEquals(id, result.getId());
		Assertions.assertEquals(customer.getName(), result.getName());

		
	}

}
