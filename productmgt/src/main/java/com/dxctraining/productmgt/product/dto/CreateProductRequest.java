package com.dxctraining.productmgt.product.dto;

public class CreateProductRequest {

	private String name;

	public Integer customerId;

	public CreateProductRequest() {
	}

	public CreateProductRequest(String name, Integer customerId) {
		this.name = name;
		this.customerId = customerId;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
