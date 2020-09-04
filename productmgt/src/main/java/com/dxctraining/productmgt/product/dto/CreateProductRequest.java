package com.dxctraining.productmgt.product.dto;

public class CreateProductRequest {

	private String name;

	public CreateProductRequest() {
	}

	public CreateProductRequest(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
