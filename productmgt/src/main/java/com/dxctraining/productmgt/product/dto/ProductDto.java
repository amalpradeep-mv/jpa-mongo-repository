package com.dxctraining.productmgt.product.dto;

public class ProductDto {

	private String id;

	private String name;

	private Integer customerId;
	
	private String customerName;
	
	public ProductDto() {}
	
	public ProductDto(String id, String name) {
		this.id=id;
		this.name=name;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
}
