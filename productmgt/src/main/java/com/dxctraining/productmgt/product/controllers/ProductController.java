package com.dxctraining.productmgt.product.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.dxctraining.productmgt.product.dto.CreateProductRequest;
import com.dxctraining.productmgt.product.dto.CustomerDto;
import com.dxctraining.productmgt.product.dto.ProductDto;
import com.dxctraining.productmgt.product.entities.Product;
import com.dxctraining.productmgt.product.service.IProductService;
import com.dxctraining.productmgt.product.util.ProductUtil;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private IProductService service;

	@Autowired
	private ProductUtil util;

	@Autowired
	private RestTemplate rest;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public ProductDto create(@RequestBody CreateProductRequest requestData) {
		String name = requestData.getName();
		Integer customerId = requestData.getCustomerId();
		Product product = new Product(name, customerId);
		product = service.save(product);
		CustomerDto customerDto = fetchProductsFromCustomer(customerId);
		ProductDto response = util.productDto(product, customerId, customerDto.getName());
		return response;
	}

	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ProductDto getProduct(@PathVariable("id") String id) {
		Product product = service.findById(id);
		Integer customerId = product.getCustomerId();
		CustomerDto customerDto = fetchProductsFromCustomer(customerId);
		ProductDto response = util.productDto(product, customerId, customerDto.getName());
		return response;
	}

	

	@GetMapping("/allproducts")
	@ResponseStatus(HttpStatus.CREATED)
	public List<ProductDto> fetchAll() {
		List<Product> list = service.findAll();
		List<ProductDto> response = new ArrayList<>();
		for (Product product : list) {
			Integer customerId = product.getCustomerId();
			CustomerDto customerDto = fetchProductsFromCustomer(customerId);
			ProductDto dto = util.productDto(product, customerId, customerDto.getName());
			response.add(dto);
		}
		return response;
	}

	public CustomerDto fetchProductsFromCustomer(Integer customerId) {
		String url = "http://customermgt/customers/get/" + customerId;
		CustomerDto dto = rest.getForObject(url, CustomerDto.class);
		return dto;
	}
}
