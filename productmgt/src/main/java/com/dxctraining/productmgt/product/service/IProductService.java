package com.dxctraining.productmgt.product.service;

import java.util.List;

import com.dxctraining.productmgt.product.entities.Product;

public interface IProductService {

	Product save(Product product);

	Product findById(String id);

	List<Product> findAll();

	List<Product> findByName(String name);
	
	void remove(String id);

	List<Product> allProductsFromCustomer(Integer customerId);

}
