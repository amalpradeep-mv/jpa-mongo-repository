package com.dxctraining.wishedlistmgt.wishedlist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import com.dxctraining.wishedlistmgt.wishedlist.dto.CreateWishedListRequest;
import com.dxctraining.wishedlistmgt.wishedlist.dto.CustomerDto;
import com.dxctraining.wishedlistmgt.wishedlist.dto.ProductDto;
import com.dxctraining.wishedlistmgt.wishedlist.dto.WishedListDto;
import com.dxctraining.wishedlistmgt.wishedlist.entities.WishedList;
import com.dxctraining.wishedlistmgt.wishedlist.service.IWishedListService;
import com.dxctraining.wishedlistmgt.wishedlist.util.WishedListUtil;

@RestController
@RequestMapping("/wishedlists")
public class WishedListController {

	@Autowired
	private IWishedListService service;

	@Autowired
	private WishedListUtil util;

	@Autowired
	private RestTemplate rest;

	@PostMapping("/add")
	@ResponseStatus(HttpStatus.CREATED)
	public WishedListDto create(@RequestBody CreateWishedListRequest requestData) {
		String name = requestData.getName();
		String productId = requestData.getProductId();
		Integer customerId=requestData.getCustomerId();
		WishedList wishedList = new WishedList(name, productId,customerId);
		wishedList = service.save(wishedList);
		ProductDto productDto = fetchWishedListsFromProduct(productId);
		CustomerDto customerDto=fetchWishedListsFromCustomer(customerId);
		WishedListDto response = util.wishedListDto(wishedList, productId, productDto.getName(), customerId, customerDto.getName());
		return response;
	}

	@GetMapping("/get/{id}")
	@ResponseStatus(HttpStatus.OK)
	public WishedListDto getWishedList(@PathVariable("id") String id) {
		WishedList wishedList = service.findById(id);
		String productId = wishedList.getProductId();
		Integer customerId=wishedList.getCustomerId();
		ProductDto productDto = fetchWishedListsFromProduct(productId);
		CustomerDto customerDto=fetchWishedListsFromCustomer(customerId);
		WishedListDto response = util.wishedListDto(wishedList, productId, productDto.getName(), customerId, customerDto.getName());
		return response;
	}
	
	
	@GetMapping("/all")
	@ResponseStatus(HttpStatus.CREATED)
	public List<WishedListDto> fetchAll() {
		List<WishedList> list = service.findAll();
		List<WishedListDto> response = new ArrayList<>();
		for (WishedList wishedList : list) {
			String productId = wishedList.getProductId();
			Integer customerId=wishedList.getCustomerId();
			ProductDto productDto = fetchWishedListsFromProduct(productId);
			CustomerDto customerDto=fetchWishedListsFromCustomer(customerId);
			WishedListDto dto = util.wishedListDto(wishedList, productId, productDto.getName(), customerId, customerDto.getName());
			response.add(dto);
		}
		return response;
	}

	
	public ProductDto fetchWishedListsFromProduct(String productId) {
		String url = "http://localhost:8686/products/get/" + productId;
		ProductDto dto = rest.getForObject(url, ProductDto.class);
		return dto;
	}
	
	public CustomerDto fetchWishedListsFromCustomer(Integer customerId) {
		String url="http://localhost:8585/customers/get/"+customerId;
		CustomerDto dto=rest.getForObject(url,CustomerDto.class);
		return dto;
	}
}
