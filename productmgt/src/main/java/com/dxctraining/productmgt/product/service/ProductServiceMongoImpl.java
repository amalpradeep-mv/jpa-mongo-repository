package com.dxctraining.productmgt.product.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.dxctraining.productmgt.exception.*;
import com.dxctraining.productmgt.product.entities.*;

@Service
public class ProductServiceMongoImpl implements IProductService {

	@Autowired
	private MongoTemplate mongo;

	@Override
	public Product save(Product product) {
		product = mongo.save(product);
		return product;
	}

	@Override
	public Product findById(String id) {
		validate(id);
		Product product = mongo.findById(id, Product.class);
		return product;
	}

	@Override
	public void remove(String id) {
		Product product = findById(id);
		mongo.remove(product);
	}

	@Override
	public List<Product> findAll() {
		List<Product> list = mongo.findAll(Product.class);
		return list;
	}

	@Override
	public List<Product> findByName(String name) {
		Criteria criteria = Criteria.where("name").is(name);
		Query query = Query.query(criteria);
		List<Product> list = mongo.find(query, Product.class);
		return list;
	}

	
	public void validate(String id) {
		if (id == null) {
			throw new ProductNotFoundException("Product not found");
		}
	}

	@Override
	public List<Product> allProductsFromCustomer(Integer customerId){
		Criteria criteria=Criteria.where("customerId").is(customerId);
		Query query=Query.query(criteria);
		List<Product>list=mongo.find(query, Product.class);
		return list;
		
	}

}