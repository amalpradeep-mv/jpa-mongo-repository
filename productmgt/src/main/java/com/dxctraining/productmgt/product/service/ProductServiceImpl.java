package com.dxctraining.productmgt.product.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dxctraining.productmgt.exception.InvalidArgumentException;
import com.dxctraining.productmgt.exception.ProductNotFoundException;
import com.dxctraining.productmgt.product.dao.IProductDao;
import com.dxctraining.productmgt.product.entities.Product;

//@Service
public class ProductServiceImpl implements IProductService {

	@Autowired
	private IProductDao dao;

	@Override
	public Product save(Product product) {
		product = dao.save(product);
		return product;
	}

	@Override
	public Product findById(String id) {
		Optional<Product> optional = dao.findById(id); // optional is a java 8 feature
		boolean exist = optional.isPresent();
		if (!exist) {
			throw new ProductNotFoundException("product not found for id=" + id);
		}
		Product product = optional.get();
		return product;
	}

	@Override
	public List<Product> findAll() {
		List<Product> list = dao.findAll();
		return list;
	}

	@Override
	public List<Product> findByName(String name) {
		List<Product> list = dao.findByName(name);
		return list;
	}

	@Override
	public void remove(String id) {
		dao.deleteById(id);
	}

	@Override
	public List<Product> allProductsFromCustomer(Integer customerId) {
		List<Product> list = dao.allProductsFromCustomer(customerId);
		return list;
	}

}
