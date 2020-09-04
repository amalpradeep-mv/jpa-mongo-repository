package com.dxctraining.wisheditemmgt.wisheditem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.dxctraining.wisheditemmgt.wisheditem.dto.CreateWishedItemRequest;
import com.dxctraining.wisheditemmgt.wisheditem.dto.CustomerDto;
import com.dxctraining.wisheditemmgt.wisheditem.dto.ProductDto;
import com.dxctraining.wisheditemmgt.wisheditem.dto.WishedItemDto;
import com.dxctraining.wisheditemmgt.wisheditem.entities.WishedItem;
import com.dxctraining.wisheditemmgt.wisheditem.service.IWishedItemService;
import com.dxctraining.wisheditemmgt.wisheditem.util.WishedItemUtil;

@RestController
@RequestMapping("/wishedlists")
public class WishedItemController {

	@Autowired
	private IWishedItemService service;

	@Autowired
	private WishedItemUtil util;

	@Autowired
	private RestTemplate rest;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public WishedItemDto create(@RequestBody CreateWishedItemRequest requestData) {
		String name = requestData.getName();
		String productId = requestData.getProductId();
		Integer customerId=requestData.getCustomerId();
		WishedItem wishedItem = new WishedItem(name, productId,customerId);
		wishedItem = service.save(wishedItem);
		ProductDto productDto = fetchWishedItemsFromProduct(productId);
		CustomerDto customerDto=fetchWishedItemsFromCustomer(customerId);
		WishedItemDto response = util.wishedItemDto(wishedItem, productId, productDto.getName(), customerId, customerDto.getName());
		return response;
	}

	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public WishedItemDto getWishedItem(@PathVariable("id") String id) {
		WishedItem wishedItem = service.findById(id);
		String productId = wishedItem.getProductId();
		Integer customerId=wishedItem.getCustomerId();
		ProductDto productDto = fetchWishedItemsFromProduct(productId);
		CustomerDto customerDto=fetchWishedItemsFromCustomer(customerId);
		WishedItemDto response = util.wishedItemDto(wishedItem, productId, productDto.getName(), customerId, customerDto.getName());
		return response;
	}
	
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.CREATED)
	public List<WishedItemDto> fetchAll() {
		List<WishedItem> list = service.findAll();
		List<WishedItemDto> response = new ArrayList<>();
		for (WishedItem wishedItem : list) {
			String productId = wishedItem.getProductId();
			Integer customerId=wishedItem.getCustomerId();
			ProductDto productDto = fetchWishedItemsFromProduct(productId);
			CustomerDto customerDto=fetchWishedItemsFromCustomer(customerId);
			WishedItemDto dto = util.wishedItemDto(wishedItem, productId, productDto.getName(), customerId, customerDto.getName());
			response.add(dto);
		}
		return response;
	}

	
	public ProductDto fetchWishedItemsFromProduct(String productId) {
		String url = "http://productmgt/products/get/" + productId;
		ProductDto dto = rest.getForObject(url, ProductDto.class);
		return dto;
	}
	
	public CustomerDto fetchWishedItemsFromCustomer(Integer customerId) {
		String url="http://customermgt/customers/get/"+customerId;
		CustomerDto dto=rest.getForObject(url,CustomerDto.class);
		return dto;
	}
}
